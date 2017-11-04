package models.commands;

import models.CommandFactory;
import models.Component;
import models.ComponentManager;
import models.Loggable;
import models.commands.Command;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RemoveCommand extends Command {

    private List<String> args = null;
    private Loggable logger;

    public RemoveCommand(ComponentManager componentManager, Loggable logger, List<String> args) {
        super(componentManager);
        if (args.get(0).equals(CommandFactory.REMOVE)){
            this.args = args.subList(1, args.size());
        } else {
            this.args = args;
        }
        this.logger = logger;
    }

    @Override
    public void execute() {
        // Get an existing or create a new, update componentMap
        Component currentComponent = componentManager.getOrCreateComponent(args.get(0));
        if (currentComponent == null || !currentComponent.isInstalled()){
            logger.printFormatted("\t%s is not installed", args.get(0));
        } else {
            // need to remove it, but only if other components do not depend on it
            removeComponent(currentComponent);
        }
    }

    private void removeComponent(Component component) {
        // get the dependents of this component
        Map<String, Component> dependents =
                component
                        .getDependents()
                        .values()
                        .stream()
                        .filter(m -> m.isInstalled())
                        .collect(Collectors.toMap(c -> c.getName(), c -> c));

        if (dependents.isEmpty()){
            component.setInstalled(false);
            logger.printFormatted("\tRemoving %s", component.getName());

            for (Component dependencyComponent : component.getDependencies().values()){
                if (dependencyComponent.isInstalled()){
                    removeComponent(dependencyComponent);
                }
            }
        } else {
            logger.printFormatted("\t%s is still needed.", component.getName());
        }
    }
}
