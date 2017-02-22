package org.usfirst.frc.team4737.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4737.robot.Robot;

/**
 * @author Brian Semrau
 * @version Feb. 13, 2017
 */
public class TeleopDrive extends Command {

    public TeleopDrive() {
        requires(Robot.DRIVE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.DRIVE.dumbDrive(-Robot.OI.controller.LS.X.get(), Robot.OI.controller.RT.get() - Robot.OI.controller.LT.get(), true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
