package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.DriverImplementation;
import ar.fiuba.tdd.tp.GameDriver;
import ar.fiuba.tdd.tp.connection.SimpleSocket;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.exceptions.ConnectionException;
import ar.fiuba.tdd.tp.model.GameBuilder;

import java.net.Socket;

public class Interactor extends SimpleSocket implements Runnable {

    private String gameFilePath;
    volatile boolean terminate = false;
    private Engine engine;
//    private GameDriver driver;

    public Interactor(Socket socket, String gameFilePath) {
        super();
        this.connection = socket;
        this.gameFilePath = gameFilePath;
        this.engine = new Engine();
//        this.driver = new DriverImplementation();
    }

    private String getResponse(String msg) {
        return engine.doCommand(msg);
    }

    public void run() {
        String msg = "";
//        driver.initGame(this.gameFilePath);
        engine.createGame(this.loadGame()); //--
        try {
            //this.write("Welcome to game '" + gameFilePath + "'!"); //Envio mensaje de bienvenida
            while (! msg.equals("exit") && ! terminate && ! engine.isGameWon()) {
                msg = this.read();
                String returnCode = this.getResponse(msg);
//                String returnCode = driver.sendCommand(msg);
                this.write(returnCode);
            }
        } catch (ConnectionException e) {
            writer.writeError(e.getMsg());
        } finally {
            this.closeConnection();
        }
    }

    private GameBuilder loadGame() { //--
        try {
            return BuilderLoader.load(this.gameFilePath);
        } catch (Exception e) { //Acá no debería entrar porque fue chequeado antes en el Server
            writer.writeError("Sorry but i can't load that file, again! :(");
            return null;
        }
    }

    public void terminate() {
        this.terminate = true;
    }
}
