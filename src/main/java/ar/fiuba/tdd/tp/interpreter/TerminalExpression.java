package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gg on 5/9/2016.
 */
public abstract class TerminalExpression implements IInterpreter {

    protected ElementTwo element;
    protected ArrayList<String> elementsListNames;

    public TerminalExpression(ElementTwo element, ArrayList<String> elementsNames) {
        this.element = element;
        this.elementsListNames = elementsNames;
    }

}
