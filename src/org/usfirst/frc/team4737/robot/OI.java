package org.usfirst.frc.team4737.robot;

import org.usfirst.frc.team4737.lib.XboxController;
import org.usfirst.frc.team4737.robot.commands.*;
import org.usfirst.frc.team4737.robot.subsystems.RunIntake;
import org.usfirst.frc.team4737.robot.triggers.JoystickAxisTrigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public final XboxController controller;
    private final JoystickAxisTrigger drivingTrigger;

    public OI() {
        controller = new XboxController(0);
        drivingTrigger = new JoystickAxisTrigger(controller.LS.X, controller.RT, controller.LT);

        controller.X.whenPressed(new RunShooter(Robot.SHOOTER_L));
        controller.X.whenReleased(new StopShooter(Robot.SHOOTER_L));

        controller.RB.whenPressed(new RunIntake());
        controller.RB.whenReleased(new StopIntake());

        drivingTrigger.whenActive(new TeleopDrive());
    }

}

