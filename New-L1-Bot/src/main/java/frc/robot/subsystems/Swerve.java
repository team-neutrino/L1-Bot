// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.util.generated.CommandSwerveDrivetrain;
import frc.robot.util.generated.CommandSwerveDrivetrain.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.MathUtil;

public class Swerve extends SubsystemBase {
  private final CANBus m_CANBus = new CANBus("rio");
  public Pigeon2 pigeon = new Pigeon2(1, m_CANBus);

  public final Pigeon2 getPigeon2() {
    return 
  }

  public Swerve() {
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
  }
}
