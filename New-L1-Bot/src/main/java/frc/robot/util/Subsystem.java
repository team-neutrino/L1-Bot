// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;

/** Add your docs here. */
public class Subsystem {
    public static final Intake intake = new Intake();
    public static final Arm arm = new Arm();
    public static final Swerve swerve = new Swerve();
}
