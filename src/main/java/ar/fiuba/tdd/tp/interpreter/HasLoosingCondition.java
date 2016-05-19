package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;

public class HasLoosingCondition extends TerminalExpression {

    private boolean state;

    public HasLoosingCondition(Element element, boolean state) {
        super(element, null);
        this.state = state;
    }

    @Override
    public boolean interpret() {
        return element.isPoisoned() == state;
    }
}
