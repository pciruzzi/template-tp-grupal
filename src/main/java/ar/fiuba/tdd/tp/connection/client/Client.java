package ar.fiuba.tdd.tp.connection.client;

import ar.fiuba.tdd.tp.CommandReader;
import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.Reader;
import ar.fiuba.tdd.tp.Writer;
import ar.fiuba.tdd.tp.connection.TCPInformation;
import ar.fiuba.tdd.tp.exceptions.*;

import java.io.IOException;
import java.net.InetAddress;

public class Client {

    private SocketClient socket;
    private Reader reader;
    private Writer writer;
    private boolean gameWon;

    public Client() {
        socket = new SocketClient();
        reader = new Console();
        writer = new Console();
        gameWon = false;
    }

    public TCPInformation readServerIPAndPort() throws ExitException, InvalidIPPortException {
        writer.write("Write the command 'connect <ip>:<port>'");
        String connect = CommandReader.readCommand("connect");
        if (connect.matches("^connect \\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}$")) {
            String parameters = connect.split(" ")[1];
            String[] ipPort = parameters.split(":");
            String host = ipPort[0]; //127.0.0.1
            int port = Integer.parseInt(ipPort[1]);
            return new TCPInformation(host, port);
        } else {
            throw new InvalidIPPortException("The IP address and port written don't match with the <ip>:<port> expected.");
        }
    }

    public void connect(TCPInformation tcpInfo) throws IOException {
        InetAddress address = InetAddress.getByName(tcpInfo.getIPaddress());
        socket.connect(address, tcpInfo.getPort());
    }

    public void playGame() {
        String command = "";
        try {
            this.readFromSocket(); //Leo mensaje de bienvenida del juego
            while (! command.equals("exit") && ! gameWon) {
                command = this.readFromInput();
                this.writeToSocket(command);
                this.readFromSocket();
            }
        } catch (ConnectionException e) {
            writer.writeError(e.getMsg());
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
        writer.write(response);
        if (response.equals("You won the game!")) {
            gameWon = true;
        }
    }
}