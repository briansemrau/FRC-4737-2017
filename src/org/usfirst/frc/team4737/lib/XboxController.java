package org.usfirst.frc.team4737.lib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * @author Brian Semrau
 * @version 12/15/2016
 */
public class XboxController {

    public class Axis {
        private final Joystick controller;
        private final int axis;

        private double deadzone;

        public Axis(Joystick controller, int axis) {
            this.controller = controller;
            this.axis = axis;
        }

        public void setDeadzone(double radius) {
            if (radius < 0) throw new IllegalArgumentException("Deadzone cannot be less than 0.");
            this.deadzone = radius;
        }

        public double getRaw() {
            return controller.getRawAxis(axis);
        }

        public double get() {
            double raw = getRaw();
            if (raw < -deadzone) {
                return scale(raw, -1, -deadzone, -1, 0);
            } else if (raw > deadzone) {
                return scale(raw, deadzone, 1, 0, 1);
            } else {
                return 0;
            }
        }

        private double scale(double val, double valLow, double valHigh, double newLow, double newHigh) {
            double reduced = (val - valLow) / (valHigh - valLow);
            return reduced * (newHigh - newLow) + newLow;
        }
    }

    public class Thumbstick {
        public final Axis X, Y;

        public Thumbstick(Joystick controller, int axisX, int axisY) {
            X = new Axis(controller, axisX);
            Y = new Axis(controller, axisY);
        }
    }

    public final Joystick controller;
    public final JoystickButton A;
    public final JoystickButton B;
    public final JoystickButton X;
    public final JoystickButton Y;
    public final JoystickButton LB;
    public final JoystickButton RB;
    public final JoystickButton SELECT;
    public final JoystickButton START;
    public final JoystickButton L3;
    public final JoystickButton R3;
    public final Thumbstick LS;
    public final Thumbstick RS;
    public final Axis LT;
    public final Axis RT;

    public XboxController(int port) {
        controller = new Joystick(port);
        A = new JoystickButton(controller, 1);
        B = new JoystickButton(controller, 2);
        X = new JoystickButton(controller, 3);
        Y = new JoystickButton(controller, 4);
        LB = new JoystickButton(controller, 5);
        RB = new JoystickButton(controller, 6);
        SELECT = new JoystickButton(controller, 7);
        START = new JoystickButton(controller, 8);
        L3 = new JoystickButton(controller, 9);
        R3 = new JoystickButton(controller, 10);
        LS = new Thumbstick(controller, 0, 1);
        LT = new Axis(controller, 2);
        RT = new Axis(controller, 3);
        RS = new Thumbstick(controller, 4, 5);
    }

    public void rumble(double left, double right) {
        controller.setRumble(Joystick.RumbleType.kLeftRumble, (float) left);
        controller.setRumble(Joystick.RumbleType.kRightRumble, (float) right);
    }

    public void rumble(double rumble) {
        rumble(rumble, rumble);
    }

}
