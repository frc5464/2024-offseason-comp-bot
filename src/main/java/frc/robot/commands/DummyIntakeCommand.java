package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;

public class DummyIntakeCommand extends EntechCommand{
        private final IntakeSubsystem intake;
        private final LEDSubsystem leds;

    public DummyIntakeCommand(IntakeSubsystem intake, LEDSubsystem leds) {
        this.intake = intake;
        this.leds = leds;
    }

     @Override
    public void initialize() {
        UserPolicy.intaking = true;
        if(RobotState.isAutonomous()){
            UserPolicy.homingPathToNote = true;
        }    
        UserPolicy.dummyIntake = true;
    }

    @Override
    public void execute(){
        if(UserPolicy.intaking){
            intake.Intake();
        return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.intaking = false;
        if(RobotState.isAutonomous()){
            UserPolicy.homingPathToNote = false;
        }    
        intake.DisableIntake();
        UserPolicy.dummyIntake = false;
    }   

    //Returns value of boolean and quits the command when break beam sensor is tripped

    @Override
    public boolean isFinished(){
        if (intake.notenotdected == false) {
            UserPolicy.LEDselected = "PinkIntake";
            leds.startBlinking();
            return true;
        }

        else {
            return false;
        }
    }

}
