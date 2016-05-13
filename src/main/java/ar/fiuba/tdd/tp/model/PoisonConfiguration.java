package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.LookAround;
import ar.fiuba.tdd.tp.icommand.MoveToPlayer;
import ar.fiuba.tdd.tp.icommand.Question;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

/**
 * Created by santy on 13/05/16.
 */
public class PoisonConfiguration implements GameBuilder {
    @Override
    public Game build() {

        Element room = new Element("room");
        Game game = new Game("Fetch Quest");
        Element player = new Element("player");

        game.setPlayer(player);
        game.setPlayerPosition(room);

        ICommand lookAround = new LookAround("look around", game);
        room.addCommand(lookAround);

        ICommand question = new Question("ask");
        ICommand pick = new MoveToPlayer("pick", game);
        createStickWithPoison(room, question, pick);

        Element antidote = createAntidote(question, pick);
        room.addElement(antidote);

        ArrayList<String> winArray = new ArrayList<String>();
        winArray.add("sticks");
        IInterpreter winInterpreter = new ContainsElements(player,winArray);

        game.setWinInterpreter(winInterpreter);

        return game;
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
        return antidote;
    }
}
