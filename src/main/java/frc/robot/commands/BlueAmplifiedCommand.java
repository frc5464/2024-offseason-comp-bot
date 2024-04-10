package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;

public class BlueAmplifiedCommand extends EntechCommand {
    
    // private final LEDSubsystem led;

    // public BlueAmplifiedCommand(LEDSubsystem led) {
    //     this.led = led;
    // }

     @Override
    public void initialize() {
        UserPolicy.LEDselected = "BlueAmplified";
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        UserPolicy.LEDselected = "White";
    }   
}
