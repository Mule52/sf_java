package models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentManager {

    private Loggable logger;
    private CommandFactory commandFactory;
    public static Map<String, Component> componentMap = new HashMap<>();

    public ComponentManager(Loggable logger, CommandFactory commandFactory){
        this.logger = logger;
        this.commandFactory = commandFactory;
    }

    public void processAllInputCommands(List<String> commands){
        for (String cmd : commands){
            processCommand(cmd);
        }
    }

    public void processCommand(String cmd){
        logger.print(cmd);

        if (cmd == null || cmd.trim().length() == 0){
            return;
        }

        List<String> cmdArgs = Arrays.asList(cmd.split("\\s+"));

        commandFactory.createCommand(this, logger, cmdArgs).execute();
    }

    public Component getComponent(String name){
        return componentMap.get(name);
    }

    public Component getOrCreateComponent(String name) {
        // get the component if it exists, if not, create new, then return
        Component component = getComponent(name);
        if (component == null){
            component = new Component(name);
            componentMap.put(name, component);
        }
        return component;
    }
}
