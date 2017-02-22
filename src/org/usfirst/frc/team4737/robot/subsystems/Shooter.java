package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.robot.Robot;
import org.usfirst.frc.team4737.robot.commands.shooter.StopShooter;

/**
 * @author Brian Semrau
 * @version Feb. 10, 2017
 */
public class Shooter extends Subsystem {

    private CANTalon flywheelTalon;

    public Shooter(Robot.Side side) {
        super("Shooter" + side.name);

        side.shooter = this;

        flywheelTalon = new CANTalon(side.flywheelID);

        flywheelTalon.enableBrakeMode(false);
        flywheelTalon.reverseOutput(side.reverse);

        flywheelTalon.setStatusFrameRateMs(CANTalon.StatusFrameRate.QuadEncoder, 10);
        flywheelTalon.configEncoderCodesPerRev(1); // It's actually 1024, but we're dealing with encoder units universally
        flywheelTalon.reverseSensor(!side.reverse);

        flywheelTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
    }

    // #######
    // Setters
    // #######

    public void getSmartDashboardPIDFvals() {
        flywheelTalon.setPID(
                SmartDashboard.getNumber(this.getName() + "P", 0),
                SmartDashboard.getNumber(this.getName() + "I", 0),
                SmartDashboard.getNumber(this.getName() + "D", 0));
        flywheelTalon.setF(SmartDashboard.getNumber(this.getName() + "F", 0));
    }

    public void setPIDF(double p, double i, double d, double f) {
        flywheelTalon.setPID(p, i, d);
        flywheelTalon.setF(f);
        SmartDashboard.putNumber(this.getName() + "P", p);
        SmartDashboard.putNumber(this.getName() + "I", i);
        SmartDashboard.putNumber(this.getName() + "D", d);
        SmartDashboard.putNumber(this.getName() + "F", f);
    }

    public void setShooterTargetSpeed(double target) {
        flywheelTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
        flywheelTalon.set(target);
    }

    public void setShooterVoltage(double speed) {
        flywheelTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
        flywheelTalon.set(speed);
    }

    public void stop() {
        setShooterVoltage(0);
    }

    // #######
    // Getters
    // #######

    public boolean readyToShoot() {
        return Math.abs(getClosedLoopError()) < SmartDashboard.getNumber("shooterTolerance", 0) * 1024;//RobotMap.SHOOTING_SPEED_TOLERANCE;
    }

    public double getSpeed() {
        return flywheelTalon.getSpeed();
    }

    public double getTarget() {
        return flywheelTalon.getSetpoint();
    }

    public double getClosedLoopError() {
        return flywheelTalon.getClosedLoopError();
    }

    public double getVoltageOutput() {
        return flywheelTalon.getOutputVoltage();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopShooter(this));
    }

}

