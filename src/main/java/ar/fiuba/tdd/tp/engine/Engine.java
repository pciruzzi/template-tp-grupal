package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;

import java.util.List;

public class Engine {

    private CommandParser commandParser;

    private Game game;

    public void createGame(GameBuilder gameBuilder) {
        commandParser = new CommandParser();
        game = gameBuilder.build();
    }

    public String createPlayer(int playerID) {
        return game.createPlayer(playerID);
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
        String command = commandParser.getCommand(action, firstElementName);
        if ( secondElement == null ) {
            return game.play(playerID, command, firstElementName);
        }
        String secondElementName = secondElement.getName();
        return game.play(playerID, command, firstElementName, secondElementName);
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
