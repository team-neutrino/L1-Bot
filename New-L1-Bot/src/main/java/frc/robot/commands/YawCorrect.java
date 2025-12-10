// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.SwerveConstants.MAX_ANGULAR_RATE;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve;
import frc.robot.util.Subsystem;
import frc.robot.util.YawCorrectionPID;

public class YawCorrect extends Command {
  private YawCorrectionPID m_rotationController = new YawCorrectionPID();
  private Swerve m_swerve;

  public YawCorrect() {
    m_swerve = Subsystem.swerve;
    addRequirements(m_swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private void drive() {
    double rotationalRate = m_rotationController.getRotationalRate();

    rotationalRate = MathUtil.clamp(rotationalRate, -MAX_ANGULAR_RATE, MAX_ANGULAR_RATE);

    m_swerve.setRotationalRate(rotationalRate);
  }

}