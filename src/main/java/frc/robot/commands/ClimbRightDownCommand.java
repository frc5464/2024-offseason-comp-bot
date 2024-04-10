package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbRightDownCommand extends EntechCommand {
    
    private final ClimbSubsystem climb;

    public ClimbRightDownCommand(ClimbSubsystem climb) {
        this.climb = climb;
    }

     @Override
    public void initialize() {
        UserPolicy.rightDown = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.rightDown) {
            climb.RightDown();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.rightDown = false;
        climb.ClimbRightDisable();
    }   
}
