package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

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

    public String doAction(Element element, int playerId) {
        Player player = game.getPlayer(playerId);
        if (this.condition.interpret(player)) {
            element.changeElementsState("visible",state);
            returnMessage = affectPlayer(element, player);
            if (state) {
                return "The " + element.getName() + correctMovementMessage + returnMessage;
            } else {
                return "The " + element.getName() + auxiliarMessage + returnMessage;
            }
        }
        return incorrectMovementMessage;
    }

    // Abro el openableElement si tengo el elementToUse
    //                ^                        ^
    //                |                        |
    // Ej:  open    chest       with          key
    public String doAction(Element playerPosition, Element openableElement, Element elementToUse, int playerId) {
        String returnMessage;
        Element player = game.getPlayer(playerId);
        if (player.hasElement(elementToUse.getName())) {
            returnMessage = doAction(openableElement, playerId);
        } else {
            returnMessage = incorrectMovementMessage + "You haven't got the " + elementToUse.getName() + ".";
        }
        return returnMessage;
    }
}