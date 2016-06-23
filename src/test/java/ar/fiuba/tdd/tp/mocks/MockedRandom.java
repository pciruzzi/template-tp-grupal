package ar.fiuba.tdd.tp.mocks;

import ar.fiuba.tdd.tp.utils.Random;
import ar.fiuba.tdd.tp.engine.Element;

import java.util.List;

public class MockedRandom extends Random {

    private String requiredRoom;

    public MockedRandom(String requiredRoom) {
        this.requiredRoom = requiredRoom;
    }

    public int getRandomRoom(List<Element> doors) {

        int index = 0;
        for (Element door : doors) {
            if (door.getObjectiveElement().getName().equals(requiredRoom)) {
                return index;
            } else {
                index++;
            }
        }

        return -1;
    }

}
