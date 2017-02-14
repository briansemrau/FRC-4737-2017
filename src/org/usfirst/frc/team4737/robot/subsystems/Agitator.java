package org.usfirst.frc.team4737.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.robot.RobotMap;
import org.usfirst.frc.team4737.robot.commands.StopAgitator;

/**
 * @author brian
 * @version Feb. 13, 2017
 */
public class Agitator extends Subsystem {

    private CANTalon leftTalon;
    private CANTalon rightTalon;

    public Agitator() {
        leftTalon = new CANTalon(RobotMap.AGITATOR_L_TALON);
        rightTalon = new CANTalon(RobotMap.AGITATOR_R_TALON);
        leftTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        rightTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    public void stop() {
        leftTalon.set(0);
        rightTalon.set(0);
    }

    public void setSpeeds(double left, double right) {
        leftTalon.set(left);
        rightTalon.set(right);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopAgitator());
    }

}

