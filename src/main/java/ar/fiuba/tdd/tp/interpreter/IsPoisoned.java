package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

public class IsPoisoned extends TerminalExpression {

    private boolean state;

    public IsPoisoned(Element element, boolean state) {
        super(element, null);
        this.state = state;
    }

    @Override
    public boolean interpret() {
        return element.isPoisoned() == state;
    }

    public boolean interpret(Player player) {
        return player.isPoisoned() == state;
    }
}
