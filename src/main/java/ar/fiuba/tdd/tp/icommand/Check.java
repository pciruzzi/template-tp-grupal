package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

import java.util.Comparator;
import java.util.List;

public class Check extends ICommand {

    private Comparator<Element> comparator;

    public Check(String name, Comparator<Element> comparator) {
        this.name = name;
        this.comparator = comparator;
    }

    public String doAction(Element element, int playerId) {
        List<Element> elementList = element.getElementList();
        if (elementList.size() == 0) {
            return incorrectMovementMessage;
        }
        elementList.sort(comparator);
        return correctMovementMessage + elementList.get(0).getSize() + ".";
    }
}
