package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

import static ar.fiuba.tdd.tp.Constants.ANTIDOTED;
import static ar.fiuba.tdd.tp.Constants.POISONED;
import static sun.audio.AudioPlayer.player;

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
        String returnMessage = "";
        if (this.condition.interpret()) {
            element.changeElementsState(state);
            if (element.isPoisoned()) {
                game.getPlayer().setPoisoned(true);
                returnMessage = POISONED;
            }
            if (game.getPlayer().isPoisoned()) {
                game.checkInventoryForAntidote();
//                checkInventoryForAntidote(game);
                returnMessage += ANTIDOTED;
            }
            if (state) {
                return "The " + element.getName() + correctMovementMessage + returnMessage;
            } else {
                return "The " + element.getName() + auxiliarMessage + returnMessage;
            }
        }
        return incorrectMovementMessage;
    }

    public String doAction(Element element1, Element element2) {
        return this.doAction(element1);
    }
}