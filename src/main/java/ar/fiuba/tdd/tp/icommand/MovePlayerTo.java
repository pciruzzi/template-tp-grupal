package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

public class MovePlayerTo extends ICommand {
    private Game game;
    private IInterpreter condition;

    public MovePlayerTo(Game game) {
        this.game = game;
        this.condition = new TrueExpression();
    }

    public String doAction(ElementTwo element) {
        if (condition.interpret()) {
            game.setPlayerPosition(element);
            return "You have crossed";
        }
        return "Error";

    }

}
