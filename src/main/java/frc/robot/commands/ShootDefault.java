package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootDefault extends EntechCommand{
        private final ShooterSubsystem shoot;
        
        public ShootDefault(ShooterSubsystem shoot) {
            super(shoot);
            this.shoot = shoot;
        }
    
         @Override
        public void initialize() {
        }
    
        @Override
        public void execute(){
            shoot.Default();
        }
    
        @Override
        public void end(boolean interrupted) {
            shoot.DisableShoot();
        }   
}
