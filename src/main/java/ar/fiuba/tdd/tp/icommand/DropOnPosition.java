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

    public String doAction(Element element, int playerId) {
        Element player = game.getPlayer();
        Element position = game.getPlayerPosition();
        //Si esta en el inventario.
        if (player.getElementMap().containsKey(element.getName())) {
            player.removeElement(element);
            element.setState(true);
            position.addElement(element);
            return correctMovementMessage + element.getName();
        }
        //No esta en el inventario.
        return incorrectMovementMessage + element.getName() + ".";
    }

    public String doAction(Element playerPosition, Element originElement, Element destinationElement, int playerId) {
        Element player = game.getPlayer();
        //Si esta en el inventario y el elemento esta abierto.
        if (checkInInventoryAndOpenElement(player, originElement, destinationElement)) {
            player.removeElement(originElement);
            originElement.setState(true);
            destinationElement.addElement(originElement);
            return correctMovementMessage + originElement.getName() + ".";
        }
        //No esta en el inventario o esta cerrado el element donde lo queres dejar.
        return incorrectMovementMessage + originElement.getName() + ".";
    }

    public boolean checkInInventoryAndOpenElement(Element player, Element originElement, Element destinationElement) {
        boolean condition1 = player.getElementMap().containsKey(originElement.getName());
        boolean condition2 = destinationElement.getState();
        return condition1 && condition2;
    }
}
