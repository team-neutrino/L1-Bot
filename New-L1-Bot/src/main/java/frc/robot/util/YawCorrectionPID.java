package frc.robot.util;

import static frc.robot.Constants.SwerveConstants.*;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class YawCorrectionPID {
    private PIDController m_rotationControl = new PIDController(YAW_CORRECTION_P, YAW_CORRECTION_I, YAW_CORRECTION_D);
    private static Pose2d m_target = new Pose2d(0, 0, new Rotation2d());

    public double getRotationalRate() {

    }
}
