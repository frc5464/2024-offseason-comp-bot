package frc.robot;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.commands.GyroReset;
import frc.robot.subsystems.DriveSubsystem;

@SuppressWarnings("unused")
public class CommandFactory {
    private DriveSubsystem driveSubsystem;

    public CommandFactory(SubsystemManager subsystemManager) {
        this.driveSubsystem = subsystemManager.getDriveSubsystem();
    }


    public Command AutonomousRun(String autoName){
        SequentialCommandGroup auto = new SequentialCommandGroup();
        auto.addCommands(new WaitCommand(UserPolicy.wait));
        auto.addCommands(new GyroReset(driveSubsystem));
        auto.addCommands(new PathPlannerAuto(autoName));

        return auto;
    }
}
