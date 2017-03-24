package org.usfirst.frc.team4737.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4737.robot.subsystems.Shooter;

/**
 * @author Brian Semrau
 * @version Feb. 21, 2017
 */
public class BinarySearchFFTuner extends Command {

    private Shooter shooter;

    public BinarySearchFFTuner(Shooter shooter) {
        super("FFTuner " + shooter.getName());

        this.shooter = shooter;
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        period = SmartDashboard.getNumber("tuningPeriod", 1);
        state = 0;
        lastPeriod = -1;
    }

    private double period;
    private double lastPeriod;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double currentTime = timeSinceInitialized();
        if (currentTime - lastPeriod >= period) {
            lastPeriod = currentTime;
            runPeriodic();
        }
    }

    private int state;

    private double initialError;

    private int n;
    private double output;
    private double coefficient;

    private double low, high, mid;

    private void runPeriodic() {
        double error = shooter.getClosedLoopError();

        shooter.setShooterTargetSpeed(SmartDashboard.getNumber("shooterSpeed", 1000) * 1024);

        SmartDashboard.putNumber("state", state);

        switch (state) {
            case 0:
                shooter.setPIDF(0, 0, 0, 0);
                state++;
                break;
            case 1:
                initialError = shooter.getClosedLoopError();
                coefficient = 1;
                n = -2;
                state++;
            case 2:
                output = Math.pow(2, n) * coefficient;
                n++;
                shooter.setPIDF(0, 0, 0, output);
                double delta = error - initialError;
                if ((delta > 0 && initialError > 0) || (delta < 0 && initialError < 0)) {
                    coefficient = -1;
                    n = 0;
                    break;
                }
                if ((initialError > 0 && error < 0) || (initialError < 0 && error > 0)) {
                    low = 0;
                    high = output;
                    mid = low + (high - low) / 2;
                    output = mid;
                    shooter.setPIDF(0, 0, 0, output);
                    state++;
                }
                break;
            case 3:
                if (error < 0)
                    high = mid;
                if (error > 0)
                    low = mid;
                mid = low + (high - low) / 2;
                output = mid;
                shooter.setPIDF(0, 0, 0, output);
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
