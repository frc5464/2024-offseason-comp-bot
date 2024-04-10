package frc.robot.commands;

import entech.commands.EntechCommand;
import frc.robot.OI.UserPolicy;

public class TwistCommand extends EntechCommand {
    @Override
    public void initialize() {
        UserPolicy.twistable = true;
        // TODO: SEE IF WE NEED TO RUN THIS COMMAND!
    }

    @Override
    public void end(boolean interrupted) {
        UserPolicy.twistable = false;
    }
}
