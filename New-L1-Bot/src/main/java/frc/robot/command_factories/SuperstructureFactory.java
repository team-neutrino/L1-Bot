// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command_factories;

import edu.wpi.first.wpilibj2.command.Command;
import static frc.robot.util.Subsystem.intake;

/** Add your docs here. */
public class SuperstructureFactory {

    public static Command IntakeCoral() {
        Command armCom = ArmFactory.IntakePosition();
        Command intakeCom = IntakeFactory.runIntake();
        return armCom.alongWith(intakeCom).until(() -> intake.hasCoral()); 
    }

    public static Command ScoreCoral() {
        Command armCom = ArmFactory.ScorePosition();
        Command intakeCom = IntakeFactory.runOuttake();
        return armCom.alongWith(intakeCom).until(() -> !(intake.debouncedHasCoral()));
    }
}
