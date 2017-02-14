
package org.usfirst.frc.team4737.robot;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.lib.FasterIterativeRobot;
import org.usfirst.frc.team4737.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends FasterIterativeRobot {

    public static OI OI;

    public static final Drive DRIVE = new Drive();
    public static final Intake INTAKE = new Intake();
    public static final Agitator AGITATOR = new Agitator();
    public static final Shooter SHOOTER_L = new Shooter(Shooter.Side.LEFT);
    public static final Shooter SHOOTER_R = new Shooter(Shooter.Side.RIGHT);
//    public static final Climber CLIMBER = new Climber();

    public static final JetsonTX1 JETSON_TX1 = new JetsonTX1();

//    Command autonomousCommand;
//    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        OI = new OI();

//        chooser = new SendableChooser();
//        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
//        SmartDashboard.putData("Auto mode", chooser);
    }

    @Override
    public void robotPeriodic() {
        Robot.SHOOTER_L.getSmartDashboardPIDFvals();
        SmartDashboard.putString("shooterTalon", "" +
                SHOOTER_L.getTarget() + ":" +
                SHOOTER_L.getSpeed() + ":" +
                SHOOTER_L.getClosedLoopError() + ":" +
                SHOOTER_L.getVPercent()
        );
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit() {
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes using
     * the dashboard. The sendable chooser code works with the Java SmartDashboard.
     * <p>
     * You can add additional auto modes by adding additional commands to the chooser code above (like the commented
     * example) or additional comparisons to the switch structure below with additional strings & commands.
     */
    public void autonomousInit() {
//        autonomousCommand = (Command) chooser.getSelected();

        // schedule the autonomous command (example)
//        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.

//        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

}
