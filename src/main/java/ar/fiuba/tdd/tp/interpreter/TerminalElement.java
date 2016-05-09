package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gg on 5/9/2016.
 */
public class TerminalElement implements IInterpreter {

    private ElementTwo element;
    private ArrayList<String> elementsToContain;

    public TerminalElement(ElementTwo element, ArrayList<String> elementsToContain) {

        this.element = element;
        this.elementsToContain = elementsToContain;

    }

    public boolean interpret() {
        for (ElementTwo element : this.element.getElementList()) {
            if (elementsToContain.contains(element.getName()) == false) {
                return false;
            }
        }
        return true;
    }
}
