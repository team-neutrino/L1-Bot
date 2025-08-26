// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import frc.robot.util.generated.TunerConstants;

public final class Constants {

    public static class OperatorConstants {
        public static final int kDriversControllerPort = 0;
        public static final int kButtonsControllerPort = 1;
    }

    public static class IntakeConstants {
        public static final int INTAKE_MOTOR_ID = 2;
        public static final int INTAKE_CURRENT_LIMIT = 40;
        public static final double INTAKE_kP = 0.0;
        public static final double INTAKE_kI = 0.0;
        public static final double INTAKE_kD = 0.0;
        public static final int PROXIMITY_SENSOR = 4;
        public static final double PROXIMITY = 0.055;
        public static final double INTAKE_VOLTAGE = 0.5;
        public static final double OUTTAKE_VOLTAGE = -0.5;
    }

    public static class ArmConstants {
        public static final int ARM_MOTOR_ID = 3;
        public static final int ARM_CURRENT_LIMIT = 40;
        public static final double ARM_kP = 0.0;
        public static final double ARM_kI = 0.0;
        public static final double ARM_kD = 0.0;
        public static final double INTAKE_POSITION = 20.0;
        public static final double SCORE_POSITION = 240.0;
        public static final double FFCONSTANT = 0.05;
        public static final double ALLOWED_ERROR = 0.5;
        public static final double GAIN_THRESHOLD = 6.0;
        public static final double START_POSITION = 270.0;
        public static final double TOLERANCE = 2.0;
    }

    public static class SwerveConstants {
        public static final double MAX_SPEED = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond);
        public static final double MAX_ANGULAR_RATE = RotationsPerSecond.of(0.75).in(RadiansPerSecond);
    }

    public static class LimelightConstants {
        public static final String REEF_CAMERA = "limelight-lemon";
        public static final double CAMERA_FORWARD_OFFSET = 0;
        public static final double CAMERA_HEIGHT_OFFSET = 0;
        public static final double CAMERA_PITCH_OFFSET = 0;
        public static final double CAMERA_ROLL_OFFSET = 0;
        public static final double CAMERA_SIDE_OFFSET = 0;
        public static final double CAMERA_YAW_OFFSET = 0;
    }
}
