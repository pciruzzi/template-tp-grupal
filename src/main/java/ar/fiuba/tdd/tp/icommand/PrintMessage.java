package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;


public class PrintMessage extends ICommand {

    private String printMessage;

    public PrintMessage(String name, String message) {
        this.printMessage = message;
        this.name = name;
    }

    public String doAction(Element element, int playerID) {
        return printMessage;
    }
}
