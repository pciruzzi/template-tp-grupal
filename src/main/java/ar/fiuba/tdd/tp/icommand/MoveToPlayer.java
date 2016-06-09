package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.model.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ar.fiuba.tdd.tp.Constants.ANTIDOTED;
import static ar.fiuba.tdd.tp.Constants.ANTIDOTE_PICKED;
import static ar.fiuba.tdd.tp.Constants.POISONED;

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

    public String doAction(Element element) {
        if (this.condition.interpret()) {
            //Si esta en el piso o dentro de algun elemento del lugar
            if (checkAvailableElement(game, element)) {
                Element playerPosition = game.getPlayerPosition();
                Element player = game.getPlayer();
                if (!player.addElement(element)) {
                    return "You can't do that, the " + player.getName() + " is full";
                }
                element.changeState("visible",false);
                // Lo saco del cuarto
                playerPosition.removeElement(element);
                // Lo saco del objeto que lo contenia
                removeElement(element, playerPosition);
                affectPlayer(element,  player);
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
            if ( containerElement.hasAllElements(elementToRemoveArray) && containerElement.hasState("visible") ) {
                containerElement.removeElement(elementToRemove);
            } else if ( containerElement.hasState("visible") && containerElement.getElementList().size() != 0 ) {
                removeElement(elementToRemove, containerElement);
            }
        }
    }

    private void affectPlayer(Element element, Element player) {

        State stateToAffect = element.getStateToAffect();

        if (player.hasState(stateToAffect.getName()) && player.getValueOfState(stateToAffect.getName()) != stateToAffect.isActive()) {
            player.changeState(stateToAffect.getName(), stateToAffect.isActive());
            returnMessage += "\n" + stateToAffect.getEffectMessage();
            if (stateToAffect.isWillDestroyTheItem()) {
                player.removeElement(element);
            }

            if (player.hasElement(stateToAffect.getAntiState())) {
                player.changeState(stateToAffect.getName(), !stateToAffect.isActive());
                returnMessage += "\n" + stateToAffect.getAntiEffectMessage();
                if (stateToAffect.isWillDestroyTheItem()) {
                    Element elementToRemoveOfAntiEffect = player.getElement(stateToAffect.getAntiState());
                    player.removeElement(elementToRemoveOfAntiEffect);
                }
            }

        }
    }

    private boolean checkAvailableElement(Game game, Element element) {
        return (game.getPlayerPosition().getVisibleElements().containsKey(element.getName()));
    }
}
