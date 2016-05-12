package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;

import java.util.ArrayList;
import java.util.List;

public class ContainsElements extends TerminalExpression{

    public ContainsElements(ElementTwo element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
    }

    public boolean interpret() {
        List<ElementTwo> elementList = this.element.getElementList();
        if (elementList.size() == 0) {
            return false;
        }

        for (String elementName : elementsListNames) {
            boolean encontrado = false;
            for (ElementTwo element : elementList) {
                if(element.getName().equals(elementName)) {
                   encontrado = true;
                }
            }
            if (!encontrado) return false;
        }

//        for (ElementTwo element : elementList) {
//            if (elementsListNames.contains(element.getName()) == false) {
//                return false;
//            }
//        }
        return true;
    }
}
