package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.connection.TCPInformation;
import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.exceptions.ExitException;
import ar.fiuba.tdd.tp.exceptions.InvalidIPPortException;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();
        Writer writer = new Console();
        try {
            TCPInformation tcpInfo = client.readServerIPAndPort();
            client.connect(tcpInfo);
            writer.write("Connection established! You can start playing by writing...");
            client.playGame();
        } catch (ExitException e) {
            writer.write("Goodbye!");
        } catch (InvalidIPPortException e) {
            writer.writeError(e.getMsg());
        } catch (IOException e) {
            writer.writeError("Unable to connect to host.");
        }
    }
}
