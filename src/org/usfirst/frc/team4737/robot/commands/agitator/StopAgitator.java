package org.usfirst.frc.team4737.robot.commands.agitator;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4737.robot.subsystems.Agitator;

/**
 * @author Brian Semrau
 * @version Feb. 13, 2017
 */
public class StopAgitator extends Command {

    private Agitator agitator;

    public StopAgitator(Agitator agitator) {
        requires(agitator);
        this.agitator = agitator;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        agitator.stop();
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
