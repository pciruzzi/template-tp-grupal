package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.games.Game;

public abstract class ICommand {

    String name;

    public abstract void doAction(ElementTwo element);

    public String getName() {
        return name;
    }

}
