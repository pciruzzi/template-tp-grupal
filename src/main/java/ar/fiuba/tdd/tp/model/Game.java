package ar.fiuba.tdd.tp.model;

//import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;

import java.util.*;

public abstract class Game {

    private Map<String, ElementTwo> elementMap;
    private String name;
    private String description;
    private boolean gameWon;

    public Game(String name, String description, Map<String, ElementTwo> elementMap) {
        this.name = name;
        this.description = description;
        this.elementMap = elementMap;
        this.gameWon = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getGameWon() {
        return this.gameWon;
    }

    public String getDescription() {
        return this.description;
    }

    public String play(String cmd, String element) {
        String returnMessage;
        if (elementMap.containsKey(element)) {
            ElementTwo actualElement = elementMap.get(element);
            returnMessage = actualElement.doCommand(cmd);
        } else {
            returnMessage = "It doesn't exist a " + element + " in the game " + getName();
        }
        returnMessage = update(returnMessage);
        return returnMessage;
    }

    private String update(String returnMessage) {
        //TODO: ver como comparar contra el estado final para ver si gano/perdio o sigue jugando.
        return returnMessage;
    }
}
