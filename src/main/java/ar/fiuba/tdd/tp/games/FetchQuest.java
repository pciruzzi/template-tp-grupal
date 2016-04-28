package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

public class FetchQuest extends Game {

    public FetchQuest() {
        //this.console = new Console();
        this.name = "fetch quest";
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

        elementsList = actualState.getElementList();

        this.console = new Console();
        console.write("Fetch Quest game was created.");

    }
}
