// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.util.generated.CommandSwerveDrivetrain;
import frc.robot.util.generated.TunerConstants;

import edu.wpi.first.math.MathUtil;

public class Swerve extends CommandSwerveDrivetrain {

  public Swerve() {
    super(TunerConstants.DrivetrainConstants, TunerConstants.FrontLeft, TunerConstants.FrontRight, TunerConstants.BackLeft, TunerConstants.BackRight);
    resetRotation(Rotation2d.fromDegrees(getYawDegrees()));
  }

  public double getYaw360() {
    return getPigeon2().getYaw().getValueAsDouble() % 360;
  }

  public double getYawDegrees() {
    return Math.toDegrees(getYawRadians());
  }

  public double getYawRadians() {
    return MathUtil.angleModulus(Math.toRadians(getPigeon2().getYaw().getValueAsDouble()));
  }

  public void resetYaw() {
    resetRotation(new Rotation2d(0));
    getPigeon2().reset();
  }

  public Command resetYawCommand() {
    return run(() -> resetYaw());
  }

  @Override
  public void periodic() {
    super.periodic();
  }
}
