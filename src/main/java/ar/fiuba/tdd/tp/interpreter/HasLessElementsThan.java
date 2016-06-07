package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

public class HasLessElementsThan extends TerminalExpression{

    private int amountOfElements;

    public HasLessElementsThan(Element element, int amountOfElements) {
        super(element, null);
        this.amountOfElements = amountOfElements;
    }

    @Override
    public boolean interpret() {
        return element.getElementMap().size() < amountOfElements;
    }

    public boolean interpret(Player player) {
        return player.getElementMap().size() < amountOfElements;
    }
}
