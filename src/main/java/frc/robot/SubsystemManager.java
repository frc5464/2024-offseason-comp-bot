// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;

/**
 * Manages the subsystems and the interactions between them.
 */
public class SubsystemManager {
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final ClimbSubsystem armSubsystem = new ClimbSubsystem();
    private final VisionSubsystem visionSubsystem = new VisionSubsystem();
    private final LEDSubsystem ledSubsystem = new LEDSubsystem();

    public SubsystemManager() {
        driveSubsystem.initialize();
        armSubsystem.initialize();
        visionSubsystem.initialize();
        shooterSubsystem.initialize();
        ledSubsystem.initialize();
    }

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    public ShooterSubsystem getShooterSubsystem(){
        return shooterSubsystem;
    }
    
    public IntakeSubsystem getIntakeSubsystem() {
        return intakeSubsystem;
    }

    public ClimbSubsystem getClimbSubsystem(){
        return armSubsystem;
    }

    public VisionSubsystem getVisionSubsystem(){
        return visionSubsystem;
    }

    public LEDSubsystem getLedSubsystem(){
        return ledSubsystem;
    }
}
