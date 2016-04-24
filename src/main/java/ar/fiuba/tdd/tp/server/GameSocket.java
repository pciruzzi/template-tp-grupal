package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.Writer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameSocket implements Runnable {

    private int port;
    private String game;
    private Writer writer;

    public GameSocket(int port, String game) {
        this.port = port;
        this.game = game;
        this.writer = new Console();
    }

    public void run() {
        try {
            ServerSocket socket = new ServerSocket(port);
            writer.write(game + " loaded and listening on port " + port);
            while (true) {
                writer.write("Waiting for connections in port " + port);
                Socket connection = socket.accept();
                writer.write("Connection received in port " + port);
                Runnable runnable = new Interactor(connection, game);
                Thread thread = new Thread(runnable);
                thread.start();
            }
        } catch (IOException e) {
            writer.writeError("Unable to create game in port " + port);
        }
    }
}
