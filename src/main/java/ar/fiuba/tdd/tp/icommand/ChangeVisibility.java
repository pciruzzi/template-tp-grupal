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
        this.condition = new TrueExpression();
    }

    public ChangeVisibility(String name, boolean state, IInterpreter condition) {
        this.name = name;
        this.state = state;
        this.condition = condition;
    }

    public String doAction(Element element) {
        if (this.condition.interpret()) {
            element.changeElementsState(state);
            if (state) {
                return "The " + element.getName() + " is opened!.";
            } else {
                return "The " + element.getName() + " is closed!.";
            }
        }
        return "You can't do that.";
    }
}
