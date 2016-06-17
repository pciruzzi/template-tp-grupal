package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.time.TimeCommand;

import java.util.*;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;

public class Game {

    private List<Player> players;
    private List<Boolean> isPlayerConnected;
    private Element initialPosition;
    private String name;
    private String description;
    private int maxPlayers;
    private boolean gameWon;
    private boolean gameLost;
    private BroadcastQueue queue;
    private ArrayList<TimeCommand> timeCommands;
    private ArrayList<Player> timeElements;
    private ArrayList<Element> containersList;

    public Game(String name) {
        this.name = name;
        this.isPlayerConnected = new ArrayList<>();
        this.players = new ArrayList<>();
        this.gameLost = false;
        this.gameWon = false;
        this.description = "Descripcion basica.";
        this.maxPlayers = 1;
        this.timeCommands   = new ArrayList<>();
        this.timeElements   = new ArrayList<>();
        this.containersList = new ArrayList<>();
    }

    public void setQueue(BroadcastQueue queue) {
        this.queue = queue;
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

    public int createPlayer() {
        if (getPlayersConnected() < maxPlayers) {
            Player newPlayer = getEmptyPlayer();
            if (newPlayer != null) {
                newPlayer.setPlayerPosition(initialPosition);
                initialPosition.addElement(newPlayer);
                int playerID = newPlayer.getPlayerID();
                isPlayerConnected.set(playerID, true);
                return playerID;
            }
            System.err.println("PlayerConnected != max, pero no se pudo crear player");
        }
        return -1;
    }

    private Player getEmptyPlayer() {
        for (int i = 0; i < isPlayerConnected.size(); i++) {
            if (! isPlayerConnected.get(i)) {
                return players.get(i);
            }
        }
        return null;
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
        returnMessage = checkFinishedGame(returnMessage, playerID);
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
        returnMessage = checkFinishedGame(returnMessage, playerID);
        return returnMessage;
    }

    public String playTime(String cmd, Player element) {
        String returnMessage = "It doesn't exist the element";
        if (element != null) {
            returnMessage = element.doTimeCommand(cmd);
            queue.pushBroadcast(returnMessage);
        }
        returnMessage = checkFinishedGameForAll(returnMessage);
        return returnMessage;
    }

    public String playTime(String cmd, Element firstElement, Element secondElement) {
        String returnMessage = "It doesn't exist the element";
        returnMessage = checkFinishedGameForAll(returnMessage);
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

    public List<Player> getTimeElements() {
        return timeElements;
    }

    public void addTimeElement(Player element) {
        for (Player checkedElement : timeElements ) {
            String elementName = element.getName();
            if (checkedElement.getName().equals(elementName) ) {
                System.out.println("Agregaste dos veces el mismo TimeElement: " + elementName);
            }
        }
        timeElements.add(element);
    }

    private String checkFinishedGameForAll(String returnMessage) {
        for (Player player : players) {
            int playerID = player.getPlayerID();
            returnMessage = this.checkFinishedGame(returnMessage, playerID);
        }
        return returnMessage;
    }

    private String checkFinishedGame(String returnMessage, int playerID) {
        if (this.isPlayerConnected.get(playerID)) {
            if (this.hasLost(playerID)) {
                gameLost = true;
                returnMessage = GAME_LOST;
                this.queue.pushLostCommand(playerID);
            }
            if (this.hasWon(playerID)) {
                gameWon = true;
                returnMessage = GAME_WON;
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

    public void addContainer(Element element) {
        for ( Element room : containersList) {
            if ( room.getName().equals(element.getName()) ) {
//                System.out.println("You are adding the same room twice to the game: " + room.getName());
            }
        }
        containersList.add(element);
    }

    public List<Element> getContainersList() {
        return containersList;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        for (int i = 0; i < players.size(); i++) {
            this.isPlayerConnected.add(false);
        }
    }
}
