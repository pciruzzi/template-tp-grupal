package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.connection.TCPInformation;
import ar.fiuba.tdd.tp.console.*;
import ar.fiuba.tdd.tp.exceptions.*;

import java.io.IOException;
import java.net.InetAddress;

import static ar.fiuba.tdd.tp.Constants.*;

public class Client {

    private SocketClient socket;
    private Reader reader;
    private Writer writer;
    private boolean exitedGame;

    public Client() {
        socket = new SocketClient();
        reader = new Console();
        writer = new Console();
        this.exitedGame = false;
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
        ClientSocketReader socketReader = new ClientSocketReader(writer, socket, this);
        Thread socketReaderThread = new Thread(socketReader);
        try {
            socketReaderThread.start();
            while (! exitedGame && ! socketReader.getGameFinished()) {
                String command = this.readFromInput();
                this.writeToSocket(command);
                if (command.equals(EXIT)) {
                    this.exitedGame = true;
                }
                Thread.sleep(500); //Esto es porque sino se queda esperando a leer algo cuando se ejecuto exit (y ya se perdio la conexion)
            }
        } catch (InterruptedException e) {
            writer.writeError("Client: " + e.toString());
        } catch (ConnectionException e) {
            writer.writeError(e.getMsg());
        } finally {
            try {
                socketReaderThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                socket.closeConnection();
            }
        }
    }

    private String readFromInput() {
        return reader.read();
    }

    private void writeToSocket(String command) throws WritingException {
        socket.write(command);
    }

    public boolean getExitedGame() {
        return this.exitedGame;
    }

}