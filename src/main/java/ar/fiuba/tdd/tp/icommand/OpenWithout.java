package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.games.Game;

public class OpenWithout extends ICommand {

//    Game game;

    public OpenWithout(Game game) {
        name = "open";
//        this.game = game;
    }

    @Override
    void doAction(Element element){

    }
}