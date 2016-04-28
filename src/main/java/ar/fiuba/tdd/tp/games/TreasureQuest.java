package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;

public class TreasureQuest extends Game {


    public TreasureQuest() {
//        name = "treasure quest";
//        gameWon = false;
    }

    @Override
    public Game copy() {
        return new TreasureQuest();
    }

    @Override
    public void createGame() {
//        console.write("Treasure Quest game was created.");
    }
}
