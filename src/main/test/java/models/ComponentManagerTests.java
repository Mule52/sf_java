package models;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ComponentManagerTests {

    @BeforeAll
    public void beforeAll() {

    }

    @AfterAll
    public void afterAll() {

    }

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {
    }

    @Test
    public void endToEndTest(){
        try {
            String inputFile = "/proga.dat";
            String expectedOutput = "[DEPEND   TELNET TCPIP NETCARD, DEPEND TCPIP NETCARD, DEPEND DNS TCPIP NETCARD, DEPEND  BROWSER   TCPIP  HTML, INSTALL NETCARD, \tInstalling NETCARD, INSTALL TELNET, \tInstalling TCPIP, \tInstalling TELNET, INSTALL foo, \tInstalling foo, REMOVE NETCARD, \tNETCARD is still needed., INSTALL BROWSER, \tInstalling HTML, \tInstalling BROWSER, INSTALL DNS, \tInstalling DNS, LIST, \t BROWSER, \t TELNET, \t TCPIP, \t foo, \t DNS, \t HTML, \t NETCARD, REMOVE TELNET, \tRemoving TELNET, \tTCPIP is still needed., \tNETCARD is still needed., REMOVE NETCARD, \tNETCARD is still needed., REMOVE DNS, \tRemoving DNS, \tTCPIP is still needed., \tNETCARD is still needed., REMOVE NETCARD, \tNETCARD is still needed., INSTALL NETCARD, \tNETCARD is already installed., REMOVE TCPIP, \tTCPIP is still needed., REMOVE BROWSER, \tRemoving BROWSER, \tRemoving TCPIP, \tRemoving NETCARD, \tRemoving HTML, REMOVE TCPIP, \tTCPIP is not installed, LIST, \t foo, END]";
            URI uri = this.getClass().getResource(inputFile).toURI();
            List<String> inputCommands = Files.readAllLines(Paths.get(uri));

            Logger logger = new Logger();
            CommandFactory commandFactory = new CommandFactory();

            ComponentManager componentManager = new ComponentManager(logger, commandFactory);
            componentManager.processAllInputCommands(inputCommands);

            assertEquals(expectedOutput, logger.getResults().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: need to add many more tests, for each component
}
