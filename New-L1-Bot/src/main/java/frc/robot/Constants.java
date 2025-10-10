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
                public static final double SLOW_SPEED = 1;
                public static final double SLOW_ANGULAR_RATE = 0.8;
        }

        public static class LimelightConstants {
                public static final String FRONT_REEF_CAMERA = "limelight-lemon";
                public static final String BACK_REEF_CAMERA = "limelight-vienna";
                public static final double FRONT_CAMERA_FORWARD_OFFSET = -0.1385;
                public static final double FRONT_CAMERA_HEIGHT_OFFSET = 0.324;
                public static final double FRONT_CAMERA_SIDE_OFFSET = -0.1955;
                public static final double FRONT_CAMERA_PITCH_OFFSET = 11.1;
                public static final double FRONT_CAMERA_ROLL_OFFSET = 0;
                public static final double FRONT_CAMERA_YAW_OFFSET = -14.3;
                public static final double BACK_CAMERA_FORWARD_OFFSET = -0.229;
                public static final double BACK_CAMERA_HEIGHT_OFFSET = 0.222;
                public static final double BACK_CAMERA_SIDE_OFFSET = 0;
                public static final double BACK_CAMERA_PITCH_OFFSET = 5;
                public static final double BACK_CAMERA_ROLL_OFFSET = 0;
                public static final double BACK_CAMERA_YAW_OFFSET = 180;
        }

        public static class DriveToPointConstants {
                public static final AprilTagFieldLayout FIELD_LAYOUT = AprilTagFieldLayout
                                .loadField(AprilTagFields.k2025ReefscapeAndyMark);

                public static final double AT_POINT_TOLERANCE = 0.04;

                public static final double PARALLEL_OFFSET = 0.0; // Offsets points A and B in one direction
                public static final double FRONT_PERPENDICULAR_OFFSET = 0.3;
                public static final double BACK_PERPENDICULAR_OFFSET = 0.8;
                public static final double DISTANCE_BETWEEN_POINTS = 0.22; // Offsets points A and B in opposite
                                                                           // directions
                public static final double DRIVE_TO_POINT_P = 4.0;
                public static final double DRIVE_TO_POINT_I = 0.0;
                public static final double DRIVE_TO_POINT_D = 0.0;
                public static final double MAX_DRIVETOPOINT_SPEED = 5;

                public static final Pose2d FRONT_RED_REEF_6A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(6).get(),
                                true);
                public static final Pose2d FRONT_RED_REEF_6B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(6).get(),
                                false);
                public static final Pose2d FRONT_RED_REEF_7A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(7).get(),
                                true);
                public static final Pose2d FRONT_RED_REEF_7B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(7).get(),
                                false);
                public static final Pose2d FRONT_RED_REEF_8A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(8).get(),
                                true);
                public static final Pose2d FRONT_RED_REEF_8B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(8).get(),
                                false);
                public static final Pose2d FRONT_RED_REEF_9A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(9).get(),
                                true);
                public static final Pose2d FRONT_RED_REEF_9B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(9).get(),
                                false);
                public static final Pose2d FRONT_RED_REEF_10A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(10).get(),
                                true);
                public static final Pose2d FRONT_RED_REEF_10B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(10).get(),
                                false);
                public static final Pose2d FRONT_RED_REEF_11A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(11).get(),
                                true);
                public static final Pose2d FRONT_RED_REEF_11B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(11).get(),
                                false);

                public static final Pose2d FRONT_BLUE_REEF_17A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(17).get(),
                                false);
                public static final Pose2d FRONT_BLUE_REEF_17B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(17).get(),
                                true);
                public static final Pose2d FRONT_BLUE_REEF_18A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(18).get(),
                                false);
                public static final Pose2d FRONT_BLUE_REEF_18B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(18).get(),
                                true);
                public static final Pose2d FRONT_BLUE_REEF_19A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(19).get(),
                                false);
                public static final Pose2d FRONT_BLUE_REEF_19B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(19).get(),
                                true);
                public static final Pose2d FRONT_BLUE_REEF_20A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(20).get(),
                                false);
                public static final Pose2d FRONT_BLUE_REEF_20B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(20).get(),
                                true);
                public static final Pose2d FRONT_BLUE_REEF_21A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(21).get(),
                                false);
                public static final Pose2d FRONT_BLUE_REEF_21B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(21).get(),
                                true);
                public static final Pose2d FRONT_BLUE_REEF_22A = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(22).get(),
                                false);
                public static final Pose2d FRONT_BLUE_REEF_22B = DriveToPoint.frontCalculatePoint(
                                FIELD_LAYOUT.getTagPose(22).get(),
                                true);

                public static final Pose2d BACK_RED_REEF_6A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(6).get(),
                                true);
                public static final Pose2d BACK_RED_REEF_6B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(6).get(),
                                false);
                public static final Pose2d BACK_RED_REEF_7A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(7).get(),
                                true);
                public static final Pose2d BACK_RED_REEF_7B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(7).get(),
                                false);
                public static final Pose2d BACK_RED_REEF_8A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(8).get(),
                                true);
                public static final Pose2d BACK_RED_REEF_8B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(8).get(),
                                false);
                public static final Pose2d BACK_RED_REEF_9A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(9).get(),
                                true);
                public static final Pose2d BACK_RED_REEF_9B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(9).get(),
                                false);
                public static final Pose2d BACK_RED_REEF_10A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(10).get(),
                                true);
                public static final Pose2d BACK_RED_REEF_10B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(10).get(),
                                false);
                public static final Pose2d BACK_RED_REEF_11A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(11).get(),
                                true);
                public static final Pose2d BACK_RED_REEF_11B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(11).get(),
                                false);

                public static final Pose2d BACK_BLUE_REEF_17A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(17).get(),
                                false);
                public static final Pose2d BACK_BLUE_REEF_17B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(17).get(),
                                true);
                public static final Pose2d BACK_BLUE_REEF_18A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(18).get(),
                                false);
                public static final Pose2d BACK_BLUE_REEF_18B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(18).get(),
                                true);
                public static final Pose2d BACK_BLUE_REEF_19A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(19).get(),
                                false);
                public static final Pose2d BACK_BLUE_REEF_19B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(19).get(),
                                true);
                public static final Pose2d BACK_BLUE_REEF_20A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(20).get(),
                                false);
                public static final Pose2d BACK_BLUE_REEF_20B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(20).get(),
                                true);
                public static final Pose2d BACK_BLUE_REEF_21A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(21).get(),
                                false);
                public static final Pose2d BACK_BLUE_REEF_21B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(21).get(),
                                true);
                public static final Pose2d BACK_BLUE_REEF_22A = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(22).get(),
                                false);
                public static final Pose2d BACK_BLUE_REEF_22B = DriveToPoint.backCalculatePoint(
                                FIELD_LAYOUT.getTagPose(22).get(),
                                true);

                public static final List<Pose2d> FRONT_RED_REEF = List.of(FRONT_RED_REEF_6A, FRONT_RED_REEF_6B,
                                FRONT_RED_REEF_7A, FRONT_RED_REEF_7B,
                                FRONT_RED_REEF_8A, FRONT_RED_REEF_8B, FRONT_RED_REEF_9A, FRONT_RED_REEF_9B,
                                FRONT_RED_REEF_10A, FRONT_RED_REEF_10B, FRONT_RED_REEF_11A, FRONT_RED_REEF_11B);

                public static final List<Pose2d> BACK_RED_REEF = List.of(BACK_RED_REEF_6A, BACK_RED_REEF_6B,
                                BACK_RED_REEF_7A,
                                BACK_RED_REEF_7B,
                                BACK_RED_REEF_8A, BACK_RED_REEF_8B, BACK_RED_REEF_9A, BACK_RED_REEF_9B,
                                BACK_RED_REEF_10A, BACK_RED_REEF_10B, BACK_RED_REEF_11A, BACK_RED_REEF_11B);

                public static final List<Pose2d> FRONT_BLUE_REEF = List.of(FRONT_BLUE_REEF_17A, FRONT_BLUE_REEF_17B,
                                FRONT_BLUE_REEF_18A, FRONT_BLUE_REEF_18B, FRONT_BLUE_REEF_19A, FRONT_BLUE_REEF_19B,
                                FRONT_BLUE_REEF_20A,
                                FRONT_BLUE_REEF_20B, FRONT_BLUE_REEF_21A, FRONT_BLUE_REEF_21B, FRONT_BLUE_REEF_22A,
                                FRONT_BLUE_REEF_22B);

                public static final List<Pose2d> BACK_BLUE_REEF = List.of(BACK_BLUE_REEF_17A, BACK_BLUE_REEF_17B,
                                BACK_BLUE_REEF_18A, BACK_BLUE_REEF_18B, BACK_BLUE_REEF_19A, BACK_BLUE_REEF_19B,
                                BACK_BLUE_REEF_20A,
                                BACK_BLUE_REEF_20B, BACK_BLUE_REEF_21A, BACK_BLUE_REEF_21B, BACK_BLUE_REEF_22A,
                                BACK_BLUE_REEF_22B);
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
