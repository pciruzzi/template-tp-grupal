package ar.fiuba.tdd.tp.games;

public class OpenDoor2 extends Game {


    public OpenDoor2() {
//        name = "open door 2";
//        gameWon = false;
//        this.description = "El open door 2 consiste en...";
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
