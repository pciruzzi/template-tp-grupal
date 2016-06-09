package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.time.TimeCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoveRandom extends ITimeCommand{

    public MoveRandom(String name) {
        this.name = name;
        this.correctMovementMessage = "The ";
        this.incorrectMovementMessage = " is locked.";
        this.auxiliarMessage = " moved to the ";
    }

    public String doTimeAction(Player elementToMove) {

        Element containingElement = elementToMove.getPlayerPosition();

        List<Element> visibleElements = containingElement.getElementList();
        List<Element> doors = new ArrayList<Element>();

        // Obtengo todos los elementos que tienen un objeto de destino
        for ( Element visibleElement : visibleElements ) {

            if ( visibleElement.getObjectiveElement() != null ) {
                doors.add(visibleElement);
            }
        }

        int amountOfDoors = doors.size();

        if ( amountOfDoors > 0 ) {
            Random random = new Random();

            // Elijo una puerta al azar
            Element doorToUse = doors.get(random.nextInt(amountOfDoors));

            Element next = doorToUse.getObjectiveElement();

            // Saco el elemento del lugar origen y lo paso al destino
            containingElement.removeElement(elementToMove);
            next.addElement(elementToMove);
            elementToMove.setPlayerPosition(next);

            return auxiliarMessage
                    + correctMovementMessage + elementToMove.getName() + " moved to the " + next.getName();
        } else {
            return correctMovementMessage + elementToMove.getName() + incorrectMovementMessage;
        }

    }
}
