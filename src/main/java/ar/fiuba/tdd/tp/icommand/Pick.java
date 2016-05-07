package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

public class Pick extends ICommand {

    public Pick() {
        name = "pick";
    }

    @Override
    void doAction(Element element) {

        element.setState("picked");

    }
}
