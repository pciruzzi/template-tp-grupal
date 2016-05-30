package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

public class MovePlayerTo extends ICommand {
    private Game game;
    private IInterpreter condition;

    public MovePlayerTo(Game game, String name) {
        this.game = game;
        this.name = name;
        this.correctMovementMessage = "You have crossed";
        this.incorrectMovementMessage = "Ey! You can't do that!";
        this.auxiliarMessage = " is locked";
        this.condition = new TrueExpression();
    }

    public MovePlayerTo(Game game, IInterpreter condition, String name) {
        this(game, name);
        this.condition = condition;
    }

    public String doAction(Element element) {
        if (condition.interpret()) {
            game.getPlayerPosition().removeElement(game.getPlayer());
            element.getObjectiveElement().addElement(game.getPlayer());
            game.setPlayerPosition(element.getObjectiveElement());
            return correctMovementMessage;
        }
        return condition.getFailMessage();
    }

    public String doAction(Element playerPosition, Element elementToOpen, Element element) {
        String returnMessage;
        if (game.getPlayer().hasElement(element.getName())) {
            returnMessage = doAction(elementToOpen);
        } else {
            returnMessage = condition.getFailMessage() + ". You haven't got the " + element.getName() + ".";
        }
        return returnMessage;
    }
}
