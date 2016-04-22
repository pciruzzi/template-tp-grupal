package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.ConsoleReader;
import ar.fiuba.tdd.tp.Reader;
import ar.fiuba.tdd.tp.connection.TCPInformation;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;
import ar.fiuba.tdd.tp.exceptions.ConnectionLostException;
import ar.fiuba.tdd.tp.exceptions.ReadingException;
import ar.fiuba.tdd.tp.exceptions.WritingException;

import java.io.IOException;
import java.net.InetAddress;

public class Client {

    private SocketClient socket;
    private Reader reader;

    public Client() {
        socket = new SocketClient();
        reader = new ConsoleReader();
    }

    public TCPInformation readServerIPAndPort() {
        //TODO: Esto debería leer de consola con el reader algo del estilo ip:port
        String host = "localhost"; //127.0.0.1
        int port = 8080;
        return new TCPInformation(host, port);
    }

    public void connect(TCPInformation tcpInfo) throws IOException {
        InetAddress address = InetAddress.getByName(tcpInfo.getIPaddress());
        socket.connect(address, tcpInfo.getPort());
    }

    public void playGame() {
        String command = "";
        try {
            while (! command.equals("exit")) {
                command = this.readFromInput();
                this.writeToSocket(command);
                this.readFromSocket();
            }
        } catch (ConnectionException e) {
            System.err.println(e.getMsg());
        } finally {
            socket.closeConnection();
        }
    }

    private String readFromInput() {
        return reader.read();
    }

    private void writeToSocket(String command) throws WritingException {
        socket.write(command);
    }

    private void readFromSocket() throws ConnectionLostException, ReadingException {
        String response = socket.read();
        System.out.println(response);
    }
}