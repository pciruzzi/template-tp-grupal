package ar.fiuba.tdd.tp.games;

public class TreasureQuest extends Game {


    public TreasureQuest() {
//        name = "treasure quest";
//        gameWon = false;
//        this.description = "El treasure quest consiste en...";
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
