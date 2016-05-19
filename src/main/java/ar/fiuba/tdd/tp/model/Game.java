package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.*;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;

public class Game {

    private Element player;
    private Element playerPosition;
    private Map<String,Element> visibleElements;
    private IInterpreter winInterpreter;
    private IInterpreter losingInterpreter;
    private String name;
    private String description;
    private boolean gameWon;
    private boolean gameLost;

    public Game(String name) {
        this.name = name;
        this.gameLost = false;
        this.gameWon = false;
        this.description = "Descripcion basica.";
    }

    public String getName() {
        return this.name;
    }

    public boolean getGameFinished() {
        return (this.gameLost || this.gameWon);
    }

    public boolean getGameWon() {
        return this.gameWon;
    }

    public boolean getGameLost() {
        return this.gameLost;
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

        Element actualElement = getElement(element);

        if (actualElement != null) {
            returnMessage = actualElement.doCommand(cmd);
        } else {
            returnMessage = "It doesn't exist a " + element + " in the game " + getName();
        }

        returnMessage = checkFinishedGame(returnMessage);
        return returnMessage;
    }

    public String play(String cmd, String element, String destinationElement) {
        String returnMessage;
        this.calculateVisibleElements();

        Element actualElement = getElement(element);
        Element destElement = getElement(destinationElement);

        if (actualElement != null && destElement != null) {
            returnMessage = actualElement.doCommand(cmd, playerPosition, destElement);
        } else {
            returnMessage = "It doesn't exist a " + element + " in the game " + getName();
        }

        returnMessage = checkFinishedGame(returnMessage);
        return returnMessage;
    }

    private Element getElement(String element) {
        Element actualElement;
        if (visibleElements.containsKey(element)) {
            actualElement = visibleElements.get(element);
        } else if (player.getElementMap().containsKey(element)) {
            actualElement = player.getElementMap().get(element);
        } else {
            actualElement = null;
        }
        return actualElement;
    }

    private String checkFinishedGame(String returnMessage) {
        if (this.hasLost()) {
            gameLost = true;
            return GAME_LOST;
        }
        if (this.hasWon()) {
            gameWon = true;
            return GAME_WON;
        }
        return returnMessage;
    }

    private boolean hasWon() {
        return winInterpreter.interpret();
    }

    private boolean hasLost() {
        return losingInterpreter.interpret();
    }

    public void setWinInterpreter(IInterpreter winInterpreter) {
        this.winInterpreter = winInterpreter;
    }

    public void setLosingInterpreter(IInterpreter losingInterpreter) {
        this.losingInterpreter = losingInterpreter;
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

    //Return true if the player had an antidote and had been healed.
    public boolean checkInventoryForAntidote() {

        List<Element> elementList = this.getPlayer().getElementList();

        for (Element inventoryElement : elementList ) {
            if (inventoryElement.isAntidote()) {
                Element player = this.getPlayer();
                player.setPoisoned(false);
                player.removeElement(inventoryElement);
                return true;
            }
        }
        return false;
    }

}
