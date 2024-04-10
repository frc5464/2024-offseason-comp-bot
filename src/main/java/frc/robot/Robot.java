// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.OI.OperatorInterface;
import frc.robot.OI.UserPolicy;
import frc.robot.commands.AmpShootCommand;
import frc.robot.commands.AutoAmpShootCommand;
import frc.robot.commands.DummySpeakerShootCommand;
import frc.robot.commands.FeedCommand;
import frc.robot.commands.GyroReset;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.SpeakerShootCommand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    // Main robot startup things
    private Command autonomousCommand;
    private SubsystemManager subsystemManager;
    private CommandFactory commandFactory;
    
    // Commands to register in Path Planner
    private IntakeCommand intakeCommand;
    private AmpShootCommand ampShootCommand;
    private SpeakerShootCommand speakerShootCommand;
    private FeedCommand feedCommand;
    private GyroReset gyroReset;
    private DummySpeakerShootCommand dummySpeakerShootCommand;
    private AutoAmpShootCommand autoAmpShootCommand;
    // private BackflipCommand backflipCommand;


    // Building the autonomous chooser
    private String auto_selected;
    private final SendableChooser<String> auto_chooser = new SendableChooser<>();
    public static final String kPos1 = "Pos1";
    public static final String kPos1Amp = "Pos1Amp";
    public static final String kPos2_4pc = "Pos2_4pc";
    public static final String kPos2non = "Pos2non";
    public static final String kPos2Amp = "Pos2Amp";
    public static final String kPos2_4pc_NoVision = "Pos2_4pc_NoVision";
    public static final String kPos3 = "Pos3";

    public static final String kShoot1 = "Shoot1";
    public static final String kShoot2 = "Shoot2";
    public static final String kShoot3 = "Shoot3";
    public static final String kBackup = "Backup";
    public static final String kCryingInACorner = "CryingInACorner";
    public static final String kPos2_4pc_Offbrand = "Pos2_4pc_Offbrand";
    public static final String kTestAuto = "TestAuto";
    public static final String kPos3Far = "Pos3Far";
    public static final String kPos3ReallyFar = "Pos3ReallyFar";

    private String wait_selected;
    private final SendableChooser<String> wait_chooser = new SendableChooser<>();
    private static final String k0Seconds = "0Seconds";
    private static final String k1Second = "1Second";
    private static final String k2Seconds = "2Seconds";
    private static final String k3Seconds = "3Seconds";

    @Override
    public void robotInit() {
        subsystemManager = new SubsystemManager();
        intakeCommand = new IntakeCommand(subsystemManager.getIntakeSubsystem(),subsystemManager.getLedSubsystem());
        ampShootCommand = new AmpShootCommand(subsystemManager.getShooterSubsystem(), subsystemManager.getIntakeSubsystem());
        speakerShootCommand = new SpeakerShootCommand(subsystemManager.getShooterSubsystem(), subsystemManager.getIntakeSubsystem());
        feedCommand = new FeedCommand(subsystemManager.getIntakeSubsystem());
        gyroReset = new GyroReset(subsystemManager.getDriveSubsystem());
        dummySpeakerShootCommand = new DummySpeakerShootCommand(subsystemManager.getShooterSubsystem(), subsystemManager.getIntakeSubsystem());
        autoAmpShootCommand = new AutoAmpShootCommand(subsystemManager.getShooterSubsystem(), subsystemManager.getIntakeSubsystem());

        NamedCommands.registerCommand("IntakeCommand", intakeCommand);
        NamedCommands.registerCommand("AmpShootCommand", ampShootCommand);
        NamedCommands.registerCommand("SpeakerShootCommand", speakerShootCommand);
        NamedCommands.registerCommand("FeedCommand", feedCommand);
        NamedCommands.registerCommand("GyroReset", gyroReset);
        NamedCommands.registerCommand("DummyIntakeCommand", dummySpeakerShootCommand);
        NamedCommands.registerCommand("AutoAmpShootCommand", autoAmpShootCommand);

        commandFactory = new CommandFactory(subsystemManager);
        OperatorInterface.create(commandFactory, subsystemManager);

        auto_chooser.addOption("Pos1", kPos1);
        auto_chooser.addOption("Pos1Amp", kPos1Amp);
        auto_chooser.addOption("Pos2_4pc", kPos2_4pc);
        auto_chooser.addOption("Pos2_4pc_Offbrand", kPos2_4pc_Offbrand);
        auto_chooser.addOption("Pos2Amp", kPos2Amp);
        auto_chooser.addOption("Pos2non", kPos2non);
        auto_chooser.addOption("Pos2_4pc_NoVision", kPos2_4pc_NoVision);
        auto_chooser.addOption("Pos3", kPos3);
        auto_chooser.addOption("Pos3Far", kPos3Far);
        auto_chooser.addOption("Pos3ReallyFar", kPos3ReallyFar);

        auto_chooser.addOption("Shoot1", kShoot1);
        auto_chooser.addOption("Shoot2", kShoot2);
        auto_chooser.addOption("Shoot3", kShoot3);

        auto_chooser.addOption("Backup", kBackup);
        auto_chooser.setDefaultOption("Shoot2", kShoot2);

        auto_chooser.addOption("CryingInACorner", kCryingInACorner);
        
        auto_chooser.addOption("TestAuto", kTestAuto);

        SmartDashboard.putData("Auto choices", auto_chooser);

        wait_chooser.addOption("0Seconds", k0Seconds);
        wait_chooser.setDefaultOption("0Seconds", k0Seconds);
        wait_chooser.addOption("1Second", k1Second);
        wait_chooser.addOption("2Seconds", k2Seconds);
        wait_chooser.addOption("3Seconds", k3Seconds);

        SmartDashboard.putData("Wait choices", wait_chooser);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        subsystemManager.getClimbSubsystem().periodic();
        subsystemManager.getIntakeSubsystem().periodic();  
        subsystemManager.getVisionSubsystem().periodic(); 
        subsystemManager.getShooterSubsystem().periodic();
        subsystemManager.getLedSubsystem().periodic();

        SmartDashboard.putData("Auto choices", auto_chooser);
        SmartDashboard.putData("Wait choices", wait_chooser);

        SmartDashboard.putNumber("wait time", UserPolicy.wait);
    }

    @Override
    public void autonomousInit() {    
         wait_selected = wait_chooser.getSelected();

            switch(wait_selected){  
                case k0Seconds:
                UserPolicy.wait = 0;
                break;

                case k1Second:
                UserPolicy.wait = 1;
                break;

                case k2Seconds:
                UserPolicy.wait = 2;
                break;

                case k3Seconds:
                UserPolicy.wait = 3;
                break;

                default:
                UserPolicy.wait = 0;
                break;
            }
        auto_selected = auto_chooser.getSelected();

        autonomousCommand = commandFactory.AutonomousRun(auto_selected);

        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic(){
        }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }

    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void disabledInit(){

    }
}
