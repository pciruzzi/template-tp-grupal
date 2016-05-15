package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

public class ChangeVisibility extends ICommand {

    private IInterpreter condition;
    private boolean state;

    public ChangeVisibility(String name, boolean state) {
        this.name = name;
        this.state = state;
        this.correctMovementMessage = " is opened!.";
        this.incorrectMovementMessage = "You can't do that.";
        this.auxiliarMessage = " is closed!.";
        this.condition = new TrueExpression();
    }

    public ChangeVisibility(String name, boolean state, IInterpreter condition) {
        this(name, state);
        this.condition = condition;
    }

    public String doAction(Element element) {
        if (this.condition.interpret()) {
            element.changeElementsState(state);
            if (state) {
                return "The " + element.getName() + correctMovementMessage;
            } else {
                return "The " + element.getName() + auxiliarMessage;
            }
        }
        return incorrectMovementMessage;
    }
}
