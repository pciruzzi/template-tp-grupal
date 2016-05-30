package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;

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
}
