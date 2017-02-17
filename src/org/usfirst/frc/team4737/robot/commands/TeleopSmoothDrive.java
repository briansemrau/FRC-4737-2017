package org.usfirst.frc.team4737.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.robot.Robot;

/**
 * @author brian
 * @version Feb. 13, 2017
 */
public class TeleopSmoothDrive extends Command {

    private double moveValue = 0;
    private double rotateValue = 0;
    private double responseRamp = 0.2;
    private double lastTime = 0;

    public TeleopSmoothDrive() {
        requires(Robot.DRIVE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        lastTime = Timer.getFPGATimestamp();
    }

    int counter = 0;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double time = Timer.getFPGATimestamp();
        double delta = time - lastTime;
        lastTime = time;

        if (delta > 0.5) delta = 0; // In case there is ever a large gap in time, don't want the robot to freak out

        double moveValue = Robot.OI.controller.RT.get() - Robot.OI.controller.LT.get();
        double rotateValue = -Robot.OI.controller.LS.X.get();

//        this.moveValue = this.moveValue * (1 - responseRamp * delta) + moveValue * responseRamp * delta;
//        this.rotateValue = this.rotateValue * (1 - responseRamp * delta) + rotateValue * responseRamp * delta;

        if (moveValue > this.moveValue) {
            double diff = Math.abs(moveValue - this.moveValue);
            if (diff < responseRamp * delta)
                this.moveValue += diff;
            else
                this.moveValue += responseRamp * delta;
        }
        if (moveValue < this.moveValue) {
            double diff = Math.abs(moveValue - this.moveValue);
            if (diff < responseRamp * delta)
                this.moveValue -= diff;
            else
                this.moveValue -= responseRamp * delta;
        }

        if (rotateValue > this.rotateValue) {
            double diff = Math.abs(rotateValue - this.rotateValue);
            if (diff < responseRamp * delta)
                this.rotateValue += diff;
            else
                this.rotateValue += responseRamp * delta;
        }
        if (rotateValue < this.rotateValue) {
            double diff = Math.abs(rotateValue - this.rotateValue);
            if (diff < responseRamp * delta)
                this.rotateValue -= diff;
            else
                this.rotateValue -= responseRamp * delta;
        }

//        counter++;
//        if (counter >= 50) {
//            System.out.println(moveValue + ", " + rotateValue + " : " + this.moveValue + ", " + this.rotateValue);
//            counter = 0;
//        }

        Robot.DRIVE.dumbDrive(this.moveValue, this.rotateValue, true);
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
