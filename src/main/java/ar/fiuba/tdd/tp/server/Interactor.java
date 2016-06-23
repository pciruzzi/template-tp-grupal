package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.connection.SimpleSocket;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;
import ar.fiuba.tdd.tp.server.queue.CommandPlayer;
import ar.fiuba.tdd.tp.server.queue.EventQueue;

import java.net.Socket;

import static ar.fiuba.tdd.tp.Constants.*;

public class Interactor extends SimpleSocket implements Runnable {

    volatile boolean terminate = false;
    private int playerNumber;
    private String gameName;
    private boolean exitedGame;
    private EventQueue queue;
    private InteractorStatus status;

    public Interactor(Socket socket, int playerNumber, EventQueue queue, String gameName) {
        super();
        this.connection = socket;
        this.playerNumber = playerNumber;
        this.queue = queue;
        this.exitedGame = false;
        this.gameName = gameName;
    }

    public void run() {
        try {
            this.write("Welcome to game " + this.gameName + ", you are player " + playerNumber + "!"); //Envio mensaje de bienvenida
            CommandPlayer newPlayer = new CommandPlayer(playerNumber, "");
            newPlayer.setNewPlayer();
            queue.push(newPlayer);
            while (this.isAlive()) {
                String msg = this.read();
                CommandPlayer message = new CommandPlayer(playerNumber, msg);
                queue.push(message);
                if (msg.equals(EXIT)) {
                    Thread.sleep(1000); //Para que pueda enviar la devolucion del exit
                    exitedGame = true;
                }
            }
        } catch (InterruptedException e) {
            writer.write("Interactor: " + e.toString());
        } catch (ConnectionException e) {
            writer.writeError(e.getMsg());
        } finally {
            this.closeConnection();
        }
    }

    public void addStatus(InteractorStatus status) {
        this.status = status;
    }

    public boolean isAlive() {
        return (! exitedGame && ! terminate && ! status.getHasWon() && ! status.getHasLost());
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public void terminate() {
        this.terminate = true;
    }
}
