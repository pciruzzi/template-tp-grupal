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
        //TODO: Lee de consola, pero no es a prueba de errores respecto de ip y puerto
        System.out.println("Write the command 'connect <ip:port>'");
        boolean loadOk = false;
        String connect = "";
        while (! loadOk) {
            String command = reader.read();
            String[] commandSplitted = command.split(" ");
            if (commandSplitted[0].equals("connect")) { //TODO: Condición más compleja...
                loadOk = true;
                if (commandSplitted.length > 1) {
                    connect = commandSplitted[1];
                }
            } else {
                System.out.println("Command unknown... Try again!");
            }
        }
        String[] ipPort = connect.split(":");
        String host = ipPort[0]; //127.0.0.1
        int port = Integer.parseInt(ipPort[1]); //TODO: Ojo si no es un numero, o si no es del tipo ip:port
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