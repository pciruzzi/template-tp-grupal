package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.ConsoleReader;
import ar.fiuba.tdd.tp.Reader;
import ar.fiuba.tdd.tp.TCPInformation;
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
        //TODO: Esto debería leer de consola algo del estilo ip:port
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
        } catch (ConnectionLostException e) {
            System.err.println(e.getMsg());
        } finally {
            socket.closeConnection();
        }
    }

    private String readFromInput() {
        String command = reader.read();
        return command;
    }

    private void writeToSocket(String command) {
        try {
            socket.write(command);
        } catch (WritingException e) {
            System.err.println(e.getMsg());
        }
    }

    private void readFromSocket() throws ConnectionLostException {
        try {
            String response = socket.read();
            System.out.println(response);
        } catch (ReadingException e) {
            System.err.println(e.getMsg());
        }
    }
}