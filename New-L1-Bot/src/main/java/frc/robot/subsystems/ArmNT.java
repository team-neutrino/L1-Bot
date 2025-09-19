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
import frc.robot.Constants.ArmConstants;
import frc.robot.util.FFTuner;
import frc.robot.util.PIDTuner;

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

  private PIDTuner m_PIDTuner;
  private double m_previousP = ArmConstants.ARM_kP;
  private double m_previousI = ArmConstants.ARM_kI;
  private double m_previousD = ArmConstants.ARM_kD;
  private FFTuner m_FFTuner;
  private double m_previousFF = ArmConstants.FFCONSTANT;
  private double m_previousAllowedError = ArmConstants.ALLOWED_ERROR;

  public ArmNT() {
    anglePositionPub = anglePosition.publish();
    anglePositionPub.setDefault(0.0);

    targetPositionPub = targetPosition.publish();
    targetPositionPub.setDefault(0.0);

    velocityPub = velocity.publish();
    velocityPub.setDefault(0.0);

    scoreReadyPub = scoreReady.publish();
    scoreReadyPub.setDefault(false);

    m_PIDTuner = new PIDTuner("arm/{tuning}PIDF");

    m_PIDTuner.setP(m_previousP);
    m_PIDTuner.setI(m_previousI);
    m_PIDTuner.setD(m_previousD);

    m_FFTuner = new FFTuner("arm/{tuning}PIDF");

    m_FFTuner.setFF(m_previousFF);
  }

  @Override
  public void periodic() {
    super.periodic();
    final long now = NetworkTablesJNI.now();
    anglePositionPub.set(getAngle(), now);
    targetPositionPub.set(getTargetAngle(), now);
    velocityPub.set(getAngularVelocity(), now);
    scoreReadyPub.set(readyToScore(), now);

    // if (m_PIDTuner.isDifferentValues(m_previousP, m_previousI, m_previousD)) {
    //   changePID(m_PIDTuner.getP(), m_PIDTuner.getI(), m_PIDTuner.getD());
    //   m_previousP = m_PIDTuner.getP();
    //   m_previousI = m_PIDTuner.getI();
    //   m_previousD = m_PIDTuner.getD();
    // }

    if (m_FFTuner.getFF() != m_previousFF) {
      changeFF(m_FFTuner.getFF());
      m_previousFF = m_FFTuner.getFF();
    }
  }
}