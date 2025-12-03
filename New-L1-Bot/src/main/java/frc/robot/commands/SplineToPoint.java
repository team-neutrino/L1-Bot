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
import com.pathplanner.lib.pathfinding.Pathfinding;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.Pair;
import frc.robot.Constants;
import frc.robot.util.Subsystem;

import static frc.robot.Constants.DriveToPointConstants.*;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class SplineToPoint extends Command {
  private Command m_pathCommand;
  private Pose2d m_target;
  PathConstraints m_constraints;
  private Translation2d m_obstacleA;
  private Translation2d m_obstacleB;

  /** Creates a new Splinetopoint. */
  public SplineToPoint() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Subsystem.swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_constraints = new PathConstraints(3.0, 3.0, 2 * Math.PI, 4 * Math.PI);
    m_target = getClosestPoint();
    setObstacle();
    m_pathCommand = AutoBuilder.pathfindToPose(m_target, m_constraints);
    m_pathCommand.schedule();
  }

  public Pose2d getClosestPoint() {
    return Subsystem.swerve.getCurrentPose().nearest(
        Subsystem.arm.isFront() ? (Constants.GlobalConstants.RED_ALLIANCE.get() ? FRONT_RED_REEF : FRONT_BLUE_REEF)
            : (Constants.GlobalConstants.RED_ALLIANCE.get() ? BACK_RED_REEF : BACK_BLUE_REEF));
  }

  public void setObstacle() {
    m_obstacleA = new Translation2d(5.0, 5.0);
    m_obstacleB = new Translation2d(7, 7);
    Pair<Translation2d, Translation2d> pair = new Pair(m_obstacleA, m_obstacleB);
    List<Pair<Translation2d, Translation2d>> obstacle = List.of(pair);
    Pathfinding.setDynamicObstacles(obstacle, Subsystem.swerve.getCurrentPose().getTranslation());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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
