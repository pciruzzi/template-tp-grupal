package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;

import java.util.Comparator;
import java.util.List;

public class Check extends ICommand {

//    private IInterpreter condition;
    private Comparator<Element> comparator;

    public Check(String name, Comparator<Element> comparator) {
        this.name = name;
        this.comparator = comparator;
//        this.condition = new TrueExpression();
    }

//    public Check(String name, IInterpreter condition, Comparator<Element> comparator) {
//        this.name = name;
//        this.comparator = comparator;
//        this.condition = condition;
//    }

    public String doAction(Element element) {

        List<Element> elementList = element.getElementList();

        if (elementList.size() == 0) {
            return incorrectMovementMessage;
        }

        elementList.sort(comparator);

        return correctMovementMessage + elementList.get(0).getSize() + ".";
    }
}
