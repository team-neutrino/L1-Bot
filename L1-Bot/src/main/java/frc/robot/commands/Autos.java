// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.*;
import frc.robot.subsystems.Intake;

// TODO
public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(Intake subsystem) {
    return RobotContainer.intake.runIntake(0.3);
    // might want to do
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
