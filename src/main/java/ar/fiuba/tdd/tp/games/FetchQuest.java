package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.ArrayList;

public class FetchQuest extends Game {

    public FetchQuest() {
        gameWon = false;
        this.name = "fetch quest";
        this.description = "El fetch quest consiste en...";
        finalStatesList = new ArrayList<State>();
    }

    @Override
    public Game copy() {
        return new FetchQuest();
    }

    @Override
    public void createGame() {
        Element stick = new Element("stick", "floor");
        stick.addActionState("pick", "picked");

        State initialState = new State();
        initialState.addElement(stick);

        actualState = initialState;

        Element pickedStick = new Element("stick", "picked");

        State proximoState = new State();
        proximoState.addElement(pickedStick);

        initialState.addDesiredState(proximoState);
        initialState.addNextState(proximoState);

        finalState = proximoState;

        finalStatesList.add(finalState);

        elementsList = actualState.getElementList();
    }
}
