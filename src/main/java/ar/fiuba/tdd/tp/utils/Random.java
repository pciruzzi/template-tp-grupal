package ar.fiuba.tdd.tp.utils;


import ar.fiuba.tdd.tp.engine.Element;

import java.util.List;

public class Random {

    public int getRandomRoom(List<Element> doors) {

        java.util.Random random = new java.util.Random();
        return random.nextInt(doors.size());
    }


}
