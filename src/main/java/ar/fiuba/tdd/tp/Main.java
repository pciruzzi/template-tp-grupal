package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.console.*;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.*;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;


public class Main {
    public static void main(String[] args) throws Exception {

        Engine engine = new Engine();
        //OpenDoorConfiguration servirá para los dos OpenDoor
        //Se le va a pasar un booleano que es isOpenDoor2
        //Si isOpenDoor2 = false -> juego OpenDoor, si = true -> juego OpenDoor2
        //boolean isOpenDoor2 = true;
        //GameBuilder fetchConfiguration = new OpenDoorConfiguration(isOpenDoor2);
//        GameBuilder fetchConfiguration = new HanoiConfiguration();
        GameBuilder fetchConfiguration = new PoisonConfiguration();
        engine.createGame(fetchConfiguration);

        Reader reader = new Console();
        Writer writer = new Console();
        writer.write("You can start playing now...");
        String input = "";
        String returnCode = "";
        while (! input.equals("exit") && ! returnCode.equals(GAME_WON) && !returnCode.equals(GAME_LOST) ) {
            input = reader.read();
            returnCode = engine.doCommand(input);
            writer.write(returnCode);
        }
    }
}
