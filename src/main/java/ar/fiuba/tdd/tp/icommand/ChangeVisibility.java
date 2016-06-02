package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

import static ar.fiuba.tdd.tp.Constants.ANTIDOTED;
import static ar.fiuba.tdd.tp.Constants.POISONED;

public class ChangeVisibility extends ICommand {

    private IInterpreter condition;
    private boolean state;
    private Game game;
    private String returnMessage;

    public ChangeVisibility(String name, boolean state, Game game) {
        this.name = name;
        this.state = state;
        this.correctMovementMessage = " is opened.";
        this.incorrectMovementMessage = "You can't do that.";
        this.auxiliarMessage = " is closed!.";
        this.condition = new TrueExpression();
        this.game = game;
        returnMessage = "";
    }

    public ChangeVisibility(String name, boolean state, IInterpreter condition, Game game) {
        this(name, state, game);
        this.condition = condition;
    }

    public String doAction(Element element) {
        if (this.condition.interpret()) {
            element.changeElementsState("visible",state);
            checkPoison(element);
            if (state) {
                return "The " + element.getName() + correctMovementMessage + returnMessage;
            } else {
                return "The " + element.getName() + auxiliarMessage + returnMessage;
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

    private void checkPoison(Element element) {
        if (element.hasState("poison")) {
            game.getPlayer().changeState("poison",true);
            returnMessage = POISONED;
        }
        if (game.getPlayer().hasState("poison")) {
            if ( game.checkInventoryForAntidote() ) {
                returnMessage += ANTIDOTED;
            }
        }
    }
}