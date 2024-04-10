package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbLeftUpCommand extends EntechCommand {
    
    private final ClimbSubsystem climb;

    public ClimbLeftUpCommand(ClimbSubsystem climb) {
        this.climb = climb;
    }

     @Override
    public void initialize() {
        UserPolicy.leftUp = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.leftUp) {
            
            climb.LeftUp();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.leftUp = false;
        climb.ClimbLeftDisable();
    }   
}
