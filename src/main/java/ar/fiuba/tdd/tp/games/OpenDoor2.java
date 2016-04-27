package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;

public class OpenDoor2 extends Game {


    public OpenDoor2() {
        name = "open door 2";
    }

    @Override
    public Game clone() {
        return new OpenDoor2();
    }

    @Override
    public void createGame() {

        Console console = new Console();
        console.write("Open Door 2 game was created.");

    }
}
