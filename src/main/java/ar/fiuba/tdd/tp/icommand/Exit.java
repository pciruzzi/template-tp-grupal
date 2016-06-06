package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

import static ar.fiuba.tdd.tp.Constants.EXIT;

public class Exit extends ICommand {

    public Exit() {
        this.name = EXIT;
    }

    public String doAction(Element element, int playerId) {
        return "You exit the game. Goodbye!";
    }
}

