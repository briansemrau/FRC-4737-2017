package org.usfirst.frc.team4737.robot.commands.group;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4737.robot.Robot;
import org.usfirst.frc.team4737.robot.commands.agitator.RunAgitator;
import org.usfirst.frc.team4737.robot.commands.feeder.SmartFeedBalls;
import org.usfirst.frc.team4737.robot.commands.shooter.RunShooter;

/**
 * @author Brian Semrau
 * @version Feb. 15, 2017
 */
public class SingleShoot extends CommandGroup {

    public SingleShoot(Robot.Side side) {
        addParallel(new RunShooter(side.shooter));
        addParallel(new SmartFeedBalls(side));
        addParallel(new RunAgitator(side.agitator));
    }

}
