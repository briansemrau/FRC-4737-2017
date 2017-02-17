package org.usfirst.frc.team4737.robot.commands.group;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4737.robot.commands.*;
import org.usfirst.frc.team4737.robot.subsystems.Shooter;

/**
 * @author brian
 * @version Feb. 15, 2017
 */
public class SingleShoot extends CommandGroup {

    public SingleShoot(Shooter.Side side) {
        addParallel(new RunShooter(side.shooter));
        addParallel(new SmartFeedBalls(side));
        addParallel(new RunAgitator());
    }

}
