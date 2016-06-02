package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.exceptions.ExitException;

import static ar.fiuba.tdd.tp.Constants.*;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(PORT);
        Writer writer = new Console();
        try {
            //while (true) { TODO: Borrar!
                String gameFilePath = server.setUp();
                server.initializeGame(gameFilePath);
            //}
        } catch (ExitException e) {
            server.terminate();
            writer.write("Goodbye!");
        }
    }
}
