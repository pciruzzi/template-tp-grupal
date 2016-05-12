package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.model.Game;

public class DropOnPosition extends ICommand {

    private Game game;

    public DropOnPosition(String name, Game game) {
        this.game = game;
        this.name = name;
    }

    public String doAction(Element element) {
        Element player = game.getPlayer();
        Element position = game.getPlayerPosition();
        //Si esta en el inventario
        if (player.getElementMap().containsKey(element.getName())) {
            player.removeElement(element);
            element.setState(true);
            position.addElement(element);
            return "Ok.";
        } else {
            //no esta en el inventario
            return "You can't drop the " + element.getName() + ".";
        }
    }
}
