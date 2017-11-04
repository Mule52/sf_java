package models.commands;

import models.CommandFactory;
import models.ComponentManager;
import models.Loggable;
import models.commands.Command;

import java.util.List;

public class ListCommand extends Command {
    private List<String> args = null;
    private Loggable logger;

    public ListCommand(ComponentManager componentManager, Loggable logger, List<String> args) {
        super(componentManager);
        if (args.get(0).equals(CommandFactory.LIST)){
            this.args = args.subList(1, args.size());
        } else {
            this.args = args;
        }
        this.logger = logger;
    }


    @Override
    public void execute() {
        // list what is installed
        componentManager.componentMap
                .values()
                .stream()
                .filter(c -> c.isInstalled())
                .forEach(x -> logger.printFormatted("\t %s", x.getName()));
    }
}
