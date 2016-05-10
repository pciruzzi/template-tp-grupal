package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.model.Game;

import java.util.ArrayList;


public class MoveToPlayer extends ICommand {
    private Game game;
    private IInterpreter condition;

    public MoveToPlayer(Game game) {
        this.game = game;
        name = "pick";
        this.condition = new TrueExpression();
    }

    public MoveToPlayer(Game game, IInterpreter condition) {
        this.game = game;
        name = "pick";
        this.condition = condition;
    }


    public String doAction(ElementTwo element) {
        if (this.condition.interpret()) {
            ElementTwo playerPosition = game.getPlayerPosition();
            ElementTwo player = game.getPlayer();
            playerPosition.removeElement(element);
            player.addElement(element);
            return "There you go";
        } else {
            return "You cant do that";
        }

    }
}
