package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

public class OpenDoor extends Game {

    public OpenDoor() {
        gameWon = false;
        name = "open door";
        description = "El open door consiste en...";
    }

    @Override
    public Game copy() {
        return new OpenDoor();
    }

    @Override
    public void createGame() {

        State nextState = new State();
        createActualState(nextState);

        finalState = new State();
        finalState.addElement(new Element("key", "grabbed"));
        finalState.addElement(new Element("door", "opened"));

        nextState.addDesiredState(finalState);
        nextState.addNextState(finalState);

        elementsList = actualState.getElementList();
    }

    private void createActualState(State nextState) {
        Element key = new Element("key", "floor");
        key.addActionState("pick", "grabbed");
        key.addActionState("drop", "floor");

        actualState = new State();
        Element unOpenableDoor = createUnopenableDoor();
        actualState.addElement(unOpenableDoor);
        actualState.addElement(key);

        Element keyTwo = new Element("key", "grabbed");
        keyTwo.addActionState("pick", "grabbed");
        keyTwo.addActionState("drop", "floor");
        nextState.addElement(keyTwo);

        Element doorTwo = createOpenableDoor();
        nextState.addElement(doorTwo);

        actualState.addDesiredAndNextState(nextState, nextState);

        nextState.addDesiredAndNextState(actualState,actualState);
    }
}