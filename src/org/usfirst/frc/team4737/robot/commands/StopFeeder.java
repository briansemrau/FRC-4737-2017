package org.usfirst.frc.team4737.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4737.robot.subsystems.Feeder;

/**
 * @author brian
 * @version Feb. 14, 2017
 */
public class StopFeeder extends Command {

    private Feeder feeder;

    public StopFeeder(Feeder feeder) {
        requires(feeder);
        this.feeder = feeder;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        feeder.setFeedVoltage(0);
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
