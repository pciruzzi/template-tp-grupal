package ar.fiuba.tdd.tp.games;

public class EvilThing extends Game {

    public EvilThing() {
//        gameWon = false;
//        console = new Console();
//        name = "evil thing";
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
