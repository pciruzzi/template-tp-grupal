package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.CommandParser;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by panchoubuntu on 12/05/16.
 */
public class EngineTwo {

    private Map<String,ElementTwo> elementsMap;
    private CommandParser commandParser;
    private Game game;

    public void createGame(GameBuilder gameBuilder) {
        commandParser = new CommandParser();
        game = gameBuilder.build();
        elementsMap = game.getCurrentPositionElements();
    }

    public String doCommand(String action) {
        ArrayList<ElementTwo> elementsList = new ArrayList<>(elementsMap.values());
        ElementTwo firstElement = commandParser.getFirstElement(action,elementsList);

        if ( firstElement == null ) {
            return "Invalid element";
        }
        String firstElementName = firstElement.getName();
        String command = commandParser.getCommand(action, firstElementName);
        return game.play(command, firstElementName);
    }
}
