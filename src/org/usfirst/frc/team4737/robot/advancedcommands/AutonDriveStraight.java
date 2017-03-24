package org.usfirst.frc.team4737.robot.advancedcommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.robot.Robot;

/**
 * @author Brian Semrau
 * @version Mar. 03, 2017
 */
public class AutonDriveStraight extends Command {

    private double distance;

    public AutonDriveStraight(double distance) {
        requires(Robot.DRIVE);

        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.DRIVE.setSmoothDrive(200);
//        Robot.DRIVE.setMaxOutput(0.4);
        Robot.DRIVE.resetEncoders();
        Robot.DRIVE.resetNavX();
        Robot.DRIVE.setTargetDist(distance);
//        Robot.DRIVE.setTargetAngle(0); // doesn't work with distance control
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.DRIVE.setMaxOutput(Math.min(timeSinceInitialized(), 0.4));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.DRIVE.distanceOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.DRIVE.setSmoothDrive(0);
        Robot.DRIVE.disableDistancePID();
        Robot.DRIVE.disableAnglePID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

}
