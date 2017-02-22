package org.usfirst.frc.team4737.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.lib.XboxController;
import org.usfirst.frc.team4737.robot.commands.*;
import org.usfirst.frc.team4737.robot.commands.drive.TeleopDrive;
import org.usfirst.frc.team4737.robot.commands.group.SingleShoot;
import org.usfirst.frc.team4737.robot.commands.tuning.BinarySearchFFTuner;
import org.usfirst.frc.team4737.robot.subsystems.*;
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
        SmartDashboard.putData(new BinarySearchFFTuner(Robot.SHOOTER_L));
        SmartDashboard.putData(new BinarySearchFFTuner(Robot.SHOOTER_R));

        // Shooters
        controller.LB.whileActive(new RunShooter(Robot.SHOOTER_L));
        controller.RB.whileActive(new RunShooter(Robot.SHOOTER_R));

        controller.START.whileActive(new SingleShoot(Shooter.Side.RIGHT));
        controller.SELECT.whileActive(new SingleShoot(Shooter.Side.LEFT));

        // Intake
        controller.X.whenPressed(new RunIntake());
        controller.X.whenReleased(new StopIntake());

        // Feeders and Agitator
        controller.B.whenPressed(new RunFeeder(Robot.FEEDER_L));
        controller.B.whenPressed(new RunFeeder(Robot.FEEDER_R));
        controller.B.whenPressed(new RunAgitator());

        controller.B.whenReleased(new StopFeeder(Robot.FEEDER_L));
        controller.B.whenReleased(new StopFeeder(Robot.FEEDER_R));
        controller.B.whenReleased(new StopAgitator());

        // Climber
        controller.Y.whenPressed(new ClimberFullspeed());
        controller.Y.whenReleased(new StopClimber());

        // Drive
        drivingTrigger.whenActive(new TeleopDrive());
    }

}

