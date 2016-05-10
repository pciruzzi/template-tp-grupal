package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.ElementTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gg on 5/9/2016.
 */
public class ContainsElements extends TerminalExpression{

    public ContainsElements(ElementTwo element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
    }

    public boolean interpret() {
        List<ElementTwo> elementList = this.element.getElementList();
        if (elementList.size() == 0) {
            return false;
        }
        for (ElementTwo element : this.element.getElementList()) {
            if (elementsListNames.contains(element.getName()) == false) {
                return false;
            }
        }
        return true;
    }
}
