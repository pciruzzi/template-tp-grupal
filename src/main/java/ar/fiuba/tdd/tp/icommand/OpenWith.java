package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.games.Game;

public class OpenWith extends ICommand {

//    Game game;

    public OpenWith(Game game) {
        name = "open";
//        this.game = game;
    }

    @Override
    public void doAction(ElementTwo element) {


    }
}
