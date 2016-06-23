package ar.fiuba.tdd.tp.interpreter.terminalexpressions;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.ArrayList;
import java.util.Optional;

public class DoesNotContainElements extends TerminalExpression {

    public DoesNotContainElements(Optional<Element> element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
    }

    public boolean interpret(Player player) {
        if (element.isPresent()) {
            return !element.get().hasAllElements(elementsListNames);
        } else {
            return !player.hasAllElements(elementsListNames);
        }
    }
}
