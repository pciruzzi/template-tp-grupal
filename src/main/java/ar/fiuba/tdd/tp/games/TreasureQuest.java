package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.State;

import java.util.ArrayList;

public class TreasureQuest extends Game {


    public TreasureQuest() {
        losingState = new State();
        name = "treasure quest";
        gameWon = false;
        this.description = "I'm sure you have played this game at least once when you were young... just try to remember";
        finalStatesList = new ArrayList<State>();
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
