// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Telemetry;
import frc.robot.util.generated.CommandSwerveDrivetrain;
import frc.robot.util.generated.TunerConstants;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest.ForwardPerspectiveValue;
import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.MathUtil;

import static frc.robot.Constants.DriveToPointConstants.DRIVE_TO_POINT_D;
import static frc.robot.Constants.DriveToPointConstants.DRIVE_TO_POINT_I;
import static frc.robot.Constants.DriveToPointConstants.DRIVE_TO_POINT_P;
import static frc.robot.Constants.SwerveConstants.*;

public class Swerve extends CommandSwerveDrivetrain {
  private boolean m_isDrivingToPoint = false;
  private boolean m_isAtPoint = false;
  private Telemetry m_telemetry = new Telemetry(MAX_SPEED);

  public Swerve() {
    super(TunerConstants.DrivetrainConstants, TunerConstants.FrontLeft, TunerConstants.FrontRight,
        TunerConstants.BackLeft, TunerConstants.BackRight);
    resetRotation(Rotation2d.fromDegrees(getYawDegrees()));
    configureRequestPID();
    registerTelemetry(m_telemetry::telemeterize);
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

  public void setDrivingToPoint(boolean isDrivingToPoint) {
    m_isDrivingToPoint = isDrivingToPoint;
  }

  public boolean getDrivingToPoint() {
    return m_isDrivingToPoint;
  }

  public void setAtPoint(boolean isAtPoint) {
    m_isAtPoint = isAtPoint;
  }

  public boolean isAtPoint() {
    return m_isAtPoint;
  }

  public Pose2d getCurrentPose() {
    return getState().Pose;
  }

  public Command defaultCommand(CommandXboxController driverController) {
    return applyRequest(() -> SwerveRequestStash.drive.withVelocityX(-driverController.getLeftY() * MAX_SPEED)
        .withVelocityY(-driverController.getLeftX() * MAX_SPEED)
        .withRotationalRate(-driverController.getRightX() * MAX_ANGULAR_RATE));
  }

  public Command slowDriveCommand(CommandXboxController driverController) {
    return applyRequest(() -> SwerveRequestStash.drive.withVelocityX(-driverController.getLeftY() * SLOW_SPEED)
        .withVelocityY(-driverController.getLeftX() * SLOW_SPEED)
        .withRotationalRate(-driverController.getRightX() * SLOW_ANGULAR_RATE));
  }

  public void setVelocity(double xVelocity, double yVelocity, Rotation2d targetDirection) {
    SwerveRequestStash.driveWithVelocity
        .withVelocityX(xVelocity)
        .withVelocityY(yVelocity)
        .withTargetDirection(targetDirection);
    setControl(SwerveRequestStash.driveWithVelocity);
  }

  public void configureRequestPID() {
    SwerveRequestStash.driveWithVelocity.HeadingController.setPID(DRIVE_TO_POINT_P, DRIVE_TO_POINT_I, DRIVE_TO_POINT_D);
  }

  public class SwerveRequestStash {
    public static final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
        .withDeadband(MAX_SPEED * 0.1).withRotationalDeadband(MAX_ANGULAR_RATE * 0.1)
        .withDriveRequestType(DriveRequestType.OpenLoopVoltage);
    public static final SwerveRequest.FieldCentricFacingAngle driveWithVelocity = new SwerveRequest.FieldCentricFacingAngle()
        .withDriveRequestType(DriveRequestType.Velocity).withForwardPerspective(ForwardPerspectiveValue.BlueAlliance);
  }

  @Override
  public void periodic() {
    super.periodic();
  }
}
