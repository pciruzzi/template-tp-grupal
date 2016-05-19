package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.TrueExpression;
import ar.fiuba.tdd.tp.model.Game;

public class MovePlayerTo extends ICommand {
    private Game game;
    private IInterpreter condition;

    public MovePlayerTo(Game game, String name) {
        this.game = game;
        this.name = name;
        this.correctMovementMessage = "You have crossed";
        this.incorrectMovementMessage = "Ey! You can't do that! The ";
        this.auxiliarMessage = " is locked";
        this.condition = new TrueExpression();
    }

    public MovePlayerTo(Game game, IInterpreter condition, String name) {
        this(game, name);
        this.condition = condition;
    }

    public String doAction(Element element) {
        if (condition.interpret()) {
            //Saco al al player de la "habitación" que estaba antes.
            game.getPlayerPosition().removeElement(game.getPlayer());
            //A la "habitación" que tengo como objetivo, le agrego el player.
            element.getObjectiveElement().addElement(game.getPlayer());
            //Al player le seteo a la habitación donde tiene que ir.
            game.setPlayerPosition(element.getObjectiveElement());
            return correctMovementMessage;
        }
        return incorrectMovementMessage + element.getName() + auxiliarMessage;
    }
}
