package org.usfirst.frc.team4737.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.lib.XboxController;
import org.usfirst.frc.team4737.robot.commands.*;
import org.usfirst.frc.team4737.robot.commands.group.DoubleShoot;
import org.usfirst.frc.team4737.robot.subsystems.RunIntake;
import org.usfirst.frc.team4737.robot.triggers.JoystickAxisTrigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public final XboxController controller;

    public OI() {
        controller = new XboxController(0);
        JoystickAxisTrigger drivingTrigger = new JoystickAxisTrigger(controller.LS.X, controller.RT, controller.LT);

        SmartDashboard.putData(new ReverseClimber());

        controller.RB.whileActive(new RunShooter(Robot.SHOOTER_L));
        controller.RB.whileActive(new RunShooter(Robot.SHOOTER_R));

        controller.X.whenPressed(new RunIntake());
        controller.X.whenReleased(new StopIntake());

        controller.B.whenPressed(new RunFeeder(Robot.FEEDER_L));
        controller.B.whenPressed(new RunFeeder(Robot.FEEDER_R));
        controller.B.whenPressed(new RunAgitator());

        controller.B.whenReleased(new StopFeeder(Robot.FEEDER_L));
        controller.B.whenReleased(new StopFeeder(Robot.FEEDER_R));
        controller.B.whenReleased(new StopAgitator());

        controller.Y.whenPressed(new ClimberFullspeed());
        controller.Y.whenReleased(new StopClimber());

        drivingTrigger.whenActive(new TeleopDrive());
    }

}

