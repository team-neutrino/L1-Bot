package frc.robot.util;

import static frc.robot.Constants.SwerveConstants.*;

import edu.wpi.first.math.controller.PIDController;

public class YawCorrectionPID {
    private PIDController m_rotationControl = new PIDController(YAW_CORRECTION_P, YAW_CORRECTION_I, YAW_CORRECTION_D);

    public double getRotationalRate() {
        return m_rotationControl
                .calculate(Subsystem.swerve.getCANRangeRightDistance() - Subsystem.swerve.getCANRangeLeftDistance());
    }
}
