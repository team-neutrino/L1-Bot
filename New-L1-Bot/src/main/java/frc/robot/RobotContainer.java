// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.commands.PathfindingCommand;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import frc.robot.Constants.DriveToPointConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.command_factories.ArmFactory;
import frc.robot.command_factories.IntakeFactory;
import frc.robot.command_factories.SuperstructureFactory;
import frc.robot.commands.DriveToPoint;
import frc.robot.commands.SplineToPoint;
import frc.robot.util.Subsystem;

import static frc.robot.util.Subsystem.*;

public class RobotContainer {
        private Subsystem subsystemContainer;
        private Command m_autonPath;

        private final CommandXboxController m_driverController = new CommandXboxController(
                        OperatorConstants.kDriversControllerPort);
        private final CommandXboxController m_buttonController = new CommandXboxController(
                        OperatorConstants.kButtonsControllerPort);

        public RobotContainer() {
                AprilTagFieldLayout tmp = DriveToPointConstants.FIELD_LAYOUT;
                subsystemContainer = new Subsystem();
                configureDefaultCommands();
                configureBindings();
                configureNamedCommand();
                PathfindingCommand.warmupCommand().schedule();
                // DataLogManager.start();
                SignalLogger.enableAutoLogging(false);
                m_autonPath = new PathPlannerAuto("Top");
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
                m_driverController.leftTrigger().whileTrue(swerve.slowDriveCommand(m_driverController));
                m_driverController.a().whileTrue(new SplineToPoint());

                m_buttonController.x().whileTrue(SuperstructureFactory.IntakeCoral());
                m_buttonController.y().toggleOnTrue(ArmFactory.ScorePositionBack());
                m_buttonController.rightBumper().whileTrue(IntakeFactory.runOuttake());
                m_buttonController.leftBumper().whileTrue(IntakeFactory.runSoftOuttake());
        }

        private void configureNamedCommand() {
                NamedCommands.registerCommand("Score L1",
                                IntakeFactory.runOuttake().until(() -> !intake.debouncedHasCoral()));
                NamedCommands.registerCommand("Move Arm Back", ArmFactory.ScorePositionBack());
                NamedCommands.registerCommand("DriveToPointForever",
                                new DriveToPoint(m_driverController));
                NamedCommands.registerCommand("DriveToPoint",
                                new DriveToPoint(m_driverController)
                                                .until(() -> swerve.isAtPoint()));
                NamedCommands.registerCommand("Arm In Frame Perimeter", ArmFactory.InFramePerimeter());
        }

        public Command getAutonomousCommand() {
                Command auto;

                if (Subsystem.swerve == null) {
                        return new InstantCommand();
                }
                try {
                        auto = m_autonPath;
                } catch (Exception e) {
                        System.err.println("Caught exception when loading auto");
                        auto = new PathPlannerAuto("Nothing");
                }

                return auto;
        }
}