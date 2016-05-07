package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.ElementTwo;

import java.util.List;

public class CommandParser {

    public ElementTwo getFirstElement(String action, List<ElementTwo> elementList) {

        ElementTwo firstElement = null;
        String elementName;
        int index;

        int minIndex = action.length();


        for ( ElementTwo element : elementList ) {
            elementName = element.getName();

            if ( action.contains( elementName ) ) {

                index = action.indexOf(elementName);
                // Se queda con el primer elemento que esta en el String action
                if ( index < minIndex) {
                    firstElement = element;
                    minIndex = index;
                }

            }
        }

        return firstElement;
    }

    public ElementTwo getSecondElement(String action, String firstElementName, List<ElementTwo> elementList) {
        // Obtengo la posicion, del ultimo caracter del nombre, del primer elemento que aparece
        // Ej: move stack1 stack2
        //               ^   <-- Obtuve esa posicion, luego me quedo con el string que sigue,
        // es decir, el nombre del segundo elemento
        int indexOfSecondElement = action.indexOf(firstElementName) + firstElementName.length();
        String secondElementName = action.substring(indexOfSecondElement);

        for ( ElementTwo element : elementList ) {
            if ( secondElementName.contains( element.getName() ) ) {
                return element;
            }
        }
        return null;
    }

    public String getCommand(String action, String firstElementName) {
        return action.substring(0, action.indexOf(firstElementName)).trim();
    }

    public String lookAround() {
        return "";
    }

    public String defaultActions(String action) {
//        switch (action) {
//            case
//        }
        return "";
    }
}
