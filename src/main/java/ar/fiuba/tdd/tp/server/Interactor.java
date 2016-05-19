package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.connection.SimpleSocket;
import ar.fiuba.tdd.tp.driver.*;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;

import java.net.Socket;

import static ar.fiuba.tdd.tp.Constants.*;

public class Interactor extends SimpleSocket implements Runnable {

    private String gameFilePath;
    volatile boolean terminate = false;
    private GameDriver driver;

    public Interactor(Socket socket, String gameFilePath) {
        super();
        this.connection = socket;
        this.gameFilePath = gameFilePath;
        this.driver = new DriverImplementation();
    }

    public void run() {
        try {
            driver.initGame(this.gameFilePath);
            String msg = "";
            String returnCode = "";
            //this.write("Welcome to game '" + gameFilePath + "'!"); //Envio mensaje de bienvenida
            while (! msg.equals(EXIT) && ! terminate && ! returnCode.equals(GAME_WON) && ! returnCode.equals(GAME_LOST)) {
                msg = this.read();
                returnCode = driver.sendCommand(msg);
                this.write(returnCode);
            }
        } catch (ConnectionException e) {
            writer.writeError(e.getMsg());
        } catch (GameLoadFailedException e) { // catch de la exception lanzada en el driver.initGame
            writer.writeError(e.toString() + ": Couldn't load game from file.");
        } finally {
            this.closeConnection();
        }
    }

    public void terminate() {
        this.terminate = true;
    }
}
