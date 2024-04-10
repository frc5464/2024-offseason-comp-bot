package frc.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import entech.util.EntechJoystick;
import frc.robot.CommandFactory;
import frc.robot.RobotConstants;
import frc.robot.SubsystemManager;
import frc.robot.commands.AmpShootCommand;
import frc.robot.commands.ClimbLeftUpCommand;
import frc.robot.commands.ClimbOverrideCommand;
import frc.robot.commands.ClimbRightDownCommand;
import frc.robot.commands.ClimbRightUpCommand;
import frc.robot.commands.DisableShootCommand;
import frc.robot.commands.BlueAmplifiedCommand;
import frc.robot.commands.ClimbAutoDownCommand;
import frc.robot.commands.ClimbAutoUpCommand;
import frc.robot.commands.ClimbLeftDownCommand;
//import frc.robot.commands.AmpRPMCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DummyIntakeCommand;
import frc.robot.commands.FeedCommand;
import frc.robot.commands.GyroReset;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeReverseCommand;
import frc.robot.commands.ShootDefault;
import frc.robot.commands.YellowCoopCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.commands.ShootReverseCommand;
import frc.robot.commands.SpeakerShootCommand;
//import frc.robot.commands.SpeakerRPMCommand;
import frc.robot.commands.XCommand;
//import frc.robot.commands.AmpRPMCommand;
//import frc.robot.commands.SpeakerRPMCommand;

@SuppressWarnings("unused")
public final class OperatorInterface {
    // private static final EntechJoystick driveJoystick = new EntechJoystick(RobotConstants.Ports.CONTROLLER.JOYSTICK);
    private static final EntechJoystick operatorPanel = new EntechJoystick(RobotConstants.Ports.CONTROLLER.PANEL);
    private static final CommandJoystick driveController = new CommandJoystick(0);
    private static final CommandJoystick secondaryController = new CommandJoystick(1);

    /**
     * Connects commands to operator panel and joystick.
     * 
     * 
     * @param commandFactory
     * @param subsystemManager
     */
    public static void create(CommandFactory commandFactory, SubsystemManager subsystemManager) {
        final DriveSubsystem drive = subsystemManager.getDriveSubsystem();
        final ShooterSubsystem shoot = subsystemManager.getShooterSubsystem();
        final IntakeSubsystem intake = subsystemManager.getIntakeSubsystem();
        final VisionSubsystem vision = subsystemManager.getVisionSubsystem();
        final LEDSubsystem led = subsystemManager.getLedSubsystem();
        final ClimbSubsystem climb = subsystemManager.getClimbSubsystem();

        //Driver controller
        driveController.button(4).onTrue(new GyroReset(drive));
        driveController.button(3).onTrue(new XCommand());
        
        driveController.axisGreaterThan(3, 0.1).whileTrue(new SpeakerShootCommand(shoot, intake));
        driveController.button(2).whileTrue(new AmpShootCommand(shoot, intake));
        driveController.button(8).whileTrue(new ShootReverseCommand(shoot));

        driveController.axisGreaterThan(2, 0.1).whileTrue(new IntakeCommand(intake,led));
        driveController.button(1).whileTrue(new DummyIntakeCommand(intake, led));
        driveController.button(5).whileTrue(new FeedCommand(intake));
        driveController.button(7).whileTrue(new IntakeReverseCommand(intake));

        // //Secondary controller
        secondaryController.pov(0).whileTrue(new ClimbRightUpCommand(climb));
        secondaryController.pov(180).whileTrue(new ClimbRightDownCommand(climb));
        secondaryController.button(4).whileTrue(new ClimbLeftUpCommand(climb));
        secondaryController.button(1).whileTrue(new ClimbLeftDownCommand(climb));
        secondaryController.axisGreaterThan(3, 0.1).whileTrue(new ClimbOverrideCommand(climb));

        secondaryController.button(7).whileTrue(new YellowCoopCommand());
        secondaryController.button(8).whileTrue(new BlueAmplifiedCommand());

        subsystemManager.getDriveSubsystem().setDefaultCommand(new DriveCommand(drive,vision, driveController));
        subsystemManager.getShooterSubsystem().setDefaultCommand(new ShootDefault(shoot));
    }

    private OperatorInterface() {
    }
}