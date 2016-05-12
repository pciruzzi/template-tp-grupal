package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.model.Game;

public class MoveToPlayer extends ICommand {
    private Game game;
    private IInterpreter condition;

    public MoveToPlayer(String name, Game game) {
        this.game = game;
        this.name = name;
        this.condition = new TrueExpression();
    }

    public MoveToPlayer(String name, Game game, IInterpreter condition) {
        this.game = game;
        this.name = name;
        this.condition = condition;
    }

    public String doAction(ElementTwo element) {
        if (this.condition.interpret()) {
            ElementTwo playerPosition = game.getPlayerPosition();
            ElementTwo player = game.getPlayer();
            playerPosition.removeElement(element);
            player.addElement(element);
            return "Ok.";
        } else {
            return "You can't do that.";
        }

    }
}
