package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.robot.*;
import org.usfirst.frc.team4737.robot.commands.agitator.StopAgitator;

/**
 * @author Brian Semrau
 * @version Feb. 13, 2017
 */
public class Agitator extends Subsystem {

    private CANTalon winMotTalon;

    public Agitator(Robot.Side side) {
        super("Agitator" + side.name);

        side.agitator = this;

        winMotTalon = new CANTalon(side.agitatorID);
        winMotTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

        winMotTalon.setInverted(true); // Inverted no matter the side because of wiring
    }

    public void stop() {
        winMotTalon.set(0);
    }

    public void setSpeed(double speed) {
        winMotTalon.set(speed);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopAgitator(this));
    }

}

