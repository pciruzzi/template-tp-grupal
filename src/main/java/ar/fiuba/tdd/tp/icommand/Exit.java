package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

public class Exit extends ICommand {

    public Exit(String name) {
        this.name = name;
    }

    public String doAction(Element element) {
        return "You exit the game. Goodbye!";
    }
}

