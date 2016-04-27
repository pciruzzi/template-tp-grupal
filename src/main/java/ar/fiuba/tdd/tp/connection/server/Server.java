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
    //TODO: map<String, Game>

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
        String game = command.replaceAll("^load game ", "");
        //TODO: Ver que se pueda crear ese juego
        portOffset++;
        return game;
    }

    public void initializeGame(String game) {
        GameSocket runnable = new GameSocket(port + portOffset, game);
        Thread thread = new Thread(runnable);
        thread.start();
        sockets.add(runnable);
        threads.add(thread);
    }

    public void terminate() {
        System.out.println("Terminando sockets del server");
        int index = 1;
        for (GameSocket socket : sockets) {
            System.out.println("    - GameSocket " + index);
            socket.terminate();
            index++;
        }
        System.out.println("Terminando threads del server");
        index = 1;
        for (Thread thread : threads) {
            System.out.println("    - Thread " + index);
            thread.interrupt();
            try {
                thread.join();
                System.out.println("      Thread joined");
            } catch (InterruptedException e) {
                System.out.println("      Thread interrupted");
            }
            index++;
        }
    }
}
