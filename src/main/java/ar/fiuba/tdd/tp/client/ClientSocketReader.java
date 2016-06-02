package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;

public class ClientSocketReader implements Runnable {

    private Writer writer;
    private SocketClient socket;
    private boolean gameFinished;

    public ClientSocketReader(Writer writer, SocketClient socket) {
        this.writer = writer;
        this.socket = socket;
        this.gameFinished = false;
    }

    public void run() {
        try {
            while (! gameFinished) {
                String response = socket.read();
                writer.write(response);
                if (response.equals(GAME_WON) || response.equals(GAME_LOST)) {
                    gameFinished = true;
                }
            }
        } catch (ConnectionException e) {
            writer.writeError(e.getMsg());
        } finally {
            socket.closeConnection();
        }
    }

    public boolean getGameFinished() {
        return this.gameFinished;
    }
}
