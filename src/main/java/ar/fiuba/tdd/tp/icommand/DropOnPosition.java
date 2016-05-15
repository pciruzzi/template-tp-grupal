package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.model.Game;

public class DropOnPosition extends ICommand {

    private Game game;

    public DropOnPosition(String name, Game game) {
        this.game = game;
        this.name = name;
        this.correctMovementMessage = "You dropped the ";
        this.incorrectMovementMessage = "You can't drop the ";
    }

    public String doAction(Element element) {
        Element player = game.getPlayer();
        Element position = game.getPlayerPosition();
        //Si esta en el inventario.
        if (player.getElementMap().containsKey(element.getName())) {
            player.removeElement(element);
            element.setState(true);
            position.addElement(element);
            return correctMovementMessage + element.getName();
        } else {
            //No esta en el inventario.
            return incorrectMovementMessage + element.getName() + ".";
        }
    }
}
