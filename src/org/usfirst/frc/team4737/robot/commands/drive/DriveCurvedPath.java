package org.usfirst.frc.team4737.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4737.robot.*;

/**
 * @author Brian Semrau
 * @version Feb. 21, 2017
 */
public class DriveCurvedPath extends Command {

    private double distance;
    private double angle;

    public DriveCurvedPath(double distance, double angle) {
        requires(Robot.DRIVE);
        this.distance = distance;
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.DRIVE.setDistSetpoint(
                distance - 2.0 * Math.PI / 360.0 * angle * RobotMap.WHEELBASE / 2.0,
                distance + 2.0 * Math.PI / 360.0 * angle * RobotMap.WHEELBASE / 2.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.DRIVE.dumbDrive(0, 0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
