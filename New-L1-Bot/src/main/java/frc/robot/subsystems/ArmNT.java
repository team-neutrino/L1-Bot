// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.BooleanTopic;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTablesJNI;

public class ArmNT extends Arm {
  NetworkTableInstance nt = NetworkTableInstance.getDefault();
  DoubleTopic anglePosition = nt.getDoubleTopic("/arm/angle_position");
  DoubleTopic targetPosition = nt.getDoubleTopic("/arm/target_position");
  DoubleTopic velocity = nt.getDoubleTopic("/arm/velocity");
  BooleanTopic scoreReady = nt.getBooleanTopic("/arm/score_ready");
  final DoublePublisher anglePositionPub;
  final DoublePublisher targetPositionPub;
  final DoublePublisher velocityPub;
  final BooleanPublisher scoreReadyPub;

  public ArmNT() {
    anglePositionPub = anglePosition.publish();
    anglePositionPub.setDefault(0.0);

    targetPositionPub = targetPosition.publish();
    targetPositionPub.setDefault(0.0);

    velocityPub = velocity.publish();
    velocityPub.setDefault(0.0);

    scoreReadyPub = scoreReady.publish();
    scoreReadyPub.setDefault(false);
  }

  @Override
  public void periodic() {
    super.periodic();
    final long now = NetworkTablesJNI.now();
    anglePositionPub.set(getAngle(), now);
    targetPositionPub.set(getTargetAngle(), now);
    velocityPub.set(getAngularVelocity(), now);
    scoreReadyPub.set(readyToScore(), now);
  }
}