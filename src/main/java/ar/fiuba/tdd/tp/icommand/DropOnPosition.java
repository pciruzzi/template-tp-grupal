package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.model.Game;

import java.util.ArrayList;

/**
 * Created by gg on 5/10/2016.
 */
public class DropOnPosition extends ICommand {

    private Game game;

    public DropOnPosition(Game game) {
        this.game = game;
        name = "drop";
        //this.condition = new DoesNotContainElements(new ElementTwo("pepe", true), new ArrayList<>());
    }

    public String doAction(ElementTwo element) {
        ElementTwo player = game.getPlayer();
        ElementTwo position = game.getPlayerPosition();
        player.removeElement(element);
        position.addElement(element);
        return "ok";
    }
}
