package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

public class ChangeVisibility extends ICommand {

    private IInterpreter condition;
    private boolean state;
    private Game game;

    public ChangeVisibility(String name, boolean state, Game game) {
        this.name = name;
        this.state = state;
        this.correctMovementMessage = " is opened.";
        this.incorrectMovementMessage = "You can't do that.";
        this.auxiliarMessage = " is closed!.";
        this.condition = new TrueExpression();
        this.game = game;
    }

    public ChangeVisibility(String name, boolean state, IInterpreter condition, Game game) {
        this(name, state, game);
        this.condition = condition;
    }

    public String doAction(Element element) {
        if (this.condition.interpret()) {
            element.changeElementsState(state);
            if (element.isPoisoned()) {
                game.getPlayer().setPoisoned(true);
            }
            if (state) {
                return "The " + element.getName() + correctMovementMessage;
            } else {
                return "The " + element.getName() + auxiliarMessage;
            }
        }
        return incorrectMovementMessage;
    }

    public String doAction(Element playerPosition, Element openableElement, Element element) {
        String returnMessage;
        Element player = game.getPlayer();
        if (player.hasElement(element.getName())) {
            returnMessage = doAction(openableElement);
        } else {
            returnMessage = incorrectMovementMessage + "You haven't got the " + element.getName() + ".";
        }
        return returnMessage;
    }
}