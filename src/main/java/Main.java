package main.java;

import models.CommandFactory;
import models.ComponentManager;
import models.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args){

        Main main = new Main();
        String inputFile = "/proga.dat";
        try {
            URI uri = main.getClass().getResource(inputFile).toURI();
            List<String> inputCommands = Files.readAllLines(Paths.get(uri));

            Logger logger = new Logger();
            CommandFactory commandFactory = new CommandFactory();

            ComponentManager componentManager = new ComponentManager(logger, commandFactory);
            componentManager.processAllInputCommands(inputCommands);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
