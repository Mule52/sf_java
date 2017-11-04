package models.commands;

import models.CommandFactory;
import models.Component;
import models.ComponentManager;
import models.Loggable;
import models.commands.Command;

import java.util.List;

public class InstallCommand extends Command {

    private List<String> args = null;
    private Loggable logger;

    public InstallCommand(ComponentManager componentManager, Loggable logger, List<String> args) {
        super(componentManager);
        if (args.get(0).equals(CommandFactory.INSTALL)){
            this.args = args.subList(1, args.size());
        } else {
            this.args = args;
        }
        this.logger = logger;
    }

    @Override
    public void execute() {
        // Get an existing component or create a new one, gets added to map
        Component currentComponent = componentManager.getOrCreateComponent(args.get(0));

        if (currentComponent.isInstalled()){
            logger.printFormatted("\t%s is already installed.", currentComponent.getName());
            return;
        }

        installComponent(currentComponent);
    }

    private void installComponent(Component component) {
        // is installed or not?
        if (!component.isInstalled()){
            component.setInstalled(true);

            for (Component dependencyComponent : component.getDependencies().values()){
                if (!dependencyComponent.isInstalled()){
                    installComponent(dependencyComponent);
                }
            }
            logger.printFormatted("\tInstalling %s", component.getName());
        } else {
            logger.printFormatted("\t%s is already installed.", component.getName());
        }
    }
}
