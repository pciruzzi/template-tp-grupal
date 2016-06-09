package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.time.TimeCommand;

import java.util.*;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;

public class Game {

    private List<Player> players;
    private List<Boolean> isPlayerConnected;
    private Element initialPosition;
    private IInterpreter winInterpreter;
    private IInterpreter losingInterpreter;
    private String name;
    private String description;
    private int maxPlayers;
    private boolean gameWon;
    private boolean gameLost;
    private ArrayList<TimeCommand> timeCommands;

    public Game(String name) {
        this.name = name;
        this.isPlayerConnected = new ArrayList<>();
        this.players = new ArrayList<>();
        this.gameLost = false;
        this.gameWon = false;
        this.description = "Descripcion basica.";
        this.timeCommands = new ArrayList<>();
        this.maxPlayers = 1;
    }

    public String getName() {
        return this.name;
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

    public int createPlayer(int id) {
        if (getPlayersConnected() < maxPlayers) {
            Player newPlayer = new Player(id);
            newPlayer.setPlayerPosition(initialPosition);
            initialPosition.addElement(newPlayer);
            newPlayer.setWinInterpreter(winInterpreter);
            newPlayer.setLosingInterpreter(losingInterpreter);
            players.add(newPlayer);
            isPlayerConnected.add(true);
            return id;
        }
        return -1;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxPlayers(int players) {
        this.maxPlayers = players;
    }

    private int getPlayersConnected() {
        int count = 0;
        for (Boolean isConnected : this.isPlayerConnected) {
            if (isConnected) {
                count++;
            }
        }
        return count;
    }

    public  String play(int playerID, String cmd) {
        String returnMessage;
        returnMessage = getPlayerPosition(playerID).doCommand(cmd, playerID);
        return returnMessage;
    }

    public String play(int playerID, String cmd, String element) {
        String returnMessage;

        Element actualElement = getElement(playerID, element);

        if (actualElement != null) {
            returnMessage = actualElement.doCommand(cmd, playerID);
        } else {
            returnMessage = "It doesn't exist a " + element + " in the game " + getName();
        }

        returnMessage = checkFinishedGame(returnMessage);
        return returnMessage;
    }

    public String play(int playerID, String cmd, String element, String destinationElement) {
        String returnMessage;

        Element actualElement = getElement(playerID, element);
        Element destElement = getElement(playerID, destinationElement);

        if (actualElement != null && destElement != null) {
            returnMessage = actualElement.doCommand(cmd, getPlayerPosition(playerID), destElement, playerID);
        } else {
            returnMessage = "It doesn't exist a " + element + " in the game " + getName();
        }

        returnMessage = checkFinishedGame(returnMessage);
        return returnMessage;
    }

    private Element getElement(int playerID, String element) {
        Element actualElement;
        Element player = getPlayer(playerID);
        Map<String, Element> visibleElements = this.calculateVisibleElements(playerID);
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
        for (Player player : players) {
            int playerID = player.getPlayerID();

            if (this.isPlayerConnected.get(playerID)) {
                if (this.hasLost(playerID)) {
                    gameLost = true;
                    returnMessage = GAME_LOST;
                }
                if (this.hasWon(playerID)) {
                    gameWon = true;
                    returnMessage = GAME_WON;
                }
            }
        }
        return returnMessage;
    }

    private boolean hasWon(int playerID) {
        Player player = getPlayer(playerID);
        return (player.getWinInterpreter().interpret() || player.getWinInterpreter().interpret(player));
    }

    private boolean hasLost(int playerID) {
        Player player = getPlayer(playerID);
        return (player.getLosingInterpreter().interpret() || player.getLosingInterpreter().interpret(player));
    }

    public void setWinInterpreter(IInterpreter winInterpreter) {
        this.winInterpreter = winInterpreter;
    }

    public void setLosingInterpreter(IInterpreter losingInterpreter) {
        this.losingInterpreter = losingInterpreter;
    }

    public Map<String, Element> calculateVisibleElements(int id) {
        Map<String, Element> elements;
        elements = getPlayerPosition(id).getVisibleElements();
        elements.putAll(getPlayer(id).getVisibleElements());
        return elements;
    }

    public List<Element> getVisibleElementList(int playerID) {
        Map<String, Element> visibleElements = this.calculateVisibleElements(playerID);
        return new ArrayList<>(visibleElements.values());
    }

    //Return true if the player had an antidote and had been healed.
    public boolean checkInventoryForAntidote(int playerID) {
        List<Element> elementList = this.getPlayer(playerID).getElementList();
        for (Element inventoryElement : elementList ) {
            if (inventoryElement.isAntidote()) {
                Element player = this.getPlayer(playerID);
                player.setPoisoned(false);
                player.removeElement(inventoryElement);
                return true;
            }
        }
        return false;
    }

    public void addTimeCommand(TimeCommand pickStick) {
        timeCommands.add(pickStick);
    }

    public ArrayList<TimeCommand> getTimeCommands() {
        return timeCommands;
    }

    public Player getPlayer(int id) {
        return players.get(id);
    }

    public Element getPlayerPosition(int id) {
        return getPlayer(id).getPlayerPosition();
    }

    public void setPlayerPosition(int id, Element newPlayerPosition) {
        Player player = getPlayer(id);
        player.setPlayerPosition(newPlayerPosition);
    }

    public void setInitialPosition(Element initialPosition) {
        this.initialPosition = initialPosition;
    }

    public void notifyExitPlayer(int playerID) {
        isPlayerConnected.set(playerID, false);
    }

}
