package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.connection.SimpleSocket;
import ar.fiuba.tdd.tp.exceptions.WritingException;

import java.net.Socket;

import static ar.fiuba.tdd.tp.Constants.REJECTED;

public class ConnectionRejector extends SimpleSocket implements Runnable {

    public ConnectionRejector(Socket socket) {
        super();
        this.connection = socket;
    }

    @Override
    public void run() {
        try {
            this.write(REJECTED);
        } catch (WritingException e) {
            e.printStackTrace();
        }
    }
}
