package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.games.Game;

public class Open extends ICommand{

    public Open() {
        name = "open";
    }

    @Override
    public void doAction(Element element) {
        element.setState("opened");
    }

}
