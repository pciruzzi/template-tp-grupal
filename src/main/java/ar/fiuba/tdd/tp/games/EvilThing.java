package ar.fiuba.tdd.tp.games;

public class EvilThing extends Game {

    public EvilThing() {
//        gameWon = false;
//        name = "evil thing";
//        this.description = "El evil thing consiste en...";
    }

    @Override
    public Game copy() {
        return new EvilThing();
    }

    @Override
    public void createGame() {

//        console.write("Evil Thing game was created.");
    }
}
