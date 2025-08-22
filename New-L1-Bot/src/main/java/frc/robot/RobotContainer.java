// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import frc.robot.Constants.OperatorConstants;
import frc.robot.command_factories.ArmFactory;
import frc.robot.command_factories.IntakeFactory;
import frc.robot.command_factories.SuperstructureFactory;
import frc.robot.util.Subsystem;

import static frc.robot.util.Subsystem.*;

public class RobotContainer {
        private Subsystem subsystemContainer;

        private final CommandXboxController m_driverController = new CommandXboxController(
                        OperatorConstants.kDriversControllerPort);
        private final CommandXboxController m_buttonController = new CommandXboxController(
                        OperatorConstants.kButtonsControllerPort);

        public RobotContainer() {
                subsystemContainer = new Subsystem();
                configureDefaultCommands();
                configureBindings();
        }

        private void configureDefaultCommands() {
                intake.setDefaultCommand(intake.intakeDefaultCommand());
                arm.setDefaultCommand(arm.armDefaultCommand());
        }

        private void configureBindings() {
                swerve.setDefaultCommand(
                                swerve.defaultCommand(m_driverController));

                // Idle while the robot is disabled. This ensures the configured
                // neutral mode is applied to the drive motors while disabled.
                final var idle = new SwerveRequest.Idle();
                RobotModeTriggers.disabled().whileTrue(
                                swerve.applyRequest(() -> idle).ignoringDisable(true));

                m_driverController.back().whileTrue(swerve.resetYawCommand());

                m_buttonController.x().whileTrue(SuperstructureFactory.IntakeCoral());
                m_buttonController.y().whileTrue(SuperstructureFactory.ScoreCoral());
                m_buttonController.a().whileTrue(IntakeFactory.runIntake());
                m_buttonController.b().whileTrue(ArmFactory.IntakePosition());

        }

        m_driverController.a().whileTrue(drivetrain.applyRequest(() -> brake));
        m_driverController.b().whileTrue(drivetrain.applyRequest(
                () -> point.withModuleDirection(
                        new Rotation2d(-m_driverController.getLeftY(), -m_driverController.getLeftX()))));

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        m_driverController.back().and(m_driverController.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        m_driverController.back().and(m_driverController.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        m_driverController.start().and(m_driverController.y())
                .whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        m_driverController.start().and(m_driverController.x())
                .whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        // reset the field-centric heading on left bumper press
        m_driverController.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));

        drivetrain.registerTelemetry(logger::telemeterize);

        m_buttonController.x().whileTrue(SuperstructureFactory.IntakeCoral());
        m_buttonController.y().whileTrue(SuperstructureFactory.ScoreCoral());
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
