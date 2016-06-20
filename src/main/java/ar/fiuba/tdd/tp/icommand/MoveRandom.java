package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoveRandom extends ITimeCommand {

    private List<Element> prohibitedRooms;

    public MoveRandom(String name) {
        this.name = name;
        this.correctMovementMessage = "The ";
        this.incorrectMovementMessage = " is locked.";
        this.auxiliarMessage = "";
        this.prohibitedRooms = new ArrayList<>();
    }

    public String doTimeAction(Player elementToMove) {
        Element containingElement = elementToMove.getPlayerPosition();
        List<Element> visibleElements = new ArrayList<>(containingElement.getVisibleElements().values());
        List<Element> doors = new ArrayList<>();

        // Obtengo todos los elementos que tienen un objeto de destino
        for ( Element visibleElement : visibleElements ) {
            Element objectiveElement = visibleElement.getObjectiveElement();
            if (visibleElement != elementToMove && objectiveElement != null && !isProhibitedRoom(objectiveElement)) {
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

    public void addProhibitedRoom(Element room) {
        prohibitedRooms.add(room);
    }

    private boolean isProhibitedRoom(Element room) {
        boolean isProhibited = false;
        for (Element prohibitedRoom : prohibitedRooms) {
            if (prohibitedRoom == room) {
                isProhibited = true;
            }
        }
        return isProhibited;
    }
}