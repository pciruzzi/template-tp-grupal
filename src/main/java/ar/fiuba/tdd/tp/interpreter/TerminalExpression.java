package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;

import java.util.ArrayList;

public abstract class TerminalExpression implements IInterpreter {

    protected Element element;
    protected ArrayList<String> elementsListNames;

    public TerminalExpression(Element element, ArrayList<String> elementsNames) {
        this.element = element;
        this.elementsListNames = elementsNames;
    }

}
