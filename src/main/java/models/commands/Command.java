package models.commands;

import models.ComponentManager;

public abstract class Command {
    protected ComponentManager componentManager;

    public Command(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public abstract void execute();
}
