package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ShooterSubsystem;

public class DisableShootCommand extends EntechCommand {

    private final ShooterSubsystem shoot;

    public DisableShootCommand(ShooterSubsystem shoot) {
        this.shoot = shoot;
    }

     @Override
    public void initialize() {
        UserPolicy.disableShoot = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.disableShoot) {
            shoot.DisableShoot();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.disableShoot = false;
        shoot.DisableShoot();
    }   
}
