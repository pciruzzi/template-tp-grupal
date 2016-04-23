package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.ConsoleReader;
import ar.fiuba.tdd.tp.Reader;

import static ar.fiuba.tdd.tp.Constants.*;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(PORT);
//        Reader reader = new ConsoleReader();
        while (true/*! reader.read().equals("exit")*/) {
            System.out.println("Write the command 'load' to begin");
            String game = server.setUp();
            server.initializeGame(game);
        }
    }
}
