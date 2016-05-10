package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.ElementTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gg on 5/9/2016.
 */
public class DoesNotContainElements extends TerminalExpression {

    public DoesNotContainElements(ElementTwo element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
    }

    public boolean interpret() {
        ContainsElements containsElements = new ContainsElements(this.element, this.elementsListNames);
        return !(containsElements.interpret());
    }
}
