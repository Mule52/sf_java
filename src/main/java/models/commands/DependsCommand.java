package models.commands;

import models.CommandFactory;
import models.Component;
import models.ComponentManager;
import models.commands.Command;

import java.util.List;

public class DependsCommand extends Command {
    private List<String> args = null;

    public DependsCommand(ComponentManager componentManager, List<String> args) {
        super(componentManager);
        if (args.get(0).equals(CommandFactory.DEPEND)){
            this.args = args.subList(1, args.size());
        } else {
            this.args = args;
        }
    }


    @Override
    public void execute() {
        // Create a new component, add it to the map
        Component currentComponent = componentManager.getOrCreateComponent(args.get(0));

        // iterate over component dependencies and get/create them
        // Add each as a dependency to the current component.
        for (String additionAllDependency : args.subList(1, args.size())){
            Component dependencyComponent = componentManager.getOrCreateComponent(additionAllDependency);
            currentComponent.addDependency(dependencyComponent);
            dependencyComponent.addDependent(currentComponent);
        }
    }
}
