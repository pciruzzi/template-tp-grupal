package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

public class Move extends ICommand {

    private Game game;
    private IInterpreter condition;

    public Move(Game game, String name) {
        this.game = game;
        this.name = name;
        this.condition = new TrueExpression();
    }

    public Move(Game game, IInterpreter condition, String name) {
        this.game = game;
        this.name = name;
        this.condition = condition;
    }

    public String doAction(Element originElement, Element movingElement, Element destinationElement) {
        if (condition.interpret()) {
            originElement.removeElement(movingElement);
            destinationElement.addElement(movingElement);
            return "Ok";
        }
        return "Error";
    }

    public String doAction(Element element) {

        return "Que injusta es la vida";
    }
}
