package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.model.Game;

public class DropOnPosition extends ICommand {

    private Game game;

    public DropOnPosition(String name, Game game) {
        this.game = game;
        this.name = name;
    }

    public String doAction(ElementTwo element) {
        ElementTwo player = game.getPlayer();
        ElementTwo position = game.getPlayerPosition();
        player.removeElement(element);
        element.setState(true);
        position.addElement(element);
        return "Ok.";
    }
}
