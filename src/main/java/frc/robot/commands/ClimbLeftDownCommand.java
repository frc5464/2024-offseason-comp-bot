package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbLeftDownCommand extends EntechCommand {
    
    private final ClimbSubsystem climb;

    public ClimbLeftDownCommand(ClimbSubsystem climb) {
        this.climb = climb;
    }

     @Override
    public void initialize() {
        UserPolicy.leftDown = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.leftDown) {
            
            climb.LeftDown();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.leftDown = false;
        climb.ClimbLeftDisable();
    }   
}
