package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;

import java.util.ArrayList;

public class Engine {

    private CommandParser commandParser;

    private Game game;

    public void createGame(GameBuilder gameBuilder) {
        commandParser = new CommandParser();
        game = gameBuilder.build();
    }

    public void createPlayer(int id) {
        game.createPlayer(id);
    }

    public String doCommand(int id, String action) {
        ArrayList<Element> elementsList = new ArrayList<>(game.getCurrentPositionElements(id).values());
        elementsList.addAll(game.getPlayer().getElementList());

        Element firstElement = commandParser.getFirstElement(action,elementsList);
        if ( firstElement == null ) {
            return game.play(id, action);
        }
        String firstElementName = firstElement.getName();

        Element secondElement = commandParser.getSecondElement(action, firstElementName, elementsList);
        String command = commandParser.getCommand(action, firstElementName);
        if ( secondElement == null ) {
            return game.play(id, command, firstElementName);
        }
        String secondElementName = secondElement.getName();
        return game.play(id, command, firstElementName, secondElementName);
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
