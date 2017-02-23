package org.usfirst.frc.team4737.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // ###############
    // Physical Values
    // ###############

    private static final double FEET_PER_METER = 3.280839895;

    public static final double WHEELBASE = 29.425 / 12.0 / FEET_PER_METER;
    public static final double WHEEL_DIAM = 4.0 / 12.0 / FEET_PER_METER;

    public static final double NAVX_DISPLACEMENT_DRIFT_PER_S = 1 / 15.0; // (1 meter / 15 seconds)

    // #####
    // Other
    // #####

    public static final int NAVX_UPDATE_RATE_HZ = 200;

    // ########################
    // Subsystem Configurations
    // ########################

    // Shooter Subsystem
    public static final int SHOOTER_L_TALON = 10;
    public static final int SHOOTER_R_TALON = 20;

    public static final double SHOOTING_SPEED = 3000 /*RPM*/ * 1024 /*Encoder ticks per rev*/; // TODO find value
    public static final double SHOOTING_SPEED_TOLERANCE = 150;

    // Feeder
    public static final int FEEDER_L_TALON = 11;
    public static final int FEEDER_R_TALON = 19;

    public static final double FEEDER_FEED_VOLTAGE = 12;

    // Agitator Subsystem
    public static final int AGITATOR_L_TALON = 12;
    public static final int AGITATOR_R_TALON = 18;

    public static final double AGITATOR_FEED_SPEED = 1;

    // Drive Subsystem
    public static final int DRIVE_LF_TALON = 14;
    public static final int DRIVE_LR_TALON = 13;
    public static final int DRIVE_RF_TALON = 16;
    public static final int DRIVE_RR_TALON = 17;

    public static final int DRIVE_ENC_L_A = 0;
    public static final int DRIVE_ENC_L_B = 1;
    public static final int DRIVE_ENC_R_A = 8;
    public static final int DRIVE_ENC_R_B = 9;
    public static final boolean DRIVE_ENC_L_REV = true; // TODO test
    public static final boolean DRIVE_ENC_R_REV = false;
    public static final double DRIVE_ENC_DIST_PER_PULSE = Math.PI * WHEEL_DIAM / 360; // 360 pps

    public static final double ENCODER_SLIPDETECT_MAX_RATE = 0.01 / 12.0;

    // Intake Subsystem
    public static final int INTAKE_TALON = 15;

    public static final double INTAKE_VOLTAGE = 10;

    // Climber Subsystem
    public static final int CLIMBER_TALON = 21;

    public static final double CLIMBER_FULLSPEED = 1;
    public static final double CLIMBER_REVERSE_SPEED = -0.5;

    // Jetson Subsystem
    public static final int JETSON_POWER_DIO = 2;

}
