// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import frc.robot.Constants.DriveToPointConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.command_factories.ArmFactory;
import frc.robot.command_factories.IntakeFactory;
import frc.robot.command_factories.SuperstructureFactory;
import frc.robot.commands.DriveToPoint;
import frc.robot.util.Subsystem;

import static frc.robot.util.Subsystem.*;

public class RobotContainer {
        private Subsystem subsystemContainer;

        private final CommandXboxController m_driverController = new CommandXboxController(
                        OperatorConstants.kDriversControllerPort);
        private final CommandXboxController m_buttonController = new CommandXboxController(
                        OperatorConstants.kButtonsControllerPort);

        public RobotContainer() {
                AprilTagFieldLayout tmp = DriveToPointConstants.FIELD_LAYOUT;
                subsystemContainer = new Subsystem();
                configureDefaultCommands();
                configureBindings();
        }

        private void configureDefaultCommands() {
                intake.setDefaultCommand(intake.intakeDefaultCommand());
                arm.setDefaultCommand(arm.armDefaultCommand());
                swerve.setDefaultCommand(
                                swerve.defaultCommand(m_driverController));
        }

        private void configureBindings() {
                // Idle while the robot is disabled. This ensures the configured
                // neutral mode is applied to the drive motors while disabled.
                final var idle = new SwerveRequest.Idle();
                RobotModeTriggers.disabled().whileTrue(
                                swerve.applyRequest(() -> idle).ignoringDisable(true));

                m_driverController.back().whileTrue(swerve.resetYawCommand());
                m_driverController.b().whileTrue(new DriveToPoint(m_driverController));

                m_buttonController.x().whileTrue(SuperstructureFactory.IntakeCoral());
                m_buttonController.y().toggleOnTrue(ArmFactory.ScorePositionBack());
                m_buttonController.rightBumper().whileTrue(IntakeFactory.runOuttake());
                m_buttonController.leftBumper().whileTrue(IntakeFactory.runSoftOuttake());
        }

        public Command getAutonomousCommand() {
                return Commands.print("No autonomous command configured");
        }
}