package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.lib.fusion.NavXEncoderFusion;
import org.usfirst.frc.team4737.robot.commands.drive.TeleopDrive;

import static org.usfirst.frc.team4737.robot.RobotMap.*;

/**
 * @author Brian Semrau
 * @version Feb. 13, 2017
 */
public class Drive extends Subsystem {

    private AHRS navX;
    private Encoder leftEnc;
    private Encoder rightEnc;

    private NavXEncoderFusion deadReckon;

    private CANTalon leftFront;
    private CANTalon leftRear;
    private CANTalon rightFront;
    private CANTalon rightRear;

    private RobotDrive dumbDrive;

    private PIDController leftControl;
    private PIDController rightControl;

    public Drive() {
        super("Drive");

        navX = new AHRS(SPI.Port.kMXP, (byte) (NAVX_UPDATE_RATE_HZ & 0xff));
        leftEnc = new Encoder(DRIVE_ENC_L_A, DRIVE_ENC_L_B, DRIVE_ENC_L_REV);
        rightEnc = new Encoder(DRIVE_ENC_R_A, DRIVE_ENC_R_B, DRIVE_ENC_R_REV);
        leftEnc.setDistancePerPulse(DRIVE_ENC_DIST_PER_PULSE);
        rightEnc.setDistancePerPulse(DRIVE_ENC_DIST_PER_PULSE);

        deadReckon = new NavXEncoderFusion(navX, AHRS.BoardAxis.kBoardAxisX, false, leftEnc, rightEnc);

        leftFront = new CANTalon(DRIVE_LF_TALON);
        leftRear = new CANTalon(DRIVE_LR_TALON);
        rightFront = new CANTalon(DRIVE_RF_TALON);
        rightRear = new CANTalon(DRIVE_RR_TALON);

        leftRear.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftRear.set(leftFront.getDeviceID());
        rightRear.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightRear.set(rightFront.getDeviceID());

        leftFront.setInverted(true);

        dumbDrive = new RobotDrive(leftFront, rightFront);

//        leftControl = new PIDController(p, i, d, f, deadReckon.getLeftSource(), leftFront);
//        rightControl = new PIDController(p, i, d, f, deadReckon.getRightSource(), leftFront);
    }

    public void dumbDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        leftFront.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        rightFront.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        dumbDrive.arcadeDrive(moveValue, rotateValue, squaredInputs);
    }

    public void setDistSetpoint(double leftDist, double rightDist) {
        // Set PIDSourceType to displacement
        deadReckon.getLeftSource().setPIDSourceType(PIDSourceType.kDisplacement);
        deadReckon.getRightSource().setPIDSourceType(PIDSourceType.kDisplacement);

        // Configure talon control mode
//        leftFront.changeControlMode(CANTalon.TalonControlMode.MotionProfile);
//        rightFront.changeControlMode(CANTalon.TalonControlMode.MotionProfile);

        // Set setpoints
//        leftFront.pushMotionProfileTrajectory(new CANTalon.TrajectoryPoint());
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

