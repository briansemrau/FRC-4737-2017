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
public class DoubleShoot extends CommandGroup {

    public DoubleShoot() {
        addParallel(new RunShooter(Robot.Side.LEFT.shooter));
        addParallel(new SmartFeedBalls(Robot.Side.LEFT));
        addParallel(new RunShooter(Robot.Side.RIGHT.shooter));
        addParallel(new SmartFeedBalls(Robot.Side.RIGHT));
        addParallel(new RunAgitator(Robot.AGITATOR_L));
        addParallel(new RunAgitator(Robot.AGITATOR_R));
    }

}
