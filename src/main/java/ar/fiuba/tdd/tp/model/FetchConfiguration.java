package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;



public class FetchConfiguration implements GameBuilder {

    public Game build() {
        Game game = new Game("Fetch Quest");

        ElementTwo room = new ElementTwo("room", true);
        ElementTwo stick = new ElementTwo("stick", true);
        ElementTwo player = new ElementTwo("player", false);

        game.setPlayer(player);

        ICommand lookAround = new LookAround("look around", game);
        room.addCommand(lookAround);

        ICommand pick = new MoveToPlayer("pick", game);
        ICommand question = new Question("what can i do");
        stick.addCommand(pick);
        stick.addCommand(question);

        room.addElement(stick);

        ArrayList<String> winArray = new ArrayList<>();
        winArray.add("stick");

        IInterpreter winInterpreter = new ContainsElements(player,winArray);

        game.setPlayerPosition(room);
        game.setWinInterpreter(winInterpreter);

        return game;

    }
}