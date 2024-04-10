package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import entech.subsystems.EntechSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI.UserPolicy;

public class IntakeSubsystem extends EntechSubsystem{

    //TODO: create a command to automatic run the intake once the shooter is up to speed

    CANSparkMax intake = new CANSparkMax(9, MotorType.kBrushless);
    DigitalInput searchnote = new DigitalInput(0);
    private static final boolean ENABLED = true;
    public boolean notenotdected = false;

    @Override
    public void initialize(){}

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    // TODO: Make the state of the break beam sensor get updated constantly in periodic.
    // This will make your autonomous AND teleop code much cleaner.
    public void periodic(){
        // grab the past state of the break beam
        boolean oldnotenotdetected = notenotdected;
        // grab the newest state of the break beam
        notenotdected = !searchnote.get();
        UserPolicy.noteDetected = searchnote.get();

        // IF something happened (either a note was detected or a note left,)
        if(notenotdected!=oldnotenotdetected){
            if(notenotdected){
                UserPolicy.LEDselected = "White";
            }
            else{
                UserPolicy.LEDselected = "PinkIntake";
            }
        }
        
        SmartDashboard.putBoolean("break beam", notenotdected);
        SmartDashboard.putBoolean("feeding", UserPolicy.feeding);
        SmartDashboard.putBoolean("intaking", UserPolicy.intaking);
        SmartDashboard.putBoolean("speakerscoring", UserPolicy.speakerShoot);
        SmartDashboard.putBoolean("ampshoot", UserPolicy.ampShoot);
    }

    public void DisableIntake(){
        intake.set(0);
    }

    public void IntakeFeed(){
        if(UserPolicy.feeding){ // JAKE PUT THIS IN
            // TODO: Make this stop once the break beam is no longer triggered
            intake.set(-1);
        }
        else{
            DisableIntake();
            UserPolicy.LEDselected = "White";
        }
        }

    public void IntakeReverse(){
        if(UserPolicy.intakeReverse){
            intake.set(1);
        }
        else{
            DisableIntake();
        }
    }    
        
    public void Intake(){
        if(UserPolicy.intaking){
           if(notenotdected == true){
            intake.set(-1);
           }
           
           else{
            intake.set(0);
            UserPolicy.LEDselected = "PinkIntake";
           }
        }
        else{
            DisableIntake();
        }
    }
}