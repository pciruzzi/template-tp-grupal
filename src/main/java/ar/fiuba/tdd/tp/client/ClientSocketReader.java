package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;

import static ar.fiuba.tdd.tp.Constants.*;

public class ClientSocketReader implements Runnable {

    private Writer writer;
    private SocketClient socket;
    private Client client;
    private boolean gameFinished;

    public ClientSocketReader(Writer writer, SocketClient socket, Client client) {
        this.writer = writer;
        this.socket = socket;
        this.client = client;
        this.gameFinished = false;
    }

    public void run() {
        try {
            while (! gameFinished && ! client.getExitedGame()) {
                String response = socket.read();
                writer.write(response);
                if (isGameFinished(response)) {
                    gameFinished = true;
                }
            }
        } catch (ConnectionException e) {
            writer.writeError(e.getMsg());
        } finally {
            socket.closeConnection();
        }
    }

    private boolean isGameFinished(String response) {
        return response.equals(GAME_WON) || response.equals(GAME_LOST) || response.contains(SOMEONE_WON) || response.equals(REJECTED);
    }

    public boolean getGameFinished() {
        return this.gameFinished;
    }
}
