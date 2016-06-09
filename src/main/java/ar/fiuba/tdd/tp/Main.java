package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.console.*;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.*;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.server.queue.EventQueue;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;

public class Main {
    public static void main(String[] args) throws Exception {
        BroadcastQueue queue = new EventQueue();
        Engine engine = new Engine(queue);
        GameBuilder fetchConfiguration = new TheEscape2Configuration();
        engine.createGame(fetchConfiguration);
        engine.createPlayer(0);

        Reader reader = new Console();
        Writer writer = new Console();
        writer.write("You can start playing now...");
        String input = "";
        String returnCode = "";
        while (! input.equals("exit") && ! returnCode.equals(GAME_WON) && !returnCode.equals(GAME_LOST) ) {
            input = reader.read();
            int id = 0;
            returnCode = engine.doCommand(id,input);
            writer.write(returnCode);
        }

    }
}
