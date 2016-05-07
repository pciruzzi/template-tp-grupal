package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.games.Game;

public abstract class ICommand {

    String name;

    abstract void doAction(Element element);

    public String getName() {
        return name;
    }

}
