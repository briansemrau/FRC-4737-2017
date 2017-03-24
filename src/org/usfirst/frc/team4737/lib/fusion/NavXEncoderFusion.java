package org.usfirst.frc.team4737.lib.fusion;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;

import static org.usfirst.frc.team4737.robot.RobotMap.*;

/**
 * An object for fusing the NavX MXP and two drive wheel distance encoders to create a more accurate measurement of
 * speed and distance driven. The sensor values are compared to detect encoder slipping, in which the less accurate NavX
 * values are used instead of the unreliable encoder data.
 *
 * @author Brian Semrau
 */
public class NavXEncoderFusion {

    private AHRS navX;
    private Encoder left;
    private Encoder right;

    private PIDSource leftSource;
    private PIDSource rightSource;

    private AHRS.BoardAxis forwardAxis;
    private boolean axisInverted;

    private boolean leftSlipping;
    private boolean rightSlipping;

    private double timeNavXStopped;

    private double leftDisplacementAccum;
    private double rightDisplacementAccum;
    private double timeLastLeftAccumReset;
    private double timeLastRightAccumReset;

    private double lastEncDistL;
    private double lastEncDistR;
    private double lastNavXDisplacement;
    private double lastNavXAngle;

    private double lastPeriodicTime;

    public NavXEncoderFusion(AHRS navX, AHRS.BoardAxis forwardAxis, boolean axisInverted, Encoder left, Encoder right) {
        this.navX = navX;
        this.forwardAxis = forwardAxis;
        this.axisInverted = axisInverted;
        this.left = left;
        this.right = right;

        leftSource = new PIDSource() {
            private PIDSourceType type;

            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                this.type = pidSource;
            }

            @Override
            public PIDSourceType getPIDSourceType() {
                return type;
            }

            @Override
            public double pidGet() {
                switch (type) {
                    case kDisplacement:
                        return getLeftDist();
                    case kRate:
                        double value = getLeftRate();
                        resetLeftAccum();
                        return value;
                }
                return 0;
            }
        };
        rightSource = new PIDSource() {
            private PIDSourceType type;

            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                this.type = pidSource;
            }

            @Override
            public PIDSourceType getPIDSourceType() {
                return type;
            }

