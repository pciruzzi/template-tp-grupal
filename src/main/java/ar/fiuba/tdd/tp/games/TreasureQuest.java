package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;

public class TreasureQuest extends Game {


    public TreasureQuest() {
        name = "treasure quest";
    }

    @Override
    public Game clone() {
        return new TreasureQuest();
    }

    @Override
    public void createGame() {

        Console console = new Console();
        console.write("Treasure Quest game was created.");
    }
}
