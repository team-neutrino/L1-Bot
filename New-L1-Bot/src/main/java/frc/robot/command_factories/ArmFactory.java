// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command_factories;

import static frc.robot.Constants.ArmConstants.*;

import edu.wpi.first.wpilibj2.command.Command;

import static frc.robot.util.Subsystem.arm;

/** Add your docs here. */
public class ArmFactory {
    public static Command IntakePosition() {
        return arm.armRotateCommand(INTAKE_POSITION);
    }

    public static Command ScorePositionFront() {
        return arm.armRotateCommand(FRONT_SCORE_POSITION).until(() -> arm.readyToScore());
    }

    public static Command ScorePositionBack() {
        return arm.armRotateCommand(BACK_SCORE_POSITION).until(() -> arm.readyToScore());
    }
}
