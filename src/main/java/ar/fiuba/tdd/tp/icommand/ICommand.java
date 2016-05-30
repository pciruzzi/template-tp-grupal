package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

public abstract class ICommand {

    String name;
    String correctMovementMessage;
    String incorrectMovementMessage;
    String auxiliarMessage;

    public abstract String doAction(Element element);

    public String doAction(Element originElement, Element element, Element destinationElement) {
        return "Incorrect doAction";
    }

    public void correctMovementMessage(String message) {
        this.correctMovementMessage = message;
    }

    public void auxiliarMessage(String message) {
        this.auxiliarMessage = message;
    }

    public void incorrectMovementMessage(String message) {
        this.incorrectMovementMessage = message;
    }

    public String getName() {
        return name;
    }
}
