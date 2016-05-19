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

    public String doAction( Element auxElement, Element originElement, Element destinationElement) {

        List<Element> elementListOrigin = originElement.getElementList();

        if ( elementListOrigin.size() == 0 ) {
            return auxiliarMessage;
        }

        elementListOrigin.sort(comparator);
        Element movingElementOrigin = elementListOrigin.get(0);

        if ( checkConditions(movingElementOrigin, destinationElement) ) {

            originElement.removeElement(movingElementOrigin);
            destinationElement.addElement(movingElementOrigin);
            return correctMovementMessage;
        }
        return incorrectMovementMessage;
    }

    public String doAction(Element element) {

        return incorrectMovementMessage;
    }
}
