package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.lib.fusion.NavXEncoderFusion;
import org.usfirst.frc.team4737.robot.commands.TeleopDrive;

import static org.usfirst.frc.team4737.robot.RobotMap.*;

/**
 * @author brian
 * @version Feb. 13, 2017
 */
public class Drive extends Subsystem {

//    private AHRS navX;
//    private Encoder leftEnc;
//    private Encoder rightEnc;

//    private NavXEncoderFusion tibng;

    private CANTalon leftFront;
    private CANTalon leftRear;
    private CANTalon rightFront;
    private CANTalon rightRear;

    private RobotDrive dumbDrive;

    public Drive() {
        super("Drive");

//        navX = new AHRS(SPI.Port.kMXP, (byte) (NAVX_UPDATE_RATE_HZ & 0xff));
//        leftEnc = new Encoder(DRIVE_ENC_L_A, DRIVE_ENC_L_B, DRIVE_ENC_L_REV);
//        rightEnc = new Encoder(DRIVE_ENC_R_A, DRIVE_ENC_R_B, DRIVE_ENC_R_REV);
//        leftEnc.setDistancePerPulse(DRIVE_ENC_DIST_PER_PULSE);
//        rightEnc.setDistancePerPulse(DRIVE_ENC_DIST_PER_PULSE);

//        tibng = new NavXEncoderFusion(navX, AHRS.BoardAxis.kBoardAxisX, false, leftEnc, rightEnc);

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
    }

    public void dumbDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        dumbDrive.arcadeDrive(moveValue, rotateValue, squaredInputs);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopDrive());
    }

}

