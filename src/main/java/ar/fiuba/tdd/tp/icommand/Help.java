package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.model.Game;

public class Help extends ICommand {

    private Game game;

    public Help(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public String doAction(Element element) {
        return game.getDescription();
    }
}
