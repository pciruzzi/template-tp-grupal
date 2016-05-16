package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.DriverImplementation;
import ar.fiuba.tdd.tp.GameDriver;
import ar.fiuba.tdd.tp.connection.SimpleSocket;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;

import java.net.Socket;

import static ar.fiuba.tdd.tp.Constants.GAME_WON;

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
        driver.initGame(this.gameFilePath);
        try {
            String msg = "";
            String returnCode = "";
            //this.write("Welcome to game '" + gameFilePath + "'!"); //Envio mensaje de bienvenida
            while (! msg.equals("exit") && ! terminate && ! returnCode.equals(GAME_WON)) {
                msg = this.read();
                returnCode = driver.sendCommand(msg);
                this.write(returnCode);
            }
        } catch (ConnectionException e) {
            writer.writeError(e.getMsg());
        } catch (RuntimeException e) { // catch de la runtime exception lanzada en el driver.sendCommand
            writer.writeError(e.toString());
        } finally {
            this.closeConnection();
        }
    }

    public void terminate() {
        this.terminate = true;
    }
}
