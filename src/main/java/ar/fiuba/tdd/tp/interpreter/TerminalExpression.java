package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.List;
import java.util.Optional;

public abstract class TerminalExpression implements IInterpreter {

    protected Optional<Element> element;
    protected List<String> elementsListNames;
    protected String failMessage;

    public TerminalExpression(Optional<Element> element, List<String> elementsNames) {
        this.element = element;
        this.elementsListNames = elementsNames;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getFailMessage() {
        return this.failMessage;
    }

    protected String getElementName(Element element, Player player) {
        String elementName = element.getName();
        if (elementName.contains("player")) {
            elementName = "player " + player.getPlayerID();
        }
        return elementName;
    }
}
