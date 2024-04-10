package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.IntakeSubsystem;

public class FeedCommand extends EntechCommand{
        private final IntakeSubsystem intake;

    public FeedCommand(IntakeSubsystem intake) {
        this.intake = intake;
    }

     @Override
    public void initialize() {
        UserPolicy.feeding = true;
    }

    @Override
    public void execute(){
            intake.IntakeFeed();
            return;
        }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.feeding = false;
        intake.DisableIntake();
    }   
}
