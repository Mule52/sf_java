package models.commands;

import models.ComponentManager;
import models.commands.Command;

public class DefaultCommand extends Command {
    public DefaultCommand(ComponentManager componentManager) {
        super(componentManager);
    }

    @Override
    public void execute() {
        // do nothing
    }
}
