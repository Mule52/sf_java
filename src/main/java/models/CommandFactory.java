package models;

import models.commands.*;

import java.util.List;

public class CommandFactory {

    public static final String DEPEND = "DEPEND";
    public static final String INSTALL = "INSTALL";
    public static final String LIST = "LIST";
    public static final String REMOVE = "REMOVE";

    public Command createCommand(ComponentManager componentManager, Loggable logger, List<String> args){
        switch (args.get(0)){
            case DEPEND:
                return new DependsCommand(componentManager, args);
            case INSTALL:
                return new InstallCommand(componentManager, logger, args);
            case LIST:
                return new ListCommand(componentManager, logger, args);
            case REMOVE:
                return new RemoveCommand(componentManager, logger, args);
            default:
                return new DefaultCommand(componentManager);
        }
    }
}
