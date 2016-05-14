package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.model.Game;

import java.util.List;

public class MoveFromPlayer extends ICommand {
    private Game game;

    public MoveFromPlayer(String name, Game game) {
        this.game = game;
        this.name = name;
    }

    public String doAction(Element element) {
        Element player = game.getPlayer();
        List<Element> playerElementList = player.getElementList();
        //Si el jugador tiene algo en el inventario.
        if (playerElementList.size() > 0) {
            for (Element actualElement : playerElementList) {
                //Sacar todos los elementos del player
                player.removeElement(actualElement);
                //Setear los elementos en false
                actualElement.setState(false);
                //Agregar los elementos en el otro elemento
                element.addElement(actualElement);
            }
            return "The " + element.getName() + " has just stolen your object!";
        } else {
            //Si el jugador tiene el inventario vacio.
            return "Nothing happens.";
        }
    }
}
