// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command_factories;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

import static frc.robot.Constants.IntakeConstants.*;

/** Add your docs here. */
public class IntakeFactory {

    public static Command runIntake() {
        return RobotContainer.intake.runIntake(INTAKE_VOLTAGE).until(() -> RobotContainer.intake.hasCoral());
    }
}
