package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.interpreter.logic.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

import java.util.ArrayList;

public class MoveToPlayer extends ICommand {
    private Game game;
    private IInterpreter condition;
    private String returnMessage;

    public MoveToPlayer(String name, Game game, IInterpreter condition) {
        this(name, game);
        this.condition = condition;
    }

    public MoveToPlayer(String name, Game game) {
        this.game = game;
        this.name = name;
        this.correctMovementMessage = "You picked the ";
        this.incorrectMovementMessage = "You can't do that.";
        this.auxiliarMessage = "You can't take the ";
        this.condition = new TrueExpression();
        this.returnMessage = "";
    }

    public String doAction(Element element, int playerId) {
        Player player = game.getPlayer(playerId);
        if (this.condition.interpret(player)) {
            //Si esta en el piso o dentro de algun elemento del lugar
            if (checkAvailableElement(element, playerId)) {
                Element playerPosition = game.getPlayerPosition(playerId);
                if (!player.addElement(element)) {
                    return "You can't do that, the " + player.getName() + " is full";
                }
                playerPosition.removeElement(element);
                // Lo saco del objeto que lo contenia
                removeElement(element, playerPosition);
                element.changeState("visible",false);
                returnMessage = affectPlayer(element,  player);
                return correctMovementMessage + element.getName() + returnMessage;
            } else {
                //No esta en el piso
                return auxiliarMessage + element.getName() + ".";
            }
        } else {
            return condition.getFailMessage();
        }
    }

    private void removeElement(Element elementToRemove, Element elementToRemoveFrom ) {
        ArrayList<String> elementToRemoveArray = new ArrayList<>();
        elementToRemoveArray.add(elementToRemove.getName());
        for ( Element containerElement : elementToRemoveFrom.getElementList()) {
            if ( containerElement.hasAllElements(elementToRemoveArray) && containerElement.getValueOfState("visible") ) {
                containerElement.removeElement(elementToRemove);
            } else if ( containerElement.getValueOfState("visible") && containerElement.getElementList().size() != 0 ) {
                removeElement(elementToRemove, containerElement);
            }
        }
    }

    private boolean checkAvailableElement(Element element, int playerId) {
        return (game.getPlayerPosition(playerId).getVisibleElements().containsKey(element.getName()));
    }
}
