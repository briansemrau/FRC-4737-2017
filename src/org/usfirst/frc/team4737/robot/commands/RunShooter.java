package org.usfirst.frc.team4737.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4737.robot.*;
import org.usfirst.frc.team4737.robot.subsystems.Shooter;

/**
 * @author brian
 * @version Feb. 11, 2017
 */
public class RunShooter extends Command {

    private Shooter shooter;

    public RunShooter(Shooter shooter) {
        requires(shooter);
        this.shooter = shooter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.setShooterTargetSpeed(RobotMap.SHOOTING_SPEED);
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
