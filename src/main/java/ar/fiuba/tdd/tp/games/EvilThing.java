package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;

public class EvilThing extends Game {

    public EvilThing(){
        name = "evil thing";
    }


    @Override
    public Game clone() {
        return new EvilThing();
    }

    @Override
    public void createGame() {
        Console console = new Console();
        console.write("Evil Thing game was created.");
    }
}
