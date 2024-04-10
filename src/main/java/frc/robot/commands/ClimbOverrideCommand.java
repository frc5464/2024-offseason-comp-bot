package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbOverrideCommand extends EntechCommand {
    private final ClimbSubsystem climb;

    public ClimbOverrideCommand(ClimbSubsystem climb){
        this.climb = climb;
    }

    @Override
    public void initialize() {
        UserPolicy.climbOverride = true;
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.climbOverride = false;
        climb.zeroEncoders();
    }
}
