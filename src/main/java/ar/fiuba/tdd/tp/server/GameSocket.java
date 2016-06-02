package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.driver.DriverImplementation;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class GameSocket implements Runnable {

    private int port;
    private int playerNumber;
    private String gameFilePath;
    private Writer writer;
    private List<Thread> threads;
    private List<Interactor> interactors;
    private ServerSocket socket;
    private GameDriver driver;
    private EventQueue queue;

    public GameSocket(int port, String gameFilePath) {
        this.port = port;
        this.playerNumber = 0;
        this.gameFilePath = gameFilePath;
        this.writer = new Console();
        this.threads = new ArrayList<>();
        this.interactors = new ArrayList<>();
        this.socket = null;
        this.driver = new DriverImplementation();
        this.queue = new EventQueue();
    }

    public void run() {
        try {
            socket = new ServerSocket(port);
            writer.write(gameFilePath + " loaded and listening on port " + port);
            try {
                driver.initGame(this.gameFilePath);
            } catch (GameLoadFailedException e) {
                writer.writeError(e.toString() + ": Couldn't load game from file.");
            }

            Dequeuer dequeuer = new Dequeuer(this.interactors, this.queue, this.driver);
            Thread dequeuerThread = new Thread(dequeuer);
            dequeuerThread.start();

            while (true) {
                acceptConnections();
            }
        } catch (SocketException e) {
            writer.write("Socket in port " + port + " has been closed.");
        } catch (IOException e) {
            writer.writeError("Unable to create game in port " + port);
        }
    }

    private void acceptConnections() throws IOException {
        writer.write("Waiting for connections in port " + port);
        Socket connection = socket.accept();
        writer.write("Connection received in port " + port);
        //driver.addPlayer();
        Interactor runnable = new Interactor(connection, this.playerNumber, this.queue);
        playerNumber++;
        Thread thread = new Thread(runnable);
        thread.start();
        interactors.add(runnable);
        threads.add(thread);
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
