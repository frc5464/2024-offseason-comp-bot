package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbAutoDownCommand extends EntechCommand {
    
    private final ClimbSubsystem climb;

    public ClimbAutoDownCommand(ClimbSubsystem climb) {
        this.climb = climb;
    }

     @Override
    public void initialize() {
        UserPolicy.autoDown = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.autoDown) {
            climb.AutoDown();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.autoDown = false;
        climb.ClimbLeftDisable();
        climb.ClimbRightDisable();
    }   

    @Override 
    public boolean isFinished(){
        if(climb.rightEncoder.getPosition() > climb.rightDown && climb.leftEncoder.getPosition() > climb.leftDown){
            UserPolicy.autoDown = false;
            return true;
        }
        else{
        UserPolicy.autoDown = true;
        return false;
        }
    }
}
