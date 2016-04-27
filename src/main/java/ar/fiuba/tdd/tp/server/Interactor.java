package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.connection.SimpleSocket;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;

import java.net.Socket;

public class Interactor extends SimpleSocket implements Runnable {

    private String game;
    volatile boolean terminate = false;

    public Interactor(Socket socket, String game) {
        super();
        this.connection = socket;
        this.game = game;
    }

    private String getResponse() {
        return "How can I help you?";
    }

    public void run() {
        //TODO: Inicializar juego -> crea motor, etc...
        String msg = "";
        try {
            this.write("Welcome to game '" + game + "'!"); //Envio mensaje de bienvenida
            while (! msg.equals("exit") && ! terminate) {
                msg = this.read();
                String returnCode = this.getResponse();
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
