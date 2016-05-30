package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.model.Game;

public class MoveFromPlayer extends ICommand {
    private Game game;
    private String element;

    public MoveFromPlayer(String name, Game game, String element) {
        this.game = game;
        this.name = name;
        this.element = element;
        this.correctMovementMessage = " has just stolen your ";
        this.incorrectMovementMessage = "Hi!";
        this.auxiliarMessage = "Hi!\n" + "The ";
    }

    public String doAction(Element element) {
        Element player = game.getPlayer();
        //Si el jugador tiene el elemento en el inventario.
        if (player.getElementMap().containsKey(this.element)) {
            Element actualElement = player.getElement(this.element);
            //Saca el elemento del player.
            player.removeElement(actualElement);
            //Setea el elemento en false, no es visible.
            actualElement.setState(false);
            //Agrega el elemento al elemento con el que interactuo.
            element.addElement(actualElement);
            return auxiliarMessage + element.getName() + correctMovementMessage + this.element;
        } else {
            //Si el jugador no tiene el elemento.
            return incorrectMovementMessage;
        }
    }
}
