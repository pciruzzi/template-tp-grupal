package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;

public class Move extends ICommand {

    private IInterpreter condition;

    public Move(String name) {
        this.name = name;
        this.correctMovementMessage = "You moved the ";
        this.incorrectMovementMessage = "You can't do that ";
        this.condition = new TrueExpression();
    }

    public Move(String name, IInterpreter condition) {
        this(name);
        this.condition = condition;
    }

    public String doAction(Element originElement, Element movingElement, Element destinationElement) {
        if (condition.interpret()) {
            originElement.removeElement(movingElement);
            destinationElement.addElement(movingElement);
            return correctMovementMessage;
        }
        return incorrectMovementMessage;
    }

    public String doAction(Element element) {
        return incorrectMovementMessage;
    }
}
