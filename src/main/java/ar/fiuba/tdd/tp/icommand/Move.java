package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.interpreter.logic.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

public class Move extends ICommand {

    private IInterpreter condition;
    private Game game;

    public Move(String name, Game game, IInterpreter condition) {
        this(name, game);
        this.condition = condition;
    }

    public Move(String name, Game game) {
        this.incorrectMovementMessage = "You can't do that ";
        this.name = name;
        this.game = game;
        this.condition = new TrueExpression();
        this.correctMovementMessage = "You moved the ";
    }

    public String doAction(Element playerPosition, Element movingElement, Element destinationElement, int playerId) {
        Player player = game.getPlayer(playerId);
        if (condition.interpret(player)) {
            if (player.hasElement(movingElement.getName()) && player.hasElement(destinationElement.getName())) {
                player.removeElement(movingElement);
                destinationElement.addElement(movingElement);
                return correctMovementMessage + movingElement.getName() + " to the " + destinationElement.getName() + " in the inventory.";
            } else if (playerPosition.hasElement(movingElement.getName()) && playerPosition.hasElement(destinationElement.getName())) {
                playerPosition.removeElement(movingElement);
                movingElement.changeState("visible",false);
                destinationElement.addElement(movingElement);
                return correctMovementMessage + movingElement.getName() + " to the " + destinationElement.getName() + " on the floor.";
            }
        }
        return incorrectMovementMessage;
    }

    public String doAction(Element element, int playerId) {
        return incorrectMovementMessage;
    }
}
