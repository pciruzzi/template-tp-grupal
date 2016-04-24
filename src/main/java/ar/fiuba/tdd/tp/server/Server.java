package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.CommandReader;
import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.Writer;
import ar.fiuba.tdd.tp.exceptions.ExitException;

public class Server {

    private int port;
    private int portOffset;
    private Writer writer;
    //TODO: map<String, Game>

    public Server(int port) {
        this.port = port;
        this.portOffset = 0;
        this.writer = new Console();
    }

    public String setUp() throws ExitException {
        writer.write("Write the command 'load game' to begin");
        String command = CommandReader.readCommand("load game ");
        String game = command.replaceAll("^load game ", "");
        //TODO: Ver que se pueda crear ese juego
        portOffset++;
        return game;
    }

    public void initializeGame(String game) {
        Runnable runnable = new GameSocket(port + portOffset, game);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
