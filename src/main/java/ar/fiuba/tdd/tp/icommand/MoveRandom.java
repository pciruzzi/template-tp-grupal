package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.utils.Random;

import java.util.ArrayList;
import java.util.List;

public class MoveRandom extends ITimeCommand {

    private List<Element> prohibitedRooms;
    private Element destinationElement;
    private Element containingElement;
    private Random random;

    public MoveRandom(String name, Random random) {
        this.name = name;
        this.correctMovementMessage = "The ";
        this.incorrectMovementMessage = " is locked.";
        this.auxiliarMessage = " moved to the ";
        this.prohibitedRooms = new ArrayList<>();
        this.random = random;
    }

    public String doTimeAction(Player elementToMove) {

        containingElement = elementToMove.getPlayerPosition();

        if (destinationElement != null) {
            return doMove(elementToMove, destinationElement);
        }
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
        return sentToOtherRoom(elementToMove, doors, amountOfDoors);
    }

    private String sentToOtherRoom(Player elementToMove, List<Element> doors, int amountOfDoors) {
        if ( amountOfDoors > 0 ) {
            // Elijo una puerta al azar
            Element doorToUse = doors.get(random.getRandomRoom(doors));
            Element next = doorToUse.getObjectiveElement();

            return doMove(elementToMove, next);
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

    public void setDestinationElement(Element destinationElement) {
        this.destinationElement = destinationElement;
    }

    protected String doMove(Player elementToMove, Element destinationElement) {

        // Saco el elemento del lugar origen y lo paso al destino
        containingElement.removeElement(elementToMove);
        destinationElement.addElement(elementToMove);
        elementToMove.setPlayerPosition(destinationElement);

        return correctMovementMessage + elementToMove.getName() + auxiliarMessage + destinationElement.getName();
    }
}
