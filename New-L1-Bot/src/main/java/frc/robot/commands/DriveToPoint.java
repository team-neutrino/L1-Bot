// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.List;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.robot.util.DriveToPointPID;
import frc.robot.util.Subsystem;

import static frc.robot.Constants.DriveToPointConstants.*;

public class DriveToPoint extends Command {
  private Swerve m_swerve;
  private CommandXboxController m_driverController;
  private DriveToPointPID m_pointControl;
  private List<Pose2d> m_localList;
  private boolean m_bumperWasPressed;
  private boolean m_hadGamePiece;

  public DriveToPoint(CommandXboxController driverController) {
    m_swerve = Subsystem.swerve;
    m_driverController = driverController;
    m_pointControl = new DriveToPointPID();
    addRequirements(m_swerve);
  }

  @Override
  public void initialize() {
    m_swerve.setDrivingToPoint(true);
    m_swerve.setAtPoint(false);
    if (!Constants.GlobalConstants.RED_ALLIANCE.isPresent()) {
      System.out.println("NO ALLIANCE VALUE YET");
      return;
    }
    m_hadGamePiece = Subsystem.intake.debouncedHasCoral();
    setTarget();
    setLocalList();
    System.out.println("init");
  }

  @Override
  public void execute() {
    checkBumpers();
    drive();
    updateAtPoint();
    if ((m_swerve.isAtPoint() && (Subsystem.intake.debouncedHasCoral() != m_hadGamePiece))
        || (Subsystem.arm.isAtIntake())) {
      initialize(); // reinitialize if the state of our game piece changes or if we want to intake
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_swerve.setDrivingToPoint(false);
    m_swerve.setAtPoint(false);
    m_pointControl.setTarget(null);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  public static Pose2d frontCalculatePoint(Pose3d tagPose, boolean isPointA) {
    double tagAngle = tagPose.getRotation().getAngle();
    double perpendicularTagAngle = tagAngle - Math.toRadians(90);
    double leftRightOffsetX = PARALLEL_OFFSET
        * Math.cos(perpendicularTagAngle)
        + (REEF_WIDTH / 2) * Math.cos(perpendicularTagAngle + (isPointA ? 0 : Math.PI));
    double leftRightOffsetY = PARALLEL_OFFSET
        * Math.sin(perpendicularTagAngle)
        + (REEF_WIDTH / 2) * Math.sin(perpendicularTagAngle + (isPointA ? 0 : Math.PI));

    double x = tagPose.getX() + FRONT_PERPENDICULAR_OFFSET * Math.cos(tagAngle) + leftRightOffsetX;
    double y = tagPose.getY() + FRONT_PERPENDICULAR_OFFSET * Math.sin(tagAngle) + leftRightOffsetY;

    return new Pose2d(x, y, Rotation2d.fromRadians(tagAngle + Math.PI));
  }

  public static Pose2d backCalculatePoint(Pose3d tagPose, boolean isPointA) {
    double tagAngle = tagPose.getRotation().getAngle();
    double perpendicularTagAngle = tagAngle - Math.toRadians(90);
    double leftRightOffsetX = PARALLEL_OFFSET
        * Math.cos(perpendicularTagAngle)
        + (REEF_WIDTH / 2) * Math.cos(perpendicularTagAngle + (isPointA ? 0 : Math.PI));
    double leftRightOffsetY = PARALLEL_OFFSET
        * Math.sin(perpendicularTagAngle)
        + (REEF_WIDTH / 2) * Math.sin(perpendicularTagAngle + (isPointA ? 0 : Math.PI));

    double x = tagPose.getX() + BACK_PERPENDICULAR_OFFSET * Math.cos(tagAngle) + leftRightOffsetX;
    double y = tagPose.getY() + BACK_PERPENDICULAR_OFFSET * Math.sin(tagAngle) + leftRightOffsetY;

    return new Pose2d(x, y, Rotation2d.fromRadians(tagAngle));
  }

  private void setTarget() {
    m_pointControl.setTargetNearest(
        Subsystem.arm.isFront() ? (Constants.GlobalConstants.RED_ALLIANCE.get() ? FRONT_RED_REEF : FRONT_BLUE_REEF)
            : (Constants.GlobalConstants.RED_ALLIANCE.get() ? BACK_RED_REEF : BACK_BLUE_REEF));
  }

  /*
   * Checks whether the driver has pressed Xbox controller buttons to move to the
   * next tag. Rising edge - only one tag change per button press.
   */
  private void checkBumpers() {
    boolean isLeftPressed = m_driverController.getHID().getLeftBumperButton();
    boolean isRightPressed = m_driverController.getHID().getRightBumperButton();
    if (m_bumperWasPressed && (!isLeftPressed && !isRightPressed)) {
      m_bumperWasPressed = false;
    }
    if (m_bumperWasPressed) {
      return;
    }
    m_bumperWasPressed = isLeftPressed || isRightPressed;

    int id = m_localList.indexOf(m_pointControl.getTarget());
    id += isRightPressed ? -1 : isLeftPressed ? 1 : 0;
    id = id >= m_localList.size() ? 0 : id < 0 ? m_localList.size() - 1 : id;

    m_pointControl.setTarget(m_localList.get(id));
  }

  /*
   * Must be called after target has been set.
   */
  private void setLocalList() {
    Pose2d target = m_pointControl.getTarget();
    System.out.println(target);
    if (FRONT_RED_REEF.contains(target)) {
      m_localList = FRONT_RED_REEF;
      return;
    }
    if (FRONT_BLUE_REEF.contains(target)) {
      m_localList = FRONT_BLUE_REEF;
      return;
    }
    if (BACK_RED_REEF.contains(target)) {
      m_localList = BACK_RED_REEF;
      return;
    }
    if (BACK_BLUE_REEF.contains(target)) {
      m_localList = BACK_BLUE_REEF;
      return;
    }
  }

  public void updateAtPoint() {
    if ((Subsystem.limelight.getFrontTv() || Subsystem.limelight.getBackTv())
        && Math.abs(m_pointControl.getStraightLineDist()) < AT_POINT_TOLERANCE) {
      m_swerve.setDrivingToPoint(false);
      m_swerve.setAtPoint(true);
    } else {
      m_swerve.setDrivingToPoint(true);
      m_swerve.setAtPoint(false);
    }
  }

  private void drive() {
    double xVelocity = m_pointControl.getXVelocity(), yVelocity = m_pointControl.getYVelocity();

    xVelocity = MathUtil.clamp(xVelocity, -MAX_DRIVETOPOINT_SPEED, MAX_DRIVETOPOINT_SPEED);
    yVelocity = MathUtil.clamp(yVelocity, -MAX_DRIVETOPOINT_SPEED, MAX_DRIVETOPOINT_SPEED);

    m_swerve.setVelocity(xVelocity, yVelocity, m_pointControl.getRotation());
  }
}
