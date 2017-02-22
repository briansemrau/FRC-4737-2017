package org.usfirst.frc.team4737.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.robot.RobotMap;

/**
 * @author Brian Semrau
 * @version Feb. 13, 2017
 */
public class JetsonTX1 extends Subsystem {

    // TODO add NetworkTable for comms
    private DigitalOutput powerSwitch;

    public JetsonTX1() {
        super("JetsonTX1");
        powerSwitch = new DigitalOutput(RobotMap.JETSON_POWER_DIO);
    }

    public void setPowerSwitch(boolean active) {
        powerSwitch.set(active);
    }

    // TODO add getters for networktables

    public void initDefaultCommand() {
        setDefaultCommand(null);
    }

}

