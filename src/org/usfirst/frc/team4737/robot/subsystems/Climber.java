package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.robot.RobotMap;
import org.usfirst.frc.team4737.robot.commands.StopClimber;

/**
 * @author brian
 * @version Feb. 14, 2017
 */
public class Climber extends Subsystem {

    private CANTalon climberTalon;

    public Climber() {
        climberTalon = new CANTalon(RobotMap.CLIMBER_TALON);
        climberTalon.enableBrakeMode(false);
    }

    public void setSpeed(double speed) {
        if (DriverStation.getInstance().isFMSAttached())
            if (speed < 0) {
                speed = 0;
                System.out.println("WARNING: Something is trying to reverse the climber. Climber speed set to 0.");
            }
        climberTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        climberTalon.set(speed);
    }

    public void setVoltage(double voltage) {
        if (voltage < 0) {
            voltage = 0;
            System.out.println("WARNING: Something is trying to reverse the climber. Climber voltage set to 0.");
        }
        climberTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
        climberTalon.set(voltage);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopClimber());
    }

}

