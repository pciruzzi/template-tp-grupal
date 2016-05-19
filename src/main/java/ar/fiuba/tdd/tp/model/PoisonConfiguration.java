package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.LookAround;
import ar.fiuba.tdd.tp.icommand.MoveToPlayer;
import ar.fiuba.tdd.tp.icommand.Question;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

public class PoisonConfiguration implements GameBuilder {

    private Game game;
    private Element player;

    @Override
    public Game build() {

        game = new Game("Fetch Quest");
        player = new Element("player");
        Element room = new Element("room");

        game.setPlayer(player);
        game.setPlayerPosition(room);

        ICommand lookAround = new LookAround("look around", game);
        room.addCommand(lookAround);

        ICommand question = new Question("ask");
        ICommand pick = new MoveToPlayer("pick", game);
        createStickWithPoison(room, question, pick);

        Element antidote = createAntidote(question, pick);
        room.addElement(antidote);

        createFinishingConditions();

        return game;
    }

    private void createFinishingConditions() {

        ArrayList<String> winArray = new ArrayList<String>();
        winArray.add("antidote");

        ArrayList<String> losingArray = new ArrayList<String>();
        losingArray.add("stick");

        IInterpreter winInterpreter = new ContainsElements(player,winArray);
        IInterpreter losingInterpreter = new ContainsElements(player,losingArray);

        game.setWinInterpreter(winInterpreter);
        game.setLosingInterpreter(losingInterpreter);
    }

    private void createStickWithPoison(Element room, ICommand question, ICommand pick) {
        Element stick = new Element("stick");
        stick.addCommand(question);
        stick.setState(true);
        stick.addCommand(pick);
        stick.setPoisoned(true);
        room.addElement(stick);
    }

    private Element createAntidote(ICommand question, ICommand pick) {
        Element antidote = new Element("antidote");
        antidote.addCommand(pick);
        antidote.setState(true);
        antidote.addCommand(question);
        antidote.setAntidote(true);
        return antidote;
    }
}
