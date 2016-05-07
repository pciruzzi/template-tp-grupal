package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;

public class Pick extends ICommand {

    public Pick() {
        name = "pick";
    }

    @Override
    public void doAction(ElementTwo element) {

        element.setState(false);

    }
}
