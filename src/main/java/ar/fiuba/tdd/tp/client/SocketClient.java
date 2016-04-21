package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.exceptions.ConnectionLostException;
import ar.fiuba.tdd.tp.exceptions.ReadingException;
import ar.fiuba.tdd.tp.exceptions.WritingException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import static ar.fiuba.tdd.tp.Constants.*;

public class SocketClient {
    Socket connection;

    public SocketClient() {
        connection = null;
    }

    public void connect(InetAddress address, int port) throws IOException {
        connection = new Socket(address, port);
    }

    public void write(String command) throws WritingException {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");
            osw.write(command + EO_MSG);
            osw.flush();
        } catch (IOException e) {
            throw new WritingException("Couldn't write to server socket.");
        }
    }

    public String read() throws ConnectionLostException, ReadingException {
        try {
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            InputStreamReader isr = new InputStreamReader(bis, "US-ASCII");
            int character;
            StringBuffer instr = new StringBuffer();
            while ((character = isr.read()) != 13) {
                instr.append((char) character);
                if (character == -1) {
                    throw new ConnectionLostException("Connection lost with server.");
                }
            }
            return instr.toString();
        } catch (IOException e) {
            throw new ReadingException("Couldn't read from client socket.");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Closing connection.");
        } catch (IOException e) {
            System.err.println("Exception when trying to close connection: " + e);
        }
    }
}
