// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command_factories;

import edu.wpi.first.wpilibj2.command.Command;

import static frc.robot.util.Subsystem.intake;

import static frc.robot.Constants.IntakeConstants.*;

/** Add your docs here. */
public class IntakeFactory {
    public static Command runIntake() {
        return intake.runIntake(INTAKE_VOLTAGE).until(() -> intake.hasCoral());
    }

    public static Command runOuttake() {
        return intake.runIntake(OUTTAKE_VOLTAGE);
    }

    public static Command runSoftOuttake() {
        return intake.runIntake(SOFT_OUTTAKE_VOLTAGE);
    }
}
