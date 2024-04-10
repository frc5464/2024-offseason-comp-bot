package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AmpShootCommand extends EntechCommand {

    private final ShooterSubsystem shoot;
    private final IntakeSubsystem intake;

    public Timer ampTimer = new Timer();

    public AmpShootCommand(ShooterSubsystem shoot, IntakeSubsystem intake) {
        // TODO: Do some funky stuff, the same as the speaker shoot command, to get auto-feeding working
        this.shoot = shoot;
        this.intake = intake;

    }

     @Override
    public void initialize() {
        UserPolicy.ampShoot = true;
        UserPolicy.shootUptoSpeed = false;
        UserPolicy.closetoamp = false;

        ampTimer.reset();
        ampTimer.start();
    }

    @Override
    public void execute(){
        if (UserPolicy.ampShoot) {
            // if(UserPolicy.shootUptoSpeed){
            //     UserPolicy.feeding = true;
            // }
            // // if(UserPolicy.closetoamp || ampTimer.get() > 2){
            // //     UserPolicy.feeding = true;
            // // }
            // if(UserPolicy.feeding == true){
            //     intake.IntakeFeed();
            //     }
            shoot.AmpCommand();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.ampShoot = false;
        UserPolicy.feeding = false;
        ampTimer.stop();
        shoot.DisableShoot();
        intake.DisableIntake();
    }   
}
