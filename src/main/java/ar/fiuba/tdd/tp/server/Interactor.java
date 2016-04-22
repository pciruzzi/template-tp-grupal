package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.connection.SimpleSocket;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;

import java.net.Socket;

public class Interactor extends SimpleSocket implements Runnable {

    private String game;

    public Interactor(Socket socket, String game) {
        this.connection = socket;
        this.game = game;
    }

    private String getResponse() {
        String response = game.concat("How can I help you?"); //Solo para que no chille por no usar game
        return response;
    }

    public void run() {
        //TODO: Inicializar juego -> crea motor, etc...
        String msg = "";
        try {
            while (! msg.equals("exit")) {
                msg = this.read();
                String returnCode = this.getResponse();
                this.write(returnCode);
            }
        } catch (ConnectionException e) {
            System.out.println(e.getMsg());
        } finally {
            this.closeConnection();
        }
    }
}
