package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;

import java.util.Comparator;
import java.util.List;

public class MoveWithComparator extends ICommand {

//    private IInterpreter condition;
    private Comparator<Element> comparator;

//    public MoveWithComparator(String name, IInterpreter condition, Comparator<Element> comparator) {
//        this.name = name;
//        this.comparator = comparator;
//        this.condition = condition;
//    }

    public MoveWithComparator(String name, Comparator<Element> comparator) {
        this.name = name;
        this.comparator = comparator;
//        this.condition = new TrueExpression();
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
            return "The stack from where you are trying to move is empty";
        }

        elementListOrigin.sort(comparator);
        Element movingElementOrigin = elementListOrigin.get(0);

        if ( checkConditions(movingElementOrigin, destinationElement) ) {

            originElement.removeElement(movingElementOrigin);
            destinationElement.addElement(movingElementOrigin);
            return "Ok";
        }
        return "You can't move that way";
    }

    public String doAction(Element element) {

        return "Que injusta es la vida";
    }
}
