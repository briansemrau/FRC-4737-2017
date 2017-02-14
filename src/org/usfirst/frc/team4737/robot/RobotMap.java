package org.usfirst.frc.team4737.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // TODO place values under their labeled categories

    // ###############
    // Physical Values
    // ###############

    private static final double FEET_PER_METER = 3.280839895;

    public static final double WHEELBASE = 29.425 / 12.0 / FEET_PER_METER;
    public static final double WHEEL_DIAM = 4.0 / 12.0 / FEET_PER_METER;

    public static final double NAVX_DISPLACEMENT_DRIFT_PER_S = 1 / 15.0; // (1 meter / 15 seconds)
    public static final double ENCODER_SLIPDETECT_MAX_RATE = 0.01 / 12.0;

    // #####
    // Other
    // #####

    public static final int NAVX_UPDATE_RATE_HZ = 200;

    // ###################
    // Speed/Tuning Values
    // ###################

    public static final double SHOOTING_SPEED = 800; // TODO find value
    public static final double INTAKE_VOLTAGE = 12; // TODO figure out what speed
    public static final double AGITATOR_FEED_SPEED = 1;
    public static final double AGITATOR_RUSTLE_SPEED = 0.5;

    // ############################
    // Talon IDs and polarities, IO
    // ############################

    // Shooter Subsystem
    public static final int SHOOTER_L_TALON = 10;
    public static final int SHOOTER_R_TALON = 20;
    public static final int FEEDER_L_TALON = 11;
    public static final int FEEDER_R_TALON = 19;

    // Agitator Subsystem
    public static final int AGITATOR_L_TALON = 12;
    public static final int AGITATOR_R_TALON = 18;

    // Drive Subsystem
    public static final int DRIVE_LF_TALON = 14;
    public static final int DRIVE_LR_TALON = 13;
    public static final int DRIVE_RF_TALON = 16;
    public static final int DRIVE_RR_TALON = 17;

    public static final int DRIVE_ENC_L_A = -1;
    public static final int DRIVE_ENC_L_B = -1;
    public static final int DRIVE_ENC_R_A = -1;
    public static final int DRIVE_ENC_R_B = -1;
    public static final boolean DRIVE_ENC_L_REV = true; // TODO test
    public static final boolean DRIVE_ENC_R_REV = false;
    public static final double DRIVE_ENC_DIST_PER_PULSE = Math.PI * WHEEL_DIAM / 360; // 360 pps

    // Intake Subsystem
    public static final int INTAKE_TALON = 15;

    // Climber Subsystem
    public static final int CLIMBER_TALON = 21;

    // Jetson Subsystem

    public static final int JETSON_POWER_DIO = 0;

}
