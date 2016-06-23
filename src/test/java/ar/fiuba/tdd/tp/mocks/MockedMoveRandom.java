package ar.fiuba.tdd.tp.mocks;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.icommand.MoveRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockedMoveRandom extends MoveRandom {

    String roomNeeded;

    public MockedMoveRandom(String name, String roomToMove) {
        super(name);
        this.roomNeeded = roomToMove;
    }



    private String sentToOtherRoom(Player elementToMove, List<Element> doors, int amountOfDoors) {

        for(Element doorToUse : doors) {
            if ( doorToUse.getObjectiveElement().getName().equals(roomNeeded) ) {
                Element next = doorToUse.getObjectiveElement();

                return doMove(elementToMove, next);
            }
        }

        //Si no encontro la habitación por la cual quiero.
        return correctMovementMessage + elementToMove.getName() + incorrectMovementMessage;

    }

}
