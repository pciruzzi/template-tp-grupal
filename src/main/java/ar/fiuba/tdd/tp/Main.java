package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Engine engine = new Engine();
        GameBuilder fetchConfiguration = new OpenDoor2Configuration();
        engine.createGame(fetchConfiguration);

        Reader reader = new Console();
        Writer writer = new Console();
        writer.write("You can start playing now...");
        String input = "";
        String returnCode = "";
        while (! input.equals("exit") && !returnCode.equals("You won!!!")) {
            input = reader.read();
            returnCode = engine.doCommand(input);
            writer.write(returnCode);
        }
    }
}
