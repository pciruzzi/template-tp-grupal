package ar.fiuba.tdd.tp.model;

//import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.*;

public class Game {

    private ElementTwo player;
    private ElementTwo playerPosition;
    private HashMap<String,ElementTwo> visibleElements;
    private IInterpreter winInterpreter;
    private String name;
    private String description;
    private boolean gameWon;

    public Game(String name) {
        this.name = name;
        this.gameWon = false;
        this.description = "descripcion";
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
        this.calculateVisibleElements();
        if (visibleElements.containsKey(element)) {
            ElementTwo actualElement = visibleElements.get(element);
            returnMessage = actualElement.doCommand(cmd);
        } else {
            returnMessage = "It doesn't exist a " + element + " in the game " + getName();
        }
        if (this.hasWon()) {
            gameWon = true;
            return "You won!!!";
        }
        return returnMessage;
    }

    private boolean hasWon() {
        return winInterpreter.interpret();
    }


    public void setWinInterpreter(IInterpreter winInterpreter) {
        this.winInterpreter = winInterpreter;
    }

    public ElementTwo getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(ElementTwo playerPosition) {
        this.playerPosition = playerPosition;
    }

    public ElementTwo getPlayer() {
        return player;
    }

    public void setPlayer(ElementTwo player) {
        this.player = player;
    }

    private void calculateVisibleElements() {
        HashMap<String, ElementTwo>  elements;
        elements = playerPosition.getVisibleElements();
        elements.putAll(player.getVisibleElements());
        visibleElements = elements;
    }

    public Map<String,ElementTwo> getCurrentPositionElements() {
        this.calculateVisibleElements();
        return visibleElements;
    }

}
