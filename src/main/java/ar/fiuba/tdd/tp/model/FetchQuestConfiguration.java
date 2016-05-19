package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.FalseExpression;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

public class FetchQuestConfiguration implements GameBuilder {

    private Game game;

    public Game build() {
        game = new Game("Fetch Quest");

        Element room = new Element("room");

        Element stick = new Element("stick");
        stick.setState(true);

        Element player = new Element("player");
        game.setPlayer(player);

        ICommand lookAround = new LookAround("look around", game);
        room.addCommand(lookAround);

        ICommand pick = new MoveToPlayer("pick", game);
        ICommand question = new Question("ask");
        stick.addCommand(pick);
        stick.addCommand(question);

        room.addElement(stick);

        game.setPlayerPosition(room);

        setWinAndLoseInterpreter(player);

        return game;
    }

    private void setWinAndLoseInterpreter(Element player) {
        ArrayList<String> winArray = new ArrayList<String>();
        winArray.add("stick");

        IInterpreter winInterpreter = new ContainsElements(player,winArray);

        game.setWinInterpreter(winInterpreter);

        IInterpreter loseInterpreter = new FalseExpression();
        game.setLosingInterpreter(loseInterpreter);
    }
}