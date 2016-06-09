package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;


public class PrintMessage extends ITimeCommand {

    private String printMessage;

    public PrintMessage(String name, String message) {
        this.printMessage = message;
        this.name = name;
    }

    public String doTimeAction(Player element) {
        return printMessage;
    }
}
