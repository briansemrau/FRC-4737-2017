package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.robot.RobotMap;
import org.usfirst.frc.team4737.robot.commands.StopShooter;

/**
 * @author brian
 * @version Feb. 10, 2017
 */
public class Shooter extends Subsystem {

    public enum Side {
        LEFT(RobotMap.SHOOTER_L_TALON, RobotMap.FEEDER_L_TALON, false),
        RIGHT(RobotMap.SHOOTER_R_TALON, RobotMap.FEEDER_R_TALON, true);

        public final int flywheelID;
        public final int feederID;
        public final boolean reverse;

        Side(int flywheelID, int feederID, boolean reverse) {
            this.flywheelID = flywheelID;
            this.feederID = feederID;
            this.reverse = reverse;
        }
    }

    private CANTalon flywheelTalon;
    private CANTalon feederTalon;

    public Shooter(Side side) {
        super("Shooter" + (side.reverse ? "L" : "R"));

        // Init flywheel talon
        flywheelTalon = new CANTalon(side.flywheelID);

        flywheelTalon.enableBrakeMode(false);

        flywheelTalon.reverseOutput(false); // TODO reverse needs to affect this

        flywheelTalon.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, 10);
        flywheelTalon.configEncoderCodesPerRev(1024);
        flywheelTalon.reverseSensor(false); // TODO reverse might need to affect this

        flywheelTalon.changeControlMode(CANTalon.TalonControlMode.Speed);

        // Init feeder talon
        feederTalon = new CANTalon(side.feederID);
        feederTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
    }

    // #######
    // Setters
    // #######

    public void getSmartDashboardPIDFvals() {
        flywheelTalon.setPID(
                SmartDashboard.getNumber("ShootP", 0),
                SmartDashboard.getNumber("ShootI", 0),
                SmartDashboard.getNumber("ShootD", 0));
        flywheelTalon.setF(SmartDashboard.getNumber("ShootF", 0));
    }

    public void setShooterTargetSpeed(double target) {
        flywheelTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
        flywheelTalon.set(target);
    }

    public void setShooterVoltage(double speed) {
        flywheelTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
        flywheelTalon.set(speed);
    }

    public void setFeedVoltage(double voltage) {
        feederTalon.set(voltage);
    }

    public void stop() {
        setShooterVoltage(0);

    }

    // #######
    // Getters
    // #######

    public double getSpeed() {
        return flywheelTalon.getSpeed();
    }

    public double getTarget() {
        return flywheelTalon.getSetpoint();
    }

    public double getClosedLoopError() {
        return flywheelTalon.getClosedLoopError();
    }

    public double getVPercent() {
        return flywheelTalon.getOutputVoltage() / flywheelTalon.getBusVoltage();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopShooter(this));
    }

}

