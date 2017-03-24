package org.usfirst.frc.team4737.robot.commands.camera;

import edu.wpi.first.wpilibj.command.*;
import org.usfirst.frc.team4737.robot.Robot;

/**
 * @author Brian Semrau
 * @version Mar. 03, 2017
 */
public class SwitchCameraView extends Command {

    private boolean finished = false;

    public SwitchCameraView() {
        requires(Robot.CAMERA_DISPLAY);
        setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.CAMERA_DISPLAY.isShowingGearCam()) {
            Scheduler.getInstance().add(new ShowFrontCam());
        } else {
            Scheduler.getInstance().add(new ShowGearCam());
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
