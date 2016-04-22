package ar.fiuba.tdd.tp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameSocket implements Runnable {

    private int port;
    private String game;

    public GameSocket(int port, String game) {
        this.port = port;
        this.game = game;
    }

    public void run() {
        try {
            ServerSocket socket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for connections in port " + port);
                Socket connection = socket.accept();
                System.out.println("Connection received in port " + port);
                Runnable runnable = new Interactor(connection, game);
                Thread thread = new Thread(runnable);
                thread.start();
            }
        } catch (IOException e) {
            System.err.println("Unable to create game in port " + port);
        }
    }
}
