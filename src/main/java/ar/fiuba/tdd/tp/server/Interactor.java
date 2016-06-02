package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.connection.SimpleSocket;
import ar.fiuba.tdd.tp.driver.*;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;

import java.net.Socket;

import static ar.fiuba.tdd.tp.Constants.*;

public class Interactor extends SimpleSocket implements Runnable {

    volatile boolean terminate = false;
    private int playerNumber;
    private EventQueue queue;

    public Interactor(Socket socket, int playerNumber, EventQueue queue) {
        super();
        this.connection = socket;
        this.playerNumber = playerNumber;
        this.queue = queue;
    }

    public void run() {
        try {
            String msg = "";
            String returnCode = "";
            this.write("Welcome to game xxxxxxxxx, you are player " + playerNumber + "!"); //Envio mensaje de bienvenida
            while (! msg.equals(EXIT) && ! terminate && ! returnCode.equals(GAME_WON) && ! returnCode.equals(GAME_LOST)) {
                msg = this.read();
                CommandPlayer message = new CommandPlayer(playerNumber, msg);
                queue.push(message);
            }
        } catch (ConnectionException e) {
            writer.writeError(e.getMsg());
        } finally {
            this.closeConnection();
        }
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public void terminate() {
        this.terminate = true;
    }
}
