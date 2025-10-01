// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import java.util.List;
import java.util.Optional;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.commands.DriveToPoint;
import frc.robot.util.generated.TunerConstants;

public final class Constants {
    public static class GlobalConstants {
        public static Optional<Boolean> RED_ALLIANCE = Optional.empty();
    }

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
        public static final double INTAKE_VOLTAGE = 1.0;
        public static final double OUTTAKE_VOLTAGE = -0.45;
        public static final double SOFT_OUTTAKE_VOLTAGE = -0.2;
    }

    public static class ArmConstants {
        public static final int ARM_MOTOR_ID = 3;
        public static final int ARM_CURRENT_LIMIT = 40;
        public static final double SENSOR_TO_MECHANISM_RATIO = 0.228;
        public static final double ARM_kP = 0.27;
        public static final double ARM_kI = 0.0;
        public static final double ARM_kD = 0.0;
        public static final double INTAKE_POSITION = 239;
        public static final double FRONT_SCORE_POSITION = 16.0;
        public static final double BACK_SCORE_POSITION = 170.0;
        public static final double FFCONSTANT = 0.25;
        public static final double ALLOWED_ERROR = 0.5;
        public static final double GAIN_THRESHOLD = 6.0;
        public static final double START_POSITION = -7.0;
        public static final double DEFAULT_POSITION = 16.0;
        public static final double TOLERANCE = 2.0;
    }

    public static class SwerveConstants {
        public static final double MAX_SPEED = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond);
        public static final double MAX_ANGULAR_RATE = RotationsPerSecond.of(0.75).in(RadiansPerSecond);
    }

    public static class LimelightConstants {
        public static final String FRONT_REEF_CAMERA = "limelight-lemon";
        public static final String BACK_REEF_CAMERA = "limelight-vienna";
        public static final double FRONT_CAMERA_FORWARD_OFFSET = 0.236;
        public static final double FRONT_CAMERA_HEIGHT_OFFSET = 0.316;
        public static final double FRONT_CAMERA_SIDE_OFFSET = -0.143;
        public static final double FRONT_CAMERA_PITCH_OFFSET = 11.1;
        public static final double FRONT_CAMERA_ROLL_OFFSET = 0;
        public static final double FRONT_CAMERA_YAW_OFFSET = -14.3;
        public static final double BACK_CAMERA_FORWARD_OFFSET = 0.229;
        public static final double BACK_CAMERA_HEIGHT_OFFSET = 0.222;
        public static final double BACK_CAMERA_SIDE_OFFSET = 0;
        public static final double BACK_CAMERA_PITCH_OFFSET = 0.13;
        public static final double BACK_CAMERA_ROLL_OFFSET = 0;
        public static final double BACK_CAMERA_YAW_OFFSET = 0;
    }

    public static class DriveToPointConstants {
        public static final AprilTagFieldLayout FIELD_LAYOUT = AprilTagFieldLayout
                .loadField(AprilTagFields.k2025ReefscapeAndyMark);

        public static final double AT_POINT_TOLERANCE = 0.04;

        public static final double PARALLEL_OFFSET = 0.13;
        public static final double PERPENDICULAR_OFFSET = 0.73;
        public static final double REEF_WIDTH = 0.33;
        public static final double DRIVE_TO_POINT_P = 4.0;
        public static final double DRIVE_TO_POINT_I = 0.0;
        public static final double DRIVE_TO_POINT_D = 0.0;
        public static final double MAX_DRIVETOPOINT_SPEED = 5;

        public static final Pose2d RED_REEF_6A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(6).get(), true);
        public static final Pose2d RED_REEF_6B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(6).get(), false);
        public static final Pose2d RED_REEF_7A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(7).get(), true);
        public static final Pose2d RED_REEF_7B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(7).get(), false);
        public static final Pose2d RED_REEF_8A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(8).get(), true);
        public static final Pose2d RED_REEF_8B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(8).get(), false);
        public static final Pose2d RED_REEF_9A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(9).get(), true);
        public static final Pose2d RED_REEF_9B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(9).get(), false);
        public static final Pose2d RED_REEF_10A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(10).get(), true);
        public static final Pose2d RED_REEF_10B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(10).get(), false);
        public static final Pose2d RED_REEF_11A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(11).get(), true);
        public static final Pose2d RED_REEF_11B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(11).get(), false);

        public static final Pose2d BLUE_REEF_17A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(17).get(),
                false);
        public static final Pose2d BLUE_REEF_17B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(17).get(), true);
        public static final Pose2d BLUE_REEF_18A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(18).get(),
                false);
        public static final Pose2d BLUE_REEF_18B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(18).get(), true);
        public static final Pose2d BLUE_REEF_19A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(19).get(),
                false);
        public static final Pose2d BLUE_REEF_19B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(19).get(), true);
        public static final Pose2d BLUE_REEF_20A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(20).get(),
                false);
        public static final Pose2d BLUE_REEF_20B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(20).get(), true);
        public static final Pose2d BLUE_REEF_21A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(21).get(),
                false);
        public static final Pose2d BLUE_REEF_21B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(21).get(), true);
        public static final Pose2d BLUE_REEF_22A = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(22).get(),
                false);
        public static final Pose2d BLUE_REEF_22B = DriveToPoint.calculatePoint(FIELD_LAYOUT.getTagPose(22).get(), true);

        public static final List<Pose2d> TARGET_POSES = List.of(
                RED_REEF_6A, RED_REEF_6B, RED_REEF_7A, RED_REEF_7B, RED_REEF_8A, RED_REEF_8B, RED_REEF_9A, RED_REEF_9B,
                RED_REEF_10A, RED_REEF_10B, RED_REEF_11A, RED_REEF_11B, BLUE_REEF_17A, BLUE_REEF_17B,
                BLUE_REEF_18A, BLUE_REEF_18B, BLUE_REEF_19A, BLUE_REEF_19B, BLUE_REEF_20A,
                BLUE_REEF_20B, BLUE_REEF_21A, BLUE_REEF_21B, BLUE_REEF_22A, BLUE_REEF_22B);
        public static final List<Pose2d> RED_REEF = List.of(RED_REEF_6A, RED_REEF_6B, RED_REEF_7A, RED_REEF_7B,
                RED_REEF_8A, RED_REEF_8B, RED_REEF_9A, RED_REEF_9B,
                RED_REEF_10A, RED_REEF_10B, RED_REEF_11A, RED_REEF_11B);
        public static final List<Pose2d> BLUE_REEF = List.of(BLUE_REEF_17A, BLUE_REEF_17B,
                BLUE_REEF_18A, BLUE_REEF_18B, BLUE_REEF_19A, BLUE_REEF_19B, BLUE_REEF_20A,
                BLUE_REEF_20B, BLUE_REEF_21A, BLUE_REEF_21B, BLUE_REEF_22A, BLUE_REEF_22B);
    }

    public static class AprilTagConstants {
        public final class RED_ALLIANCE_IDS {
            public static final int SOURCE = 1;
            public static final int SOURCE_PROCESSOR_SIDE = 2;
            public static final int PROCESSSOR = 3;
            public static final int REEF_FACING_SOURCE = 6;
            public static final int REEF_FACING_ALLIANCE = 7;
            public static final int REEF_FACING_SOURCE_PROCESSOR_SIDE = 8;
            public static final int REEF_FACING_PROCESSOR = 9;
            public static final int REEF_FACING_BARGE = 10;
            public static final int REEF_FACING_CAGES = 11;
        }

        public final class BLUE_ALLIANCE_IDS {
            public static final int SOURCE = 13;
            public static final int SOURCE_PROCESSOR_SIDE = 12;
            public static final int PROCESSSOR = 16;
            public static final int REEF_FACING_SOURCE = 19;
            public static final int REEF_FACING_ALLIANCE = 18;
            public static final int REEF_FACING_SOURCE_PROCESSOR_SIDE = 17;
            public static final int REEF_FACING_PROCESSOR = 22;
            public static final int REEF_FACING_BARGE = 21;
            public static final int REEF_FACING_CAGES = 20;
        }

    }
}
