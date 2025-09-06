// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.IntakeConstants.*;
import com.reduxrobotics.sensors.canandcolor.Canandcolor;
import com.reduxrobotics.sensors.canandcolor.CanandcolorSettings;

public class Intake extends SubsystemBase {
  private final CANBus m_CANBus = new CANBus("rio");
  private TalonFX m_motor = new TalonFX(INTAKE_MOTOR_ID, m_CANBus);
  private TalonFXConfiguration m_motorConfig = new TalonFXConfiguration();
  private final CurrentLimitsConfigs m_currentLimitConfig = new CurrentLimitsConfigs();
  private double m_motorVoltage;

  private Canandcolor m_proximitySensor = new Canandcolor(PROXIMITY_SENSOR);
  private CanandcolorSettings m_settings = new CanandcolorSettings();
  private Debouncer m_debouncer = new Debouncer(0.1, DebounceType.kFalling);
  private boolean m_debounced;

  public Intake() {
    m_currentLimitConfig.withSupplyCurrentLimit(INTAKE_CURRENT_LIMIT)
        .withSupplyCurrentLimitEnable(true)
        .withStatorCurrentLimit(INTAKE_CURRENT_LIMIT)
        .withStatorCurrentLimitEnable(true);
    m_motorConfig.CurrentLimits = m_currentLimitConfig;

    m_motorConfig.Slot0.kP = INTAKE_kP;
    m_motorConfig.Slot0.kI = INTAKE_kI;
    m_motorConfig.Slot0.kD = INTAKE_kD;

    m_motor.setNeutralMode(NeutralModeValue.Brake);
    m_motor.getConfigurator().apply(m_motorConfig);
    m_settings.setLampLEDBrightness(0.4);
    m_proximitySensor.setSettings(m_settings);
    m_proximitySensor.setLampLEDBrightness(0.1);
  }

  private boolean withinProximity(double distance) {
    return m_proximitySensor.getProximity() < distance;
  }

  public boolean hasCoral() {
    return withinProximity(PROXIMITY);
  }

  /**
   * debounced the falling edge of hasCoral for use with scoring commands
   */
  public boolean debouncedHasCoral() {
    return m_debounced;
  }

  public double getMotorVoltage() {
    return m_motorVoltage;
  }

  @Override
  public void periodic() {
    m_motor.set(m_motorVoltage);
    m_debounced = m_debouncer.calculate(hasCoral());
  }

  public void setVoltage(double volts) {
    m_motorVoltage = volts;
  }

  /**
   * Gives an instance of the coral default command. Stops intake from running
   * 
   * @return The coral default command
   */
  public Command intakeDefaultCommand() {
    return run(() -> {
      m_motorVoltage = 0;
    });
  }

  /**
   * Gives an instance of the run intake command., sets the intake voltage to the
   * speed provided.
   * 
   * @param speed speed coral is set to
   * @return The run intake command
   */
  public Command runIntake(double speed) {
    return run(() -> m_motorVoltage = speed);
  }

}
