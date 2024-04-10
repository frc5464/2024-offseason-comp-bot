package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SpeakerShootCommand extends EntechCommand {

    private final ShooterSubsystem shoot;
    private final IntakeSubsystem intake;

    public Timer shootTimer = new Timer();

    public SpeakerShootCommand(ShooterSubsystem shoot, IntakeSubsystem intake) {

        this.shoot = shoot;
        this.intake = intake;
    }

     @Override
    public void initialize() {
        UserPolicy.speakerShoot = true;
        UserPolicy.shootUptoSpeed = false;
        UserPolicy.snapAprilSpeaker = true;
        UserPolicy.closetospeaker = false;

        shootTimer.reset();
        shootTimer.start();
    }

    @Override
    public void execute(){

        if (UserPolicy.speakerShoot) {
            // If we have April tag detection working properly,
            // We only auto-shoot when we are in range.
            if ((UserPolicy.shootUptoSpeed && UserPolicy.closetospeaker) || shootTimer.get() > 2) {
                UserPolicy.feeding = true;
                shootTimer.stop();
    
            }
            if(UserPolicy.feeding == true){
                intake.IntakeFeed();
                }
            shoot.SpeakerCommand();
            return;
            // }
            
            // else{
            //     // Otherwise, if no apriltags are detected, just shoot when up to speed.
            //     if (UserPolicy.shootUptoSpeed) {
            //         UserPolicy.feeding = true;
            //         intake.IntakeFeed();
            //     }
            //     shoot.SpeakerCommand();
            //     return;
            // }
        }
    }

    @Override
    public void end(boolean interrupted) {

        UserPolicy.speakerShoot = false;
        UserPolicy.feeding = false;
        shoot.DisableShoot();
        intake.DisableIntake();
        // TODO: Sam asked to have the drivetrain always homing even when not shooting. How do we do this?
        UserPolicy.snapAprilSpeaker = false;

        shootTimer.stop();
    }  

    @Override
    public boolean isFinished(){
        if (intake.notenotdected == true) {
            return true;
        }

        else {
            return false;
        }
    }

}
