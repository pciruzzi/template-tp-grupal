package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.time.Time;

import java.util.List;

public class Engine {

    private CommandParser commandParser;
    private Game game;
    private Time time;
    private BroadcastQueue queue;

    public Engine(BroadcastQueue queue) {
        this.commandParser = new CommandParser();
        this.time = new Time(this, queue);
        this.queue = queue;
    }

    public void createGame(GameBuilder gameBuilder) {
        game = gameBuilder.build();
        game.setQueue(this.queue);
        time.setTimeTasks(game.getTimeCommands());
        time.start();
    }

    public int createPlayer() {
        return game.createPlayer();
    }

    public String doCommand(int playerID, String action) {
        List<Element> elementsList = game.getVisibleElementList(playerID);
        elementsList.addAll(game.getPlayer(playerID).getElementList());

        Element firstElement = commandParser.getFirstElement(action,elementsList);
        if ( firstElement == null ) {
            return game.play(playerID, action);
        }
        String firstElementName = firstElement.getName();

        Element secondElement = commandParser.getSecondElement(action, firstElementName, elementsList);
        action = action.concat(" " + playerID); //por si es un comando que requiere el playerID, sino lo descarta...
        String command = commandParser.getCommand(action, firstElementName);
        if ( secondElement == null ) {
            return game.play(playerID, command, firstElementName);
        }
        String secondElementName = secondElement.getName();
        return game.play(playerID, command, firstElementName, secondElementName);
    }

    public String doTimeCommand(String action) {
        List<Player> timeElements = game.getTimeElements();

        Player firstElement = commandParser.getFirstPlayer(action,timeElements);
        if ( firstElement == null ) {
            return "No hay elemento que realice el TimeCommand " + action;
        }

        String firstElementName = firstElement.getName();

        Player secondElement = commandParser.getSecondPlayer(action, firstElementName, timeElements);
        String command = commandParser.getCommand(action, firstElementName);
        if ( secondElement == null ) {
            return game.playTime(command, firstElement);
        }
        return game.playTime(command, firstElement, secondElement);
    }

    public boolean isGameWon() {
        return this.game.getGameWon();
    }

    public boolean isGameLost() {
        return this.game.getGameLost();
    }

    public Game getGame() {
        return game;
    }
}
