package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.lib.fusion.NavXEncoderFusion;
import org.usfirst.frc.team4737.robot.commands.drive.TeleopDrive;

import static org.usfirst.frc.team4737.robot.RobotMap.*;

/**
 * @author Brian Semrau
 * @version Feb. 13, 2017
 */
public class Drive extends Subsystem {

    private AHRS navX;
//    public Encoder leftEnc;
    public Encoder rightEnc;

//    private NavXEncoderFusion deadReckon;

    private CANTalon leftFront;
    private CANTalon leftRear;
    private CANTalon rightFront;
    private CANTalon rightRear;

    private RobotDrive dumbDrive;

    private PIDController leftDistControl;
    private PIDController rightDistControl;

    private PIDController steerControl;
    private PIDController speedControl;

    public Drive() {
        super("Drive");

        // Init sensors

        navX = new AHRS(SPI.Port.kMXP, (byte) (NAVX_UPDATE_RATE_HZ & 0xff));
//        leftEnc = new Encoder(DRIVE_ENC_L_A, DRIVE_ENC_L_B, DRIVE_ENC_L_REV);
        rightEnc = new Encoder(DRIVE_ENC_R_A, DRIVE_ENC_R_B, DRIVE_ENC_R_REV);
//        leftEnc.setDistancePerPulse(DRIVE_ENC_DIST_PER_PULSE);
        rightEnc.setDistancePerPulse(DRIVE_ENC_DIST_PER_PULSE);

//        deadReckon = new NavXEncoderFusion(navX, AHRS.BoardAxis.kBoardAxisX, false, leftEnc, rightEnc);

        // Init talons

        leftFront = new CANTalon(DRIVE_LF_TALON);
        leftRear = new CANTalon(DRIVE_LR_TALON);
        rightFront = new CANTalon(DRIVE_RF_TALON);
        rightRear = new CANTalon(DRIVE_RR_TALON);

        leftFront.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        rightFront.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

        leftRear.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftRear.set(leftFront.getDeviceID());
        rightRear.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightRear.set(rightFront.getDeviceID());

        // Init controls

        dumbDrive = new RobotDrive(leftFront, rightFront);

        double distTolerance = 1.0 / 12.0;
        leftDistControl = new PIDController(.45, 0, 0, 0, new PIDSource() {
            public void setPIDSourceType(PIDSourceType pidSource) {
            }

            public PIDSourceType getPIDSourceType() {
                return PIDSourceType.kDisplacement;
            }

            public double pidGet() {
                return rightEnc.getDistance();//leftEnc.getDistance();
            }
        }, this::updateLeftOutput);
        leftDistControl.setAbsoluteTolerance(distTolerance);

        rightDistControl = new PIDController(.45, 0, 0, 0, new PIDSource() {
            public void setPIDSourceType(PIDSourceType pidSource) {
            }

            public PIDSourceType getPIDSourceType() {
                return PIDSourceType.kDisplacement;
            }

            public double pidGet() {
                return rightEnc.getDistance();
            }
        }, this::updateRightOutput);
        rightDistControl.setAbsoluteTolerance(distTolerance);

        steerControl = new PIDController(1.0 / 3.0, 0, 0, 0, new PIDSource() {
            public void setPIDSourceType(PIDSourceType pidSource) {
            }

            public PIDSourceType getPIDSourceType() {
                return PIDSourceType.kDisplacement;
            }

            public double pidGet() {
                return navX.getAngle() % 360;
            }
        }, this::updateSteerOutput);
        steerControl.setInputRange(0, 360);
        steerControl.setContinuous();

        speedControl = new PIDController(0.05, 0, 0, 0.1, new PIDSource() {
            public void setPIDSourceType(PIDSourceType pidSource) {
            }

            public PIDSourceType getPIDSourceType() {
                return PIDSourceType.kRate;
            }

            public double pidGet() {
                return rightEnc.getRate();//(leftEnc.getRate() + rightEnc.getRate()) / 2;

            }
        }, this::updateSpeedOutput);

//        distControl = new PIDController(0.35, 0.001, 0, 0, new PIDSource() {
//            public void setPIDSourceType(PIDSourceType pidSource) {
//            }
//
//            public PIDSourceType getPIDSourceType() {
//                return PIDSourceType.kDisplacement;
//            }
//
//            public double pidGet() {
//                return (leftEnc.getDistance() + rightEnc.getDistance()) / 2;
//
//            }
//        }, this::updateSpeedOutput);
//        distControl.setAbsoluteTolerance(3.0 / 12.0);
    }

    public void setSmoothDrive(double ramp) {
        leftFront.setVoltageRampRate(ramp);
        rightFront.setVoltageRampRate(ramp);
        leftRear.setVoltageRampRate(ramp);
        rightRear.setVoltageRampRate(ramp);
    }

    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        steerControl.disable();
        speedControl.disable();
        steerControl.setSetpoint(0);
        speedControl.setSetpoint(0);

        dumbDrive.arcadeDrive(moveValue, rotateValue, squaredInputs);
    }

    private double steerOutput, speedOutput;

    private void updateSteerOutput(double steerOutput) {
        this.steerOutput = -steerOutput;
        dumbDrive.arcadeDrive(this.speedOutput, this.steerOutput, false);
    }

    private void updateSpeedOutput(double speedOutput) {
        this.speedOutput = speedOutput;
        dumbDrive.arcadeDrive(this.speedOutput, this.steerOutput, false);
    }

    private double leftOutput, rightOutput;

    private void updateLeftOutput(double output) {
        leftOutput = output;
        dumbDrive.tankDrive(this.leftOutput, this.rightOutput);
    }

    private void updateRightOutput(double output) {
        rightOutput = output;
        dumbDrive.tankDrive(this.leftOutput, this.rightOutput);
    }

    public void setMaxOutput(double output) {
        dumbDrive.setMaxOutput(output);
    }

    public void setTargetAngle(double targetAngle) {
        steerControl.setSetpoint(targetAngle);
        steerControl.enable();
    }

    public void setTargetSpeed(double targetSpeed) {
        speedControl.setSetpoint(targetSpeed);
        speedControl.enable();
    }

    public void setTargetDist(double distance) {
        leftDistControl.setSetpoint(distance);
        rightDistControl.setSetpoint(distance);
        leftDistControl.enable();
        rightDistControl.enable();
    }

    public void resetEncoders() {
//        leftEnc.reset();
        rightEnc.reset();
    }

    public void resetNavX() {
        navX.reset();
    }

    public void disableSpeedPID() {
        speedControl.disable();
    }

    public void disableDistancePID() {
        leftDistControl.disable();
        rightDistControl.disable();
    }

    public void disableAnglePID() {
        steerControl.disable();
    }

    public boolean distanceOnTarget() {
        return /*leftDistControl.onTarget() && */rightDistControl.onTarget();
    }

    public void setBrakeMode(boolean brake) {
        leftFront.enableBrakeMode(brake);
        leftRear.enableBrakeMode(brake);
        rightFront.enableBrakeMode(brake);
        rightRear.enableBrakeMode(brake);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopDrive());
    }

}

