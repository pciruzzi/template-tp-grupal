package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.model.Game;

public class MoveToPlayer extends ICommand {
    private Game game;
    private IInterpreter condition;

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
    }

    //Return true if the player had an antidote and had been healed.
    private boolean checkInventoryForAntidote(Game game) {
        if ( game.getPlayer().getElementMap().containsKey("antidote") ) {
            game.getPlayer().getElementMap().remove("antidote");
            game.getPlayer().setPoisoned(false);
            return true;
        }
        return false;
    }

    public String doAction(Element element) {
        if (this.condition.interpret()) {
            //Si esta en el piso o dentro de algun elemento del lugar
            if (checkAvailableElement(game, element)) {
                Element playerPosition = game.getPlayerPosition();
                Element player = game.getPlayer();
                playerPosition.removeElement(element);
                element.setState(false);
//<<<<<<< HEAD
                if (!player.addElement(element)) {
                    return "You can't do that, the " + player.getName() + " is full";
                }
//=======
                player.addElement(element);

                //todo aca deberia ver para avisar que lo envenene o cure.
                checkElementForPoisonAndAntidote(element, player);
                return correctMovementMessage + element.getName();
//                return "Ok.";
//>>>>>>> doingPoison
            } else {
                //No esta en el piso
                return auxiliarMessage + element.getName() + ".";
            }
        } else {
            return incorrectMovementMessage;
        }
    }

    private void checkElementForPoisonAndAntidote(Element element, Element player) {
        if (element.isPoisoned()) {
            player.setPoisoned(true);
            element.setPoisoned(false);
        }
        if (player.isPoisoned()) {
            checkInventoryForAntidote(game);
        }
    }

    private boolean checkAvailableElement(Game game, Element element) {
        return (game.getPlayerPosition().getVisibleElements().containsKey(element.getName()));
    }
}
