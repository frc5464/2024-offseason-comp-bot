package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootReverseCommand extends EntechCommand{
        private final ShooterSubsystem shoot;

    public ShootReverseCommand(ShooterSubsystem shoot) {
        this.shoot = shoot;
    }

     @Override
    public void initialize() {
        UserPolicy.shootReverse = true;
    }

    @Override
    public void execute(){
        if (UserPolicy.shootReverse) {
            shoot.ShootReverse();
            return;
        }
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.intakeReverse = false;
        shoot.DisableShoot();
    }   
}
