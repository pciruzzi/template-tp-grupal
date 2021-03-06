package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.driver.DriverImplementation;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.GameLoadFailedException;
import ar.fiuba.tdd.tp.driver.PlayerJoinFailedException;
import ar.fiuba.tdd.tp.server.queue.Dequeuer;
import ar.fiuba.tdd.tp.server.queue.EventQueue;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameSocket implements Runnable {

    private int port;
    private String gameFilePath;
    private Writer writer;
    private List<Thread> interactorThreads;
    private List<InteractorStatus> interactors;
    private ServerSocket socket;
    private GameDriver driver;
    private EventQueue queue;
    private Dequeuer dequeuer;
    private Thread dequeuerThread;

    public GameSocket(int port, String gameFilePath) {
        this.port = port;
        this.gameFilePath = gameFilePath;
        this.writer = new Console();
        this.interactorThreads = new ArrayList<>();
        this.interactors = Collections.synchronizedList(new ArrayList<>());
        this.socket = null;
        this.queue = new EventQueue();
        this.driver = new DriverImplementation(this.queue);
    }

    public void run() {
        String gameName = getGameName();
        try {
            socket = new ServerSocket(port);
            writer.write(gameName + " loaded and listening on port " + port);
            try {
                driver.initGame(this.gameFilePath);
            } catch (GameLoadFailedException e) {
                writer.writeError(e.toString() + ": Couldn't load game from file.");
            }

            dequeuer = new Dequeuer(this.interactors, this.queue, this.driver);
            dequeuerThread = new Thread(dequeuer);
            dequeuerThread.start();

            GameCloser closer = new GameCloser(this, dequeuer);
            Thread closerThread = new Thread(closer);
            closerThread.start();

            while (true) {
                acceptConnections(gameName);
            }
        } catch (SocketException e) {
            writer.write("Socket in port " + port + " has been closed.");
        } catch (IOException e) {
            writer.writeError("Unable to create game " + gameName + " in port " + port);
        }
    }

    private String getGameName() {
        int lastSlash = this.gameFilePath.lastIndexOf('/');
        String gameName = this.gameFilePath.substring(lastSlash + 1);
        return gameName.replace("Configuration.jar", "");
    }

    private void acceptConnections(String gameName) throws IOException {
        writer.write("Waiting for connections in port " + port);
        Socket connection = socket.accept();
        writer.write("Connection received in port " + port);
        try {
            int playerNumber = driver.joinPlayer();
            Interactor interactor = new Interactor(connection, playerNumber, this.queue, gameName);
            InteractorStatus interactorStatus = new InteractorStatus(interactor);
            interactor.addStatus(interactorStatus);
            Thread thread = new Thread(interactor);
            thread.start();
            interactors.add(interactorStatus);
            interactorThreads.add(thread);
        } catch (PlayerJoinFailedException e) {
            writer.write(e.getMsg());
            ConnectionRejector rejector = new ConnectionRejector(connection);
            Thread thread = new Thread(rejector);
            thread.start();
        }
    }

    public void terminate() {
        try {
            for (InteractorStatus interactor : interactors) {
                interactor.getInteractor().terminate();
            }
            dequeuer.terminate();
            for (Thread thread : interactorThreads) {
                thread.interrupt();
            }
            dequeuerThread.interrupt();
            socket.close();
        } catch (IOException e) {
            writer.writeError("Error when trying to close socket");
        }
    }
}
