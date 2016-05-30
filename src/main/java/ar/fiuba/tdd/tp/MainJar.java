package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.console.*;
import ar.fiuba.tdd.tp.driver.*;

import static ar.fiuba.tdd.tp.Constants.*;

public class MainJar {

    public static void main(String[] args) throws Exception {
        String gameFilePath = CommandReader.readCommand("load game ");
        gameFilePath = gameFilePath.replaceAll("^load game ", "");

        GameDriver driver = new DriverImplementation();

        Reader reader = new Console();
        Writer writer = new Console();
        try {
            driver.initGame(gameFilePath);
            String msg = "";
            String returnCode = "";
            while (! returnCode.equals(GAME_WON) && ! returnCode.equals(GAME_LOST) && ! msg.equals(EXIT)) {
                msg = reader.read();
                returnCode = driver.sendCommand(msg);
                writer.write(returnCode);
            }
        } catch (GameLoadFailedException e) { // catch de la runtime exception lanzada en el driver.sendCommand
            writer.writeError(e.toString());
        }
    }
}
