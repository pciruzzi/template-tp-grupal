package ar.fiuba.tdd.tp.connection.server;

import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.Writer;
import ar.fiuba.tdd.tp.exceptions.ExitException;

import static ar.fiuba.tdd.tp.Constants.*;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(PORT);
        Writer writer = new Console();
        try {
            while (true) {
                String game = server.setUp();
                server.initializeGame(game);
            }
        } catch (ExitException e) {
            server.terminate();
            writer.write("Goodbye!");
        }
    }
}
