package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;

public class Close extends ICommand{

    public Close() {
        name = "close";
    }

    @Override
    public void doAction(ElementTwo element) {

        element.setState(false);

    }
}
