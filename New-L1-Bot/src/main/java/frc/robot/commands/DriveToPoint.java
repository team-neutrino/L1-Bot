// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve;
import frc.robot.util.Subsystem;

import static frc.robot.Constants.DriveToPointConstants.*;

public class DriveToPoint extends Command {
  Swerve m_swerve;

  public DriveToPoint() {
    m_swerve = Subsystem.swerve;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  public Pose2d calculatePoint(Pose3d tagPose, boolean isPointA) {
    double tagAngle = tagPose.getRotation().getAngle();
    double perpendicularTagAngle = tagAngle - Math.toRadians(90);
    double leftRightOffsetX = PARALLEL_OFFSET
        * Math.cos(perpendicularTagAngle)
        + (REEF_WIDTH / 2) * Math.cos(perpendicularTagAngle + (isPointA ? 0 : Math.PI));
    double leftRightOffsetY = PARALLEL_OFFSET
        * Math.sin(perpendicularTagAngle)
        + (REEF_WIDTH / 2) * Math.sin(perpendicularTagAngle + (isPointA ? 0 : Math.PI));

    double x = tagPose.getX() + PERPENDICULAR_OFFSET * Math.cos(tagAngle) + leftRightOffsetX;
    double y = tagPose.getY() + PERPENDICULAR_OFFSET * Math.sin(tagAngle) + leftRightOffsetY;

    return new Pose2d(x, y, Rotation2d.fromRadians(tagAngle + Math.PI));
  }
}
