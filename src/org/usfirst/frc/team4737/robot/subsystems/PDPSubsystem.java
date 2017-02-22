package org.usfirst.frc.team4737.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Brian Semrau
 * @version Feb. 22, 2017
 */
public class PDPSubsystem extends Subsystem {

    public final PowerDistributionPanel pdp;

    public PDPSubsystem() {
        pdp = new PowerDistributionPanel();
    }

    public double getClimberCurrent() {
        return pdp.getCurrent(0);
    }

    // TODO add more if needed

    public void initDefaultCommand() {
        setDefaultCommand(null);
    }

}

