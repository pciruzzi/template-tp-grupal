package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;

import java.util.List;

public abstract class TerminalExpression implements IInterpreter {

    protected Element element;
    protected List<String> elementsListNames;
    protected String failMessage;

    public TerminalExpression(Element element, List<String> elementsNames) {
        this.element = element;
        this.elementsListNames = elementsNames;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getFailMessage() {
        return this.failMessage;
    }
}
