// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import static frc.robot.Constants.ArmConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private final CANBus m_CANBus = new CANBus("rio");
  private TalonFX m_motor = new TalonFX(ARM_MOTOR_ID, m_CANBus);
  private TalonFXConfiguration m_motorConfig = new TalonFXConfiguration();
  private final CurrentLimitsConfigs m_currentLimitConfig = new CurrentLimitsConfigs();
  private double m_motorVoltage;
  private double m_targetAngle = START_POSITION;
  private double m_FFConstant = FFCONSTANT;

  public Arm() {
    m_currentLimitConfig.withSupplyCurrentLimit(ARM_CURRENT_LIMIT)
        .withSupplyCurrentLimitEnable(true)
        .withStatorCurrentLimit(ARM_CURRENT_LIMIT)
        .withStatorCurrentLimitEnable(true);
    m_motorConfig.CurrentLimits = m_currentLimitConfig;

    m_motorConfig.Slot0.kP = ARM_kP;
    m_motorConfig.Slot0.kI = ARM_kI;
    m_motorConfig.Slot0.kD = ARM_kD;

    m_motor.setNeutralMode(NeutralModeValue.Coast);
    m_motorConfig.Feedback.SensorToMechanismRatio = 0.57;
    //8/4.22 = 1.8957
    //4.22/8 = 0.5275
    m_motor.getConfigurator().apply(m_motorConfig);
    m_motor.setPosition(START_POSITION);
  }

  /**
   * Returns the actual angle of the arm in degrees with 180 as vertical pointing
   * up and 90 pointing forward
   */
  public double getAngle() {
    return m_motor.getPosition().getValueAsDouble();
  }

  /**
   * Returns the target angle of the arm in degrees with 180 as vertical pointing
   * up and 90 pointing forward
   */
  public double getTargetAngle() {
    return m_targetAngle;
  }

  public double getAngularVelocity() {
    return m_motor.getVelocity().getValueAsDouble();
  }

  private boolean atTargetAngle() {
    return Math.abs(getAngle() - m_targetAngle) <= ALLOWED_ERROR;
  }

  private boolean nearTargetAngle() {
    return Math.abs(getAngle() - m_targetAngle) <= GAIN_THRESHOLD;
  }

  public boolean readyToScore() {
    return atTargetAngle() && !(m_targetAngle == INTAKE_POSITION);
  }

  public boolean isAtIntake() {
    return Math.abs(getAngle() - INTAKE_POSITION) <= TOLERANCE;
  }

  public void setTarget(double target) {
    m_targetAngle = target;
  }

  /**
   * Determines the necessary volts needed for the Feedforward. Used to pass into
   * closed loop controller
   * 
   * @return volts
   */
  private double feedForwardCalculation() {
    double currentAngle = (getAngle() - 90) * (Math.PI / 180);
    double volts = m_FFConstant * Math.cos(currentAngle);
    return volts;
  }

  public void setFeedForward() {
    double targetRotations = m_targetAngle / 360.0;
    PositionVoltage positionControl = new PositionVoltage(targetRotations).withFeedForward(feedForwardCalculation());
    m_motor.setControl(positionControl);
  }

  public void changeFF(double newFF) {
    m_FFConstant = newFF;
  }

  public void setVoltage(double volts) {
    m_motorVoltage = volts;
  }

  @Override
  public void periodic() {
    m_motor.setVoltage(m_motorVoltage);
    setFeedForward();
    System.out.println("Arm angle: " + getAngle());
  }

  /**
   * Gives a instance of the arm default command. Rotates the arm to the default
   * position
   * 
   * @return The rotate wrist command
   */
  public Command armDefaultCommand() {
    return run(() -> {
      m_targetAngle = START_POSITION;
    });
  }

  /**
   * Gives a instance of the arm rotate command. Rotates the arm to the given
   * angle
   * 
   * @param targetAngle
   * @return The arm rotate command
   */
  public Command armRotateCommand(double targetAngle) {
    return run(() -> m_targetAngle = targetAngle);
  }
}
