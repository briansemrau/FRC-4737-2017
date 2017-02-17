package org.usfirst.frc.team4737.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4737.robot.*;
import org.usfirst.frc.team4737.robot.subsystems.*;

/**
 * @author brian
 * @version Feb. 15, 2017
 */
public class SmartFeedBalls extends Command {

    private Shooter.Side side;

    public SmartFeedBalls(Shooter.Side side) {
        requires(side.feeder);
        this.side = side;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (side.shooter.readyToShoot()) {
            side.feeder.setFeedVoltage(RobotMap.FEEDER_FEED_VOLTAGE);
        }
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
