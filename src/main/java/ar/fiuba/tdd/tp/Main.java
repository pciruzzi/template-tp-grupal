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
        GameBuilder configuration = new DummyGameConfiguration();

        engine.createGame(configuration);
        engine.createPlayer();
        engine.createPlayer();

        Reader reader = new Console();
        Writer writer = new Console();
        writer.write("You can start playing now...");
        String input = "";
        String returnCode = "";
        while (! input.equals("exit") && ! returnCode.equals(GAME_WON) && !returnCode.equals(GAME_LOST)) {
            input = reader.read();
            //Hay que enviar commandos del tipo 0look around o 1pick stick
            String idStr = input.substring(0,1);
            String command = input.substring(1);
            int id = Integer.parseInt(idStr);

            returnCode = engine.doCommand(id, command);
            writer.write(returnCode);
        }
    }
}
