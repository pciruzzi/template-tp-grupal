package ar.fiuba.tdd.tp.console;

import ar.fiuba.tdd.tp.exceptions.ExitException;

public class CommandReader {

    private static Writer writer = new Console();
    private static Reader reader = new Console();

    public CommandReader() {
    }

    public static String readCommand(String command) throws ExitException {
        boolean loadOk = false;
        String lineRead = "";
        while (! loadOk) {
            lineRead = reader.read();
            if (lineRead.matches("^" + command + ".*")) {
                loadOk = true;
            } else if (lineRead.matches("^exit$")) {
                throw new ExitException("Exit command executed");
            } else {
                writer.write("Command unknown... Try again!");
            }
        }
        return lineRead;
    }
}
