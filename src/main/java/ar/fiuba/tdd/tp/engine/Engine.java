package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.CommandParser;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;

import java.util.ArrayList;
import java.util.Map;

public class Engine {

    private Map<String,Element> elementsMap;
    private CommandParser commandParser;
    private Game game;

    public void createGame(GameBuilder gameBuilder) {
        commandParser = new CommandParser();
        game = gameBuilder.build();
        elementsMap = game.getCurrentPositionElements();
    }

    public String doCommand(String action) {


        ArrayList<Element> elementsList = new ArrayList<Element>(elementsMap.values());

        Element firstElement = commandParser.getFirstElement(action,elementsList);

        if ( firstElement == null ) {
            return game.play(action);
//            return "Invalid element";
        }
        String firstElementName = firstElement.getName();
        String command = commandParser.getCommand(action, firstElementName);
        return game.play(command, firstElementName);
    }

    public boolean isGameWon() {
        return this.game.getGameWon();
    }
}
