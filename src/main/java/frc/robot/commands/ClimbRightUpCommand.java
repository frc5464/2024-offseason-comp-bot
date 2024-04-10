package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbRightUpCommand extends EntechCommand {
    
    private final ClimbSubsystem climb;

    public ClimbRightUpCommand(ClimbSubsystem climb) {
        this.climb = climb;
    }

     @Override
    public void initialize() {
        UserPolicy.rightUp = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.rightUp) {
            climb.RightUp();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.rightUp = false;
        climb.ClimbRightDisable();
    }   
}
