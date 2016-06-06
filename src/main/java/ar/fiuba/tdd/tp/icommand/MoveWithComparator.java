package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

import java.util.Comparator;
import java.util.List;

public class MoveWithComparator extends ICommand {

    private Comparator<Element> comparator;

    public MoveWithComparator(String name, Comparator<Element> comparator) {
        this.name = name;
        this.comparator = comparator;
    }

    private boolean checkConditions(Element movingElementOrigin, Element destinationElement) {
        List<Element> elementListDestination = destinationElement.getElementList();
        if ( elementListDestination.size() == 0 ) {
            return true;
        }
        elementListDestination.sort(comparator);
        Element movingElementDestination = elementListDestination.get(0);
        int comparation = comparator.compare(movingElementOrigin, movingElementDestination);
        return comparation < 0;
    }

    public String doAction( Element auxElement, Element originElement, Element destinationElement, int playerId) {
        List<Element> elementListOrigin = originElement.getElementList();
        if ( elementListOrigin.size() == 0 ) {
            return auxiliarMessage;
        }
        elementListOrigin.sort(comparator);
        Element movingElementOrigin = elementListOrigin.get(0);
        if ( checkConditions(movingElementOrigin, destinationElement) ) {

            originElement.removeElement(movingElementOrigin);
            destinationElement.addElement(movingElementOrigin);
            checkBiggestAvailable(movingElementOrigin);
            return correctMovementMessage;
        }
        return incorrectMovementMessage;
    }

    public String doAction(Element element, int playerId) {
        return incorrectMovementMessage;
    }

    // Esto se usa para el caso en que se necesite agarrar el ultimo disco, cuaderno o lo que sea de
    // la pila. Solo va a estar visible cuando se hayan removido todos los otros de esa pila.
    // TODO: Revisar con lo de multiples estados.
    private void checkBiggestAvailable(Element originElement) {
        List<Element> elementListOrigin = originElement.getElementList();
        if ( elementListOrigin.size() == 1 ) {
            elementListOrigin.get(0).setState(true);
        }
    }


}
