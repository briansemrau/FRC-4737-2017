package org.usfirst.frc.team4737.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Brian Semrau
 * @version Mar. 03, 2017
 */
public class GearHolder extends Subsystem {

    private DigitalInput limitSwitch;

    public GearHolder() {
        limitSwitch = new DigitalInput(7);
    }

    public boolean detectingGear() {
        return limitSwitch.get();
    }

    public void initDefaultCommand() {
        setDefaultCommand(null);
    }

}