            @Override
            public double pidGet() {
                switch (type) {
                    case kDisplacement:
                        return getRightDist();
                    case kRate:
                        double value = getRightRate();
                        resetRightAccum();
                        return value;
                }
                return 0;
            }
        };

        timeNavXStopped = Timer.getFPGATimestamp();

        timeLastLeftAccumReset = Timer.getFPGATimestamp();
        timeLastRightAccumReset = Timer.getFPGATimestamp();

        lastPeriodicTime = Timer.getFPGATimestamp();
    }

    private double navXDisplacement() {
        switch (forwardAxis) {
            case kBoardAxisX:
                return axisInverted ? -navX.getDisplacementX() : navX.getDisplacementX();
            case kBoardAxisY:
                return axisInverted ? -navX.getDisplacementY() : navX.getDisplacementY();
            case kBoardAxisZ:
                return axisInverted ? -navX.getDisplacementZ() : navX.getDisplacementZ();
        }
        return axisInverted ? navX.getDisplacementX() : -navX.getDisplacementX();
    }

    private double navXRate() {
        switch (forwardAxis) {
            case kBoardAxisX:
                return axisInverted ? -navX.getVelocityX() : navX.getVelocityX();
            case kBoardAxisY:
                return axisInverted ? -navX.getVelocityY() : navX.getVelocityY();
            case kBoardAxisZ:
                return axisInverted ? -navX.getVelocityZ() : navX.getVelocityZ();
        }
        return axisInverted ? navX.getVelocityX() : -navX.getVelocityX();
    }

    private double getLeftRate() {
        return leftDisplacementAccum / timeLastLeftAccumReset;
    }

    private double getRightRate() {
        return rightDisplacementAccum / timeLastRightAccumReset;
    }

    private double getLeftDist() {
        return leftDisplacementAccum;
    }

    private double getRightDist() {
        return rightDisplacementAccum;
    }

    private void resetLeftAccum() {
        leftDisplacementAccum = 0;
        timeLastLeftAccumReset = Timer.getFPGATimestamp();
    }

    private void resetRightAccum() {
        rightDisplacementAccum = 0;
        timeLastRightAccumReset = Timer.getFPGATimestamp();
    }

    public void periodic() {
        double currentTime = Timer.getFPGATimestamp();
        double deltaTime = currentTime - lastPeriodicTime;
        lastPeriodicTime = currentTime;

        double encDistL = left.getDistance();
        double encDistR = right.getDistance();
        double navDisplacement = navXDisplacement();
        double navAngle = navX.getAngle();

        double deltaEncDistL = encDistL - lastEncDistL;
        double deltaEncDistR = encDistR - lastEncDistR;
        double deltaNavDisplacement = navDisplacement - lastNavXDisplacement;
        double deltaNavAngle = navAngle - lastNavXAngle;

        double deltaNavDistL = deltaNavDisplacement - deltaNavAngle * Math.PI * WHEELBASE / 360.0;
        double deltaNavDistR = deltaNavDisplacement + deltaNavAngle * Math.PI * WHEELBASE / 360.0;

        double estNavXDistErr = timeNavXStopped * NAVX_DISPLACEMENT_DRIFT_PER_S;

        //
        // Detect slipping
        //

        if (navX.isMoving()) {
            if (Math.abs(deltaEncDistL - deltaNavDistL) > estNavXDistErr) {
                leftSlipping = true;
            } else leftSlipping = false;

            if (Math.abs(deltaEncDistR - deltaNavDistR) > estNavXDistErr) {
                rightSlipping = true;
            } else rightSlipping = false;
        } else {
            if (deltaEncDistL / deltaTime > ENCODER_SLIPDETECT_MAX_RATE) {
                leftSlipping = true;
            } else leftSlipping = false;

            if (deltaEncDistR / deltaTime > ENCODER_SLIPDETECT_MAX_RATE) {
                rightSlipping = true;
            } else rightSlipping = false;

            timeNavXStopped = Timer.getFPGATimestamp();
        }

        //
        // Add to the distance accumulators for the next data pull
        //

        // If slipping, use rough navX values, otherwise use encoders
        if (leftSlipping)
            leftDisplacementAccum += deltaNavDistL;
        else
            leftDisplacementAccum += deltaEncDistL;

        if (rightSlipping)
            rightDisplacementAccum += deltaNavDistR;
        else
            rightDisplacementAccum += deltaEncDistR;

        lastEncDistL = encDistL;
        lastEncDistR = encDistR;
        lastNavXDisplacement = navDisplacement;
        lastNavXAngle = navAngle;
    }

    /**
     * @return Returns whether or not the left wheels are detected slipping.
     */
    public boolean isLeftSlipping() {
        return leftSlipping;
    }

    /**
     * @return Returns whether or not the right wheels are detected slipping.
     */
    public boolean isRightSlipping() {
        return rightSlipping;
    }

    /**
     * Calculates the speed of the left wheels based on current sensor rate data. Using the left PIDSource may be more
     * accurate since it calculates an average rate over the time between pidGet calls, rather than the current sensor
     * calculated values.
     * <p>
     * TODO find out if that's actually true
     *
     * @return Returns the instantaneous detected speed of the left wheels adjusted for slipping.
     */
    public double getRoughLeftRate() {
        if (leftSlipping) {
            return navXRate() - navX.getRate() * Math.PI * WHEELBASE / 360.0;
        } else return left.getRate();
    }

    /**
     * Calculates the speed of the right wheels based on current sensor rate data. Using the right PIDSource may be more
     * accurate since it calculates an average rate over the time between pidGet calls, rather than the current sensor
     * calculated values.
     * <p>
     * TODO find out if that's actually true
     *
     * @return Returns the instantaneous detected speed of the right wheels adjusted for slipping.
     */
    public double getRoughRightRate() {
        if (rightSlipping) {
            return navXRate() + navX.getRate() * Math.PI * WHEELBASE / 360.0;
        } else return right.getRate();
    }

    public PIDSource getLeftSource() {
        return leftSource;
    }

    public PIDSource getRightSource() {
        return rightSource;
    }

    /**
     * Resets the calculated distance for the left and right wheels.
     */
    public void resetDistance() {
        resetLeftAccum();
        resetRightAccum();
    }

}
