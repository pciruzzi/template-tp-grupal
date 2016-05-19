package ar.fiuba.tdd.tp.connection;

import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.exceptions.*;

import java.io.*;
import java.net.Socket;

import static ar.fiuba.tdd.tp.Constants.*;

public class SimpleSocket {

    protected Socket connection;
    protected Writer writer;

    public SimpleSocket() {
        this.writer = new Console();
    }

    public void write(String command) throws WritingException {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(bos, ENCODING);
            osw.write(command + EO_MSG);
            osw.flush();
        } catch (IOException e) {
            throw new WritingException("Couldn't write to socket.");
        }
    }

    public String read() throws ConnectionLostException, ReadingException {
        try {
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            InputStreamReader isr = new InputStreamReader(bis, ENCODING);
            int character;
            StringBuilder msg = new StringBuilder();
            while ((character = isr.read()) != EO_MSG) {
                msg.append((char) character);
                if (character == -1) {
                    throw new ConnectionLostException("Connection lost.");
                }
            }
            return msg.toString();
        } catch (IOException e) {
            throw new ReadingException("Couldn't read from socket.");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            writer.write("Closing connection.");
        } catch (IOException e) {
            writer.writeError("Exception when trying to close connection: " + e);
        }
    }
}
