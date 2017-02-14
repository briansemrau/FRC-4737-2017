package org.usfirst.frc.team4737.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4737.robot.Robot;

/**
 * @author brian
 * @version Feb. 13, 2017
 */
public class ActivateJetson extends Command {

    public ActivateJetson() {
        requires(Robot.JETSON_TX1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.JETSON_TX1.setPowerSwitch(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized() > 0.350;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.JETSON_TX1.setPowerSwitch(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
