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
        boolean encontrado = false;

        List<ElementTwo> elementList = this.element.getElementList();
        if (elementList.size() == 0) {
            return false;
        }

        for (String elementName : elementsListNames) {
            encontrado = isEncontrado(elementList, elementName);
            if (!encontrado) {
                return false;
            }

//        for (ElementTwo element : elementList) {
//            if (elementsListNames.contains(element.getName()) == false) {
//                return false;
//            }
//        }
            //todo capaz aca haya un error, antes habia un return true
        }
        return encontrado;
    }

    private boolean isEncontrado(List<ElementTwo> elementList, String elementName) {
        boolean encontrado = false;
        for (ElementTwo element : elementList) {
            if (element.getName().equals(elementName)) {
                encontrado = true;
            }
        }
        return encontrado;
    }
}
