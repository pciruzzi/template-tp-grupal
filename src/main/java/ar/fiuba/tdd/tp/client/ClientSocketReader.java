package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;
import static ar.fiuba.tdd.tp.Constants.SOMEONE_WON;

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
                if (response.equals(GAME_WON) || response.equals(GAME_LOST) || response.contains(SOMEONE_WON)) {
                    gameFinished = true;
                }
            }
            writer.write("ClientSocketReader: Termino el juego o aprete exit");
        } catch (ConnectionException e) {
            writer.writeError("Connection exception en ClientSocketReader");
            writer.writeError(e.getMsg());
        } finally {
            socket.closeConnection();
        }
    }

    public boolean getGameFinished() {
        return this.gameFinished;
    }
}
