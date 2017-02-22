package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.robot.RobotMap;
import org.usfirst.frc.team4737.robot.commands.intake.StopIntake;

/**
 * @author Brian Semrau
 * @version Feb. 13, 2017
 */
public class Intake extends Subsystem {

    private CANTalon talon;

    public Intake() {
        super("Intake");

        talon = new CANTalon(RobotMap.INTAKE_TALON);
        talon.changeControlMode(CANTalon.TalonControlMode.Voltage);
        talon.enableBrakeMode(false);
    }

    public void setVoltage(double voltage) {
        talon.set(voltage);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopIntake());
    }

}

