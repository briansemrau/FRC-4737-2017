package org.usfirst.frc.team4737.lib.fusion;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team4737.lib.fusion.history.PositionHistory;
import org.usfirst.frc.team4737.robot.subsystems.*;

/**
 * @author Brian Semrau
 * @version 2/25/2017
 */
public class SensorFusion {

    private AHRS navX;
    private AHRS.BoardAxis forwardAxis;
    private boolean axisInverted;
    private Encoder leftEnc;
    private Encoder rightEnc;
    private JetsonTX1 jetson;

    private PositionHistory history;

    public SensorFusion(AHRS navX, AHRS.BoardAxis forwardAxis, boolean axisInverted, Encoder left, Encoder right, JetsonTX1 jetson) {
        this.navX = navX;
        this.forwardAxis = forwardAxis;
        this.axisInverted = axisInverted;
        this.leftEnc = left;
        this.rightEnc = right;
        this.jetson = jetson;

        history = new PositionHistory();
    }

    private double lastTime = -1;

    private double lastNavXAngle;

    public void update() {
        if (lastTime == -1) {
            lastTime = Timer.getFPGATimestamp();
            return;
        }

        double currentTime = Timer.getFPGATimestamp();
        double deltaTime = currentTime - lastTime;
        lastTime = currentTime;

//        if (jetson.hasVisionUpdate()) {
            // update position
//        }

        // Record sensor data
        double angle = navX.getAngle();
        double leftDist = leftEnc.getDistance();
        double rightDist = rightEnc.getDistance();
        leftEnc.reset();
        rightEnc.reset();

        // Calculate change in field position
        double deltaAngle = angle - lastNavXAngle;
        double deltaDist;
        if (deltaAngle < 0) {
            deltaDist = leftDist;
        } else {
            deltaDist = rightDist;
        }

//        Point deltaPos = new Point(new Point(), );

        // Finish up
        lastNavXAngle = angle;

    }

    public void getFieldPosition() {

    }

}
