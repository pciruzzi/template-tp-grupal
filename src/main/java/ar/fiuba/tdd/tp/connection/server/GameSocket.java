package ar.fiuba.tdd.tp.connection.server;

import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.Writer;
import ar.fiuba.tdd.tp.engine.Engine;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class GameSocket implements Runnable {

    private int port;
    private String game;
    private Writer writer;
    private List<Thread> threads;
    private List<Interactor> interactors;
    private ServerSocket socket;

    public GameSocket(int port, String game) {
        this.port = port;
        this.game = game;
        this.writer = new Console();
        this.threads = new ArrayList<Thread>();
        this.interactors = new ArrayList<Interactor>();
        this.socket = null;
    }

    public void run() {
        try {
            socket = new ServerSocket(port);
            writer.write(game + " loaded and listening on port " + port);
            while (true) {
                writer.write("Waiting for connections in port " + port);
                Socket connection = socket.accept();
                writer.write("Connection received in port " + port);
                Engine engine = new Engine();
                Interactor runnable = new Interactor(connection, this.game, engine);
                Thread thread = new Thread(runnable);
                thread.start();
                interactors.add(runnable);
                threads.add(thread);
            }
        } catch (SocketException e) {
            writer.write("Socket in port " + port + " has been closed.");
        } catch (IOException e) {
            writer.writeError("Unable to create game in port " + port);
        }
    }

    public void terminate() {
        try {
            for (Interactor interactor : interactors) {
                interactor.terminate();
            }
            for (Thread thread : threads) {
                thread.interrupt();
            }
            socket.close();
        } catch (IOException e) {
            writer.writeError("Error when trying to close socket");
        }
    }
}
