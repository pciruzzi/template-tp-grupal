package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

import ar.fiuba.tdd.tp.interpreter.*;

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

    public String doAction(Element playerPosition, Element movingElement, Element destinationElement) {
        if (condition.interpret()) {
            Element player = game.getPlayer();
            if (player.hasElement(movingElement.getName())) {
                player.removeElement(movingElement);
            } else {
                playerPosition.removeElement(movingElement);
                movingElement.setState(false);
            }
            destinationElement.addElement(movingElement);
            return correctMovementMessage;
        }
        return incorrectMovementMessage;
    }

    public String doAction(Element element) {
        return incorrectMovementMessage;
    }
}
