package org.usfirst.frc.team4737.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team4737.lib.XboxController;

/**
 * @author Brian Semrau
 * @version 2/11/2017
 */
public class JoystickAxisTrigger extends Trigger {

    private XboxController.Axis[] axis;

    public JoystickAxisTrigger(XboxController.Axis... axis) {
        this.axis = axis;
    }

    @Override
    public boolean get() {
        for (XboxController.Axis axis : axis)
            if (axis.get() != 0) return true;
        return false;
    }

}
