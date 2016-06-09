package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;
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

        player.addState(new State("poison", false, false));

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
        State antidoteState = new State("poison", false, true);
        antidoteState.setEffectMessage("The antidote healed you, PAPA!!");
        antidote.setStateToAffect(antidoteState);
        room.addElement(antidote);

        createFinishingConditions();

        return game;
    }

    private void createFinishingConditions() {
        ArrayList<String> winArray = new ArrayList<String>();
        winArray.add("antidotes");

        ArrayList<String> losingArray = new ArrayList<String>();
        losingArray.add("stickp");

        IInterpreter winInterpreter = new ContainsElements(player,winArray);
        IInterpreter losingInterpreter = new ContainsElements(player,losingArray);

        game.setWinInterpreter(winInterpreter);
        game.setLosingInterpreter(losingInterpreter);
    }

    private void createChestWithPoison(Element room, ICommand question, ICommand open) {
        Element chest = new Element("chest");
        chest.addCommand(question);
        chest.changeState("visible", true);
        chest.addCommand(open);
        chest.setStateToAffect(new State("poison", true, false));
        room.addElement(chest);
    }

    private void createStickWithPoison(Element room, ICommand question, ICommand pick) {
        Element stick = new Element("stick");
        stick.addCommand(question);
        stick.changeState("visible", true);
        stick.addCommand(pick);
        State poisonState = new State("poison", true, "antidote", true);
        poisonState.setEffectMessage("You have been poison :(");
        poisonState.setAntiEffectMessage("You have been healed :)");
        stick.setStateToAffect(poisonState);
        room.addElement(stick);
    }

    @SuppressWarnings("CPD-END")

    private Element createAntidote(ICommand question, ICommand pick) {
        Element antidote = new Element("antidote");
        antidote.addCommand(pick);
        antidote.changeState("visible", true);
        antidote.addCommand(question);
        return antidote;
    }
}
