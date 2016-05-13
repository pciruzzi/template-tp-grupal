package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.model.Game;

public class MoveToPlayer extends ICommand {
    private Game game;
    private IInterpreter condition;

    public MoveToPlayer(String name, Game game, IInterpreter condition) {
        this.game = game;
        this.name = name;
        this.condition = condition;
    }

    public MoveToPlayer(String name, Game game) {
        this.game = game;
        this.name = name;
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
            //Si esta en el piso
            if (game.getPlayerPosition().getElementMap().containsKey(element.getName())) {
                Element playerPosition = game.getPlayerPosition();
                Element player = game.getPlayer();
                playerPosition.removeElement(element);
                element.setState(false);
                player.addElement(element);

                if (element.isPoisoned()) {
                    player.setPoisoned(true);
                    element.setPoisoned(false);
                }
                if (player.isPoisoned()) {
                    checkInventoryForAntidote(game);
                }
                return "Ok.";
            } else {
                //No esta en el piso
                return "You can't take the " + element.getName() + ".";
            }
        } else {
            return "You can't do that.";
        }
    }
}
