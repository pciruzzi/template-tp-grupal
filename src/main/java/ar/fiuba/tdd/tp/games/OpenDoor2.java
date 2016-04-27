package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;

public class OpenDoor2 extends Game {


    public OpenDoor2() {
//        console = new Console();
//        name = "open door 2";
//        gameWon = false;
    }

    @Override
    public Game copy() {
        return new OpenDoor2();
    }

    @Override
    public void createGame() {

//        console.write("Open Door 2 game was created.");

    }
}
