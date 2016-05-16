package ar.fiuba.tdd.tp;

import static ar.fiuba.tdd.tp.Constants.GAME_WON;

public class MainJar {

    public static void main(String[] args) throws Exception {
        String gameFilePath = CommandReader.readCommand("load game ");
        gameFilePath = gameFilePath.replaceAll("^load game ", "");

        GameDriver driver = new DriverImplementation();
        driver.initGame(gameFilePath);

        Reader reader = new Console();
        Writer writer = new Console();
        try {
            String msg = "";
            String returnCode = "";
            while (! returnCode.equals(GAME_WON) && ! msg.equals("exit")) {
                msg = reader.read();
                returnCode = driver.sendCommand(msg);
                writer.write(returnCode);
            }
        } catch (Exception e) { // catch de la runtime exception lanzada en el driver.sendCommand
            writer.writeError(e.toString());
        }
    }
}
