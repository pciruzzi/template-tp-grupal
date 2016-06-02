package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;


public class PrintMessage extends ICommand {

    private String printMessage;

    public PrintMessage(String message) {
        this.printMessage = message;
    }

    public String doAction(Element element) {
        return printMessage;
    }
}
