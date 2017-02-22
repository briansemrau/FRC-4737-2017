
package org.usfirst.frc.team4737.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.lib.FasterIterativeRobot;
import org.usfirst.frc.team4737.robot.commands.ActivateJetson;
import org.usfirst.frc.team4737.robot.commands.drive.MakeRobotPushable;
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

    public enum Side {
        LEFT(RobotMap.SHOOTER_L_TALON, RobotMap.FEEDER_L_TALON, RobotMap.AGITATOR_L_TALON, false, "L"),
        RIGHT(RobotMap.SHOOTER_R_TALON, RobotMap.FEEDER_R_TALON, RobotMap.AGITATOR_R_TALON, true, "R");

        public final int flywheelID, feederID, agitatorID;
        public final boolean reverse;
        public final String name;
        public Shooter shooter;
        public Feeder feeder;
        public Agitator agitator;

        Side(int flywheelID, int feederID, int agitatorID, boolean reverse, String name) {
            this.flywheelID = flywheelID;
            this.feederID = feederID;
            this.agitatorID = agitatorID;
            this.reverse = reverse;
            this.name = name;
        }
    }

    public static final Drive DRIVE = new Drive();
    public static final Intake INTAKE = new Intake();
    public static final Agitator AGITATOR_L = new Agitator(Side.LEFT);
    public static final Agitator AGITATOR_R = new Agitator(Side.RIGHT);
    public static final Shooter SHOOTER_L = new Shooter(Side.LEFT);
    public static final Shooter SHOOTER_R = new Shooter(Side.RIGHT);
    public static final Feeder FEEDER_L = new Feeder(Side.LEFT);
    public static final Feeder FEEDER_R = new Feeder(Side.RIGHT);
    public static final Climber CLIMBER = new Climber();

    public static final JetsonTX1 JETSON_TX1 = new JetsonTX1();

//    Command autonomousCommand;
//    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        OI = new OI();

        Scheduler.getInstance().add(new ActivateJetson());

//        chooser = new SendableChooser();
//        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
//        SmartDashboard.putData("Auto mode", chooser);
    }

    private double lastSecond = Timer.getFPGATimestamp();
    private int count = 0;

    @Override
    public void robotPeriodic() {
        count++;
        double currentTime = Timer.getFPGATimestamp();
        if (currentTime - lastSecond > 1) {
            SmartDashboard.putNumber("UPS", count);
            count = 0;
            lastSecond = currentTime;
        }

//        selectedShooter = shooterTuningChooser.getSelected();

        SHOOTER_L.getSmartDashboardPIDFvals();
//        SmartDashboard.putString("shooterLClosedLoopError", "" +
//                SHOOTER_L.getClosedLoopError()
//        );
//        SmartDashboard.putString("shooterLTargetVSSpeed", "" +
//                SHOOTER_L.getTarget() + ":" +
//                SHOOTER_L.getSpeed()
//        );
        SmartDashboard.putNumber("shooterLClosedLoopError", SHOOTER_L.getClosedLoopError());
        SmartDashboard.putNumber("shooterLVoltage", SHOOTER_L.getVoltageOutput());

        SHOOTER_R.getSmartDashboardPIDFvals();
//        SmartDashboard.putString("shooterRClosedLoopError", "" +
//                SHOOTER_R.getClosedLoopError()
//        );
//        SmartDashboard.putString("shooterRTargetVSSpeed", "" +
//                SHOOTER_R.getTarget() + ":" +
//                SHOOTER_R.getSpeed()
//        );
        SmartDashboard.putNumber("shooterRClosedLoopError", SHOOTER_R.getClosedLoopError());
        SmartDashboard.putNumber("shooterRVoltage", SHOOTER_R.getVoltageOutput());

        SmartDashboard.putBoolean("shooterLOnTarget", SHOOTER_L.readyToShoot());
        SmartDashboard.putBoolean("shooterROnTarget", SHOOTER_R.readyToShoot());

//        StringBuilder sb = new StringBuilder(100);
//        for (int channel = 0; channel < 16; channel++) {
//            if (channel >= 6 && channel <= 9) continue;
//
//            sb.append(PDP.getCurrent(channel));
//            if (channel < 15)
//                sb.append(":");
//        }
//        SmartDashboard.putString("currents", sb.toString());
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit() {
        new MakeRobotPushable().start();
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
