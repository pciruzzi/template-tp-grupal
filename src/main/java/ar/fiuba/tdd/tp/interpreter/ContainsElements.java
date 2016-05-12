package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;

import java.util.ArrayList;
import java.util.List;

public class ContainsElements extends TerminalExpression{

    public ContainsElements(Element element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
    }

    public boolean interpret() {
        List<Element> elementList = this.element.getElementList();
        if (elementList.size() == 0) {
            return false;
        }
        for (Element element : this.element.getElementList()) {
            if (!elementsListNames.contains(element.getName())) {
                return false;
            }
        }
        return true;
    }
}
