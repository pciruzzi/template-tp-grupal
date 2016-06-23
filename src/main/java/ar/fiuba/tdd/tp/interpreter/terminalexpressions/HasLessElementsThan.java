package ar.fiuba.tdd.tp.interpreter.terminalexpressions;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.Optional;

public class HasLessElementsThan extends TerminalExpression {

    private int amountOfElements;

    public HasLessElementsThan(Optional<Element> element, int amountOfElements) {
        super(element, null);
        this.amountOfElements = amountOfElements;
    }

    public boolean interpret(Player player) {
        if (element.isPresent()) {
            return element.get().getElementMap().size() < amountOfElements;
        } else {
            return player.getElementMap().size() < amountOfElements;
        }
    }
}
