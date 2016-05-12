package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;


public class MovePlayerTo extends ICommand {

    private Game game;
    private IInterpreter condition;

    public MovePlayerTo(Game game, String name) {
        this.game = game;
        this.name = name;
        this.condition = new TrueExpression();
    }

    public MovePlayerTo(Game game, IInterpreter condition, String name) {
        this.game = game;
        this.name = name;
        this.condition = condition;
    }

    public String doAction(ElementTwo element) {
        if (condition.interpret()) {
            game.getPlayerPosition().removeElement(game.getPlayer());
            element.getObjetiveElement().addElement(game.getPlayer());
            game.setPlayerPosition(element.getObjetiveElement());
            return "You have crossed";
        }
        return "Error";

    }

}
