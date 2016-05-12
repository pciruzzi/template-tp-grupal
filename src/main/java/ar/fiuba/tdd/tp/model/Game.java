package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.*;

import static ar.fiuba.tdd.tp.Constants.GAME_WON;

public class Game {

    private Element player;
    private Element playerPosition;
    private Map<String,Element> visibleElements;
    private IInterpreter winInterpreter;
    private String name;
    private String description;
    private boolean gameWon;

    public Game(String name) {
        this.name = name;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public  String play(String cmd) {
        String returnMessage;
        returnMessage = playerPosition.doCommand(cmd);
        return returnMessage;
    }

    public String play(String cmd, String element) {
        String returnMessage;
        this.calculateVisibleElements();
        if (visibleElements.containsKey(element)) {
            Element actualElement = visibleElements.get(element);
            returnMessage = actualElement.doCommand(cmd);
        } else if (player.getElementMap().containsKey(element)) {
            Element actualElement = player.getElementMap().get(element);
            returnMessage = actualElement.doCommand(cmd);
        } else {
            returnMessage = "It doesn't exist a " + element + " in the game " + getName();
        }

        returnMessage = checkGameWon(returnMessage);
        return returnMessage;
    }

    public String play(String cmd, String element, String destinationElement) {
        String returnMessage;
        this.calculateVisibleElements();
        if (visibleElements.containsKey(element)) {
            Element actualElement = visibleElements.get(element);
            Element destElement = visibleElements.get(destinationElement);
            returnMessage = actualElement.doCommand(cmd, playerPosition, destElement);
        } else {
            returnMessage = "It doesn't exist a " + element + " in the game " + getName();
        }
        if (this.hasWon()) {
            gameWon = true;
            return "You won!!!";
        }
        return returnMessage;
    }

    private String checkGameWon(String returnMessage) {
        if (this.hasWon()) {
            gameWon = true;
            return GAME_WON;
        }
        return returnMessage;
    }

    private boolean hasWon() {
        return winInterpreter.interpret();
    }

    public void setWinInterpreter(IInterpreter winInterpreter) {
        this.winInterpreter = winInterpreter;
    }

    public Element getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Element playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Element getPlayer() {
        return player;
    }

    public void setPlayer(Element player) {
        this.player = player;
    }

    private void calculateVisibleElements() {
        Map<String, Element> elements;
        elements = playerPosition.getVisibleElements();
        elements.putAll(player.getVisibleElements());
        visibleElements = elements;
    }

    public Map<String,Element> getCurrentPositionElements() {
        this.calculateVisibleElements();
        return visibleElements;
    }

    public List<Element> getVisibleElementList() {
        this.calculateVisibleElements();
        List<Element> returnList = new ArrayList<Element>(visibleElements.values());
        return returnList;
    }
}
