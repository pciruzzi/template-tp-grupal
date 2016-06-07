package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.ArrayList;

public class DoesNotContainElements extends TerminalExpression {

    public DoesNotContainElements(Element element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
    }

    public boolean interpret() {
        return !element.hasAllElements(elementsListNames);
    }

    public boolean interpret(Player player) {
        return !player.hasAllElements(elementsListNames);
    }
}
