package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class GameSocket implements Runnable {

    private int port;
    private String gameFilePath;
    private Writer writer;
    private List<Thread> threads;
    private List<Interactor> interactors;
    private ServerSocket socket;

    public GameSocket(int port, String gameFilePath) {
        this.port = port;
        this.gameFilePath = gameFilePath;
        this.writer = new Console();
        this.threads = new ArrayList<>();
        this.interactors = new ArrayList<>();
        this.socket = null;
    }

    public void run() {
        try {
            socket = new ServerSocket(port);
            writer.write(gameFilePath + " loaded and listening on port " + port);
            while (true) {
                writer.write("Waiting for connections in port " + port);
                Socket connection = socket.accept();
                writer.write("Connection received in port " + port);
                Interactor runnable = new Interactor(connection, this.gameFilePath);
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
