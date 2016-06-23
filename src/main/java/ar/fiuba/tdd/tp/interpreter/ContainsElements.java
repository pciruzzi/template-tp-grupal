package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.ArrayList;

public class ContainsElements extends TerminalExpression {

    public ContainsElements(Element element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
        failMessage = "FAILED";
    }

    public boolean interpret() {
        return element.hasAllElements(elementsListNames);
    }

    public boolean interpret(Player player) {
        return player.hasAllElements(elementsListNames);
    }
}
