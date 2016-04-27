package ar.fiuba.tdd.tp.connection.client;

import ar.fiuba.tdd.tp.connection.SimpleSocket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient extends SimpleSocket {

    public SocketClient() {
        super();
        connection = null;
    }

    public void connect(InetAddress address, int port) throws IOException {
        connection = new Socket(address, port);
    }
}
