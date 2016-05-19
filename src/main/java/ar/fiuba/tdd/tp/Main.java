package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.console.*;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.*;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;

public class Main {
    public static void main(String[] args) throws Exception {

        Engine engine = new Engine();
        GameBuilder fetchConfiguration = new TheScape();
        engine.createGame(fetchConfiguration);

        Reader reader = new Console();
        Writer writer = new Console();
        writer.write("You can start playing now...");
        String input = "";
        String returnCode = "";
        while (! input.equals("exit") && ! returnCode.equals(GAME_WON) && !returnCode.equals(GAME_LOST) ) {
            input = reader.read();
            returnCode = engine.doCommand(input.toLowerCase());
            writer.write(returnCode);
        }
    }
}
