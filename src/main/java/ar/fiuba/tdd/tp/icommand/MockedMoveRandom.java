package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.time.TimeCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockedMoveRandom extends MoveRandom {

    private Element destinationElement;

    public MockedMoveRandom(String name, Element destinationElement) {
        super(name);
        this.correctMovementMessage = "The ";
        this.incorrectMovementMessage = " is locked.";
        this.auxiliarMessage = " moved to the ";
        this.destinationElement = destinationElement;
    }

    public String doTimeAction(Player elementToMove) {

            Element containingElement = elementToMove.getPlayerPosition();

            // Saco el elemento del lugar origen y lo paso al destino
            containingElement.removeElement(elementToMove);
            destinationElement.addElement(elementToMove);
            elementToMove.setPlayerPosition(destinationElement);

            return auxiliarMessage + elementToMove.getName() + " moved to the " + destinationElement.getName();
    }

}
