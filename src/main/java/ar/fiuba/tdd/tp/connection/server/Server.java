package ar.fiuba.tdd.tp.connection.server;

import ar.fiuba.tdd.tp.CommandReader;
import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.Writer;
import ar.fiuba.tdd.tp.exceptions.ExitException;

import java.util.ArrayList;
import java.util.List;

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
        this.threads = new ArrayList<Thread>();
        this.sockets = new ArrayList<GameSocket>();
    }

    public String setUp() throws ExitException {
        writer.write("Write the command 'load game' to begin");
        String command = CommandReader.readCommand("load game ");
        return command.replaceAll("^load game ", "");
    }

    public void initializeGame(String game) {
        //if (Engine.canCreate(game)) { //método static
        portOffset++;
        GameSocket runnable = new GameSocket(port + portOffset, game);
        Thread thread = new Thread(runnable);
        thread.start();
        sockets.add(runnable);
        threads.add(thread);
        //} else {
        //    writer.writeError("I can't load that game");
        //}
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
