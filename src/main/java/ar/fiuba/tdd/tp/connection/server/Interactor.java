package ar.fiuba.tdd.tp.connection.server;

import ar.fiuba.tdd.tp.connection.SimpleSocket;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.FetchConfiguration;

import java.net.Socket;

public class Interactor extends SimpleSocket implements Runnable {

    private String game;
    volatile boolean terminate = false;
    private Engine engine;

    public Interactor(Socket socket, String game, Engine engine) {
        super();
        this.connection = socket;
        this.game = game;
        this.engine = engine;
    }

    private String getResponse(String msg) {
        return engine.doCommand(msg);
    }

    public void run() {
        String msg = "";
        engine.createGame(new FetchConfiguration());
        try {
            this.write("Welcome to game '" + game + "'!"); //Envio mensaje de bienvenida
            while (! msg.equals("exit") && ! terminate && ! engine.isGameWon()) {
                msg = this.read();
                String returnCode = this.getResponse(msg);
                this.write(returnCode);
            }
        } catch (ConnectionException e) {
            writer.write(e.getMsg());
        } finally {
            this.closeConnection();
        }
    }

    public void terminate() {
        this.terminate = true;
    }
}
