package org.usfirst.frc.team4737.robot.commands.group;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team4737.robot.commands.*;
import org.usfirst.frc.team4737.robot.subsystems.Shooter;

/**
 * @author brian
 * @version Feb. 15, 2017
 */
public class DoubleShoot extends CommandGroup {

    public DoubleShoot() {
        addParallel(new RunShooter(Shooter.Side.LEFT.shooter));
        addParallel(new SmartFeedBalls(Shooter.Side.LEFT));
        addParallel(new RunShooter(Shooter.Side.RIGHT.shooter));
        addParallel(new SmartFeedBalls(Shooter.Side.RIGHT));
        addParallel(new RunAgitator());
    }

}
