package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

@SuppressWarnings("CPD-START")

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
        ICommand open = new ChangeVisibility("open", true, game);
        createChestWithPoison(room, question, open);
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

    private void createChestWithPoison(Element room, ICommand question, ICommand open) {
        Element chest = new Element("chest");
        chest.addCommand(question);
        chest.setState(true);
        chest.addCommand(open);
        chest.setPoisoned(true);
        room.addElement(chest);
    }

    private void createStickWithPoison(Element room, ICommand question, ICommand pick) {
        Element chest = new Element("stick");
        chest.addCommand(question);
        chest.setState(true);
        chest.addCommand(pick);
        chest.setPoisoned(true);
        room.addElement(chest);
    }

    @SuppressWarnings("CPD-END")

    private Element createAntidote(ICommand question, ICommand pick) {
        Element antidote = new Element("antidote");
        antidote.addCommand(pick);
        antidote.setState(true);
        antidote.addCommand(question);
        antidote.setAntidote(true);
        return antidote;
    }
}
