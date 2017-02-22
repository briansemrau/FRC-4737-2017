package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.robot.Robot;
import org.usfirst.frc.team4737.robot.commands.feeder.StopFeeder;

/**
 * @author Brian Semrau
 * @version Feb. 14, 2017
 */
public class Feeder extends Subsystem {

    private CANTalon feederTalon;

    public Feeder(Robot.Side side) {
        super("Feeder" + side.name);
        side.feeder = this;

        feederTalon = new CANTalon(side.feederID);
        feederTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
        feederTalon.enableBrakeMode(true);

        feederTalon.setInverted(side.reverse);
    }

    public void setFeedVoltage(double voltage) {
        feederTalon.set(voltage);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopFeeder(this));
    }

}

