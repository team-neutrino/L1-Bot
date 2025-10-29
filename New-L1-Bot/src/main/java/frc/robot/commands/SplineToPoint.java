// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.path.GoalEndState;
import com.pathplanner.lib.path.IdealStartingState;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.path.Waypoint;
import com.pathplanner.lib.trajectory.PathPlannerTrajectory;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.util.Subsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class SplineToPoint extends Command {
  private List<Waypoint> m_waypoints;
  private Command m_pathCommand;
  private RobotConfig m_config;

  /** Creates a new Splinetopoint. */
  public SplineToPoint() {
    // Use addRequirements() here to declare subsystem dependencies.
    m_config = Subsystem.swerve.getRobotConfig();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_waypoints = PathPlannerPath.waypointsFromPoses(
        Subsystem.swerve.getCurrentPose(),
        new Pose2d(9.0, 4.0, Rotation2d.fromDegrees(0)));

    PathConstraints m_constraints = new PathConstraints(3.0, 3.0, 2 * Math.PI, 4 * Math.PI);

    PathPlannerPath path = new PathPlannerPath(
        m_waypoints,
        m_constraints,
        null,
        new GoalEndState(0.0, Rotation2d.fromDegrees(-90)));

    path.generateTrajectory(new ChassisSpeeds(), new Rotation2d(), m_config);
    m_pathCommand = AutoBuilder.followPath(path);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_pathCommand.execute();
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
}
