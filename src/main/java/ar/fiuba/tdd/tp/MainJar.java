package ar.fiuba.tdd.tp;

public class MainJar {

    public static void main(String[] args) throws Exception {
        String gameFilePath = CommandReader.readCommand("load game ");
        gameFilePath = gameFilePath.replaceAll("^load game ", "");

        GameDriver driver = new DriverImplementation();
        driver.initGame(gameFilePath);

        Reader reader = new Console();
        Writer writer = new Console();
        String msg = "";
        while (! msg.equals("exit") /*&& ! driver.isGameWon()*/) {
            msg = reader.read();
            String returnCode = driver.sendCommand(msg);
            writer.write(returnCode);
        }
    }
}
