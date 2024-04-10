package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeReverseCommand extends EntechCommand{
        private final IntakeSubsystem intake;

    public IntakeReverseCommand(IntakeSubsystem intake) {
        this.intake = intake;
    }

     @Override
    public void initialize() {
        UserPolicy.intakeReverse = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.intakeReverse) {
            intake.IntakeReverse();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.intakeReverse = false;
        intake.DisableIntake();
    }   
}
