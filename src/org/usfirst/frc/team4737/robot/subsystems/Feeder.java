package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.robot.commands.StopFeeder;

/**
 * @author brian
 * @version Feb. 14, 2017
 */
public class Feeder extends Subsystem {

    private CANTalon feederTalon;

    public Feeder(Shooter.Side side) {
        super("Feeder" + (side.reverse ? "L" : "R"));
        side.feeder = this;

        feederTalon = new CANTalon(side.feederID);
        feederTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
        feederTalon.setInverted(side.reverse);
    }

    public void setFeedVoltage(double voltage) {
        feederTalon.set(voltage);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopFeeder(this));
    }

}

