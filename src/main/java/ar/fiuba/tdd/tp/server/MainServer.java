package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.ConsoleReader;
import ar.fiuba.tdd.tp.Reader;

import static ar.fiuba.tdd.tp.Constants.*;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(PORT);
        Reader reader = new ConsoleReader();
        while (! reader.read().equals("exit")) {
            String game = server.setUp();
            server.initializeGame(game);
        }
    }
}
