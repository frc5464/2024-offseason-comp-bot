package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;

public class YellowCoopCommand extends EntechCommand {
    
    // private final LEDSubsystem led;

    // public YellowCoopCommand(LEDSubsystem led) {
    //     this.led = led;
    // }

     @Override
    public void initialize() {
        UserPolicy.LEDselected = "YellowCoop";
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        UserPolicy.LEDselected = "White";
    }   
}
