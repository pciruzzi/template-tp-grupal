package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.console.*;
import ar.fiuba.tdd.tp.exceptions.ExitException;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.tp.server.BuilderLoader.*;

public class Server {

    private int port;
    private int portOffset;
    private Writer writer;
    private List<Thread> threads;
    private List<GameSocket> sockets;

    public Server(int port) {
        this.port = port;
        this.portOffset = 0;
        this.writer = new Console();
        this.threads = new ArrayList<>();
        this.sockets = new ArrayList<>();
    }

    public String setUp() throws ExitException {
        writer.write("Write the command 'load game <path-to-jar-file>' to begin");
        //String command = CommandReader.readCommand("load game ");
        String command = "load game ar/fiuba/tdd/tp/model/TheEscape2Configuration.jar"; //TODO: Borrar
        return command.replaceAll("^load game ", "");
    }

    public void initializeGame(String gameFilePath) {
        if (this.canCreate(gameFilePath)) {
            portOffset++;
            GameSocket runnable = new GameSocket(port + portOffset, gameFilePath);
            Thread thread = new Thread(runnable);
            thread.start();
            sockets.add(runnable);
            threads.add(thread);
        }
    }

    private boolean canCreate(String gameFilePath) {
        try {
            return load(gameFilePath) != null;
        } catch (Exception e) {
            writer.writeError("Sorry but i can't load that file! :(");
            return false;
        }
    }

    public void terminate() {
        for (GameSocket socket : sockets) {
            socket.terminate();
        }
        for (Thread thread : threads) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                writer.writeError("Thread interrupted");
            }
        }
    }
}
