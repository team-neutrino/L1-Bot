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
  private double m_lastFrame = -2;
  private boolean m_has_tag;
  private double m_yaw = 0;

  /** Creates a new Limelight. */
  public Limelight() {
    m_swerve = Subsystem.swerve;
    LimelightHelpers.setLEDMode_ForceOff(REEF_CAMERA);
    LimelightHelpers.setCameraPose_RobotSpace(REEF_CAMERA,
        CAMERA_FORWARD_OFFSET, // Forward offset (meters)
        CAMERA_SIDE_OFFSET, // Side offset (meters) left is positive
        CAMERA_HEIGHT_OFFSET, // Height offset (meters)
        CAMERA_ROLL_OFFSET, // Roll (degrfees)
        CAMERA_PITCH_OFFSET, // Pitch (degrees)
        CAMERA_YAW_OFFSET // Yaw (degrees)
    );
    LimelightHelpers.SetFiducialDownscalingOverride(REEF_CAMERA, 3);

  }

  private void updateOdometry() {
    Subsystem.swerve.setVisionMeasurementStdDevs(VecBuilder.fill(0.7, 0.7, 9999999));
    LimelightHelpers.PoseEstimate limePoseEst = LimelightHelpers
        .getBotPoseEstimate_wpiBlue_MegaTag2(REEF_CAMERA);
    double frame = getFrame(REEF_CAMERA);
    if (limePoseEst != null && limePoseEst.tagCount != 0
        && m_swerve.getState().Speeds.omegaRadiansPerSecond < 4 * Math.PI
        && frame > m_lastFrame) {
      m_swerve.addVisionMeasurement(limePoseEst.pose, limePoseEst.timestampSeconds);
    }
    m_lastFrame = frame;
  }

  private boolean getTv() {
    return m_has_tag;
  }

  private double getFrame(String limelight) {
    return NetworkTableInstance.getDefault().getTable(limelight).getEntry("hb").getDouble(-1);
  }

  public Command limelightDefaultCommand() {
    return run(() -> {
    });
  }

  @Override
  public void periodic() {
    m_has_tag = LimelightHelpers.getTV(REEF_CAMERA);
    m_yaw = m_swerve.getYawDegrees();
    LimelightHelpers.SetRobotOrientation(REEF_CAMERA, m_yaw, 0, 0, 0, 0, 0);
    updateOdometry();
  }
}
