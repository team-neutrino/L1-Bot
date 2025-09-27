// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.util.Subsystem;

import static frc.robot.Constants.LimelightConstants.*;

public class Limelight extends SubsystemBase {
  private Swerve m_swerve;
  private double m_lastFrontFrame = -2;
  private double m_lastBackFrame = -2;
  private boolean m_has_tag1;
  private boolean m_has_tag2;
  private double m_yaw = 0;

  /** Creates a new Limelight. */
  public Limelight() {
    m_swerve = Subsystem.swerve;
    LimelightHelpers.setLEDMode_ForceOff(FRONT_REEF_CAMERA);
    LimelightHelpers.setLEDMode_ForceOff(BACK_REEF_CAMERA);
    LimelightHelpers.setCameraPose_RobotSpace(FRONT_REEF_CAMERA,
        FRONT_CAMERA_FORWARD_OFFSET, // Forward offset (meters)
        FRONT_CAMERA_SIDE_OFFSET, // Side offset (meters) left is positive
        FRONT_CAMERA_HEIGHT_OFFSET, // Height offset (meters)
        FRONT_CAMERA_ROLL_OFFSET, // Roll (degrfees)
        FRONT_CAMERA_PITCH_OFFSET, // Pitch (degrees)
        FRONT_CAMERA_YAW_OFFSET // Yaw (degrees)
    );
    LimelightHelpers.setCameraPose_RobotSpace(BACK_REEF_CAMERA,
        BACK_CAMERA_FORWARD_OFFSET, // Forward offset (meters)
        BACK_CAMERA_SIDE_OFFSET, // Side offset (meters) left is positive
        BACK_CAMERA_HEIGHT_OFFSET, // Height offset (meters)
        BACK_CAMERA_ROLL_OFFSET, // Roll (degrfees)
        BACK_CAMERA_PITCH_OFFSET, // Pitch (degrees)
        BACK_CAMERA_YAW_OFFSET // Yaw (degrees)
    );
    LimelightHelpers.SetFiducialDownscalingOverride(FRONT_REEF_CAMERA, 3);
    LimelightHelpers.SetFiducialDownscalingOverride(BACK_REEF_CAMERA, 1);
  }

  private void updateFrontOdometry() {
    m_swerve.setVisionMeasurementStdDevs(VecBuilder.fill(0.7, 0.7, 9999999));
    if (getFrontTv()) {
      LimelightHelpers.PoseEstimate limePoseEst = LimelightHelpers
          .getBotPoseEstimate_wpiBlue_MegaTag2(FRONT_REEF_CAMERA);
      double frame = getFrontFrame(FRONT_REEF_CAMERA);
      if (limePoseEst != null && limePoseEst.tagCount != 0
          && m_swerve.getState().Speeds.omegaRadiansPerSecond < 4 * Math.PI
          && frame > m_lastFrontFrame) {
        m_swerve.addVisionMeasurement(limePoseEst.pose, limePoseEst.timestampSeconds);
      }
      m_lastFrontFrame = frame;
    }
  }

  private void updateBackOdometry() {
    m_swerve.setVisionMeasurementStdDevs(VecBuilder.fill(0.7, 0.7, 9999999));
    if (getBackTv()) {
      LimelightHelpers.PoseEstimate limePoseEst = LimelightHelpers
          .getBotPoseEstimate_wpiBlue_MegaTag2(BACK_REEF_CAMERA);
      double frame = getBackFrame(BACK_REEF_CAMERA);
      if (limePoseEst != null && limePoseEst.tagCount != 0
          && m_swerve.getState().Speeds.omegaRadiansPerSecond < 4 * Math.PI
          && frame > m_lastBackFrame) {
        m_swerve.addVisionMeasurement(limePoseEst.pose, limePoseEst.timestampSeconds);
      }
      m_lastBackFrame = frame;
    }
  }

  public boolean getFrontTv() {
    return m_has_tag1;
  }

  public boolean getBackTv() {
    return m_has_tag2;
  }

  private double getFrontFrame(String limelight) {
    return NetworkTableInstance.getDefault().getTable(limelight).getEntry("hb").getDouble(-1);
  }

  private double getBackFrame(String limelight) {
    return NetworkTableInstance.getDefault().getTable(limelight).getEntry("hb").getDouble(-1);
  }

  public Command limelightDefaultCommand() {
    return run(() -> {
    });
  }

  @Override
  public void periodic() {
    m_has_tag1 = LimelightHelpers.getTV(FRONT_REEF_CAMERA);
    m_has_tag2 = LimelightHelpers.getTV(BACK_REEF_CAMERA);
    m_yaw = m_swerve.getYawDegrees();
    LimelightHelpers.SetRobotOrientation(FRONT_REEF_CAMERA, m_yaw, 0, 0, 0, 0, 0);
    LimelightHelpers.SetRobotOrientation(BACK_REEF_CAMERA, m_yaw, 0, 0, 0, 0, 0);
    updateFrontOdometry();
    updateBackOdometry();
  }
}
