package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

public class OpenDoor2 extends Game {

    public OpenDoor2() {
        name = "open door 2";
        gameWon = false;
    }

    @Override
    public Game copy() {
        return new OpenDoor2();
    }

    @Override
    public void createGame() {
        finalState = new State();
        finalState.addElement(new Element("door", "opened"));
        finalState.addElement(new Element("key", "grabbed"));
        finalState.addElement(new Element("box", "opened"));

        createActualState();
        State secondState = createSecondState();

        State desiredStateOfActualState = new State();
        desiredStateOfActualState.addElement(new Element("box", "opened"));
        desiredStateOfActualState.addElement(new Element("door", "closed"));

        actualState.addDesiredAndNextState(desiredStateOfActualState, secondState);
    }

    private void createActualState() {
        Element box = new Element("box", "closed");
        box.addActionState("open", "opened");
        box.addActionState("close", "closed");

        Element door = createUnopenableDoor();

        actualState = new State();
        actualState.addElement(door);
        actualState.addElement(box);
    }

    private State createSecondState() {

        State secondState = new State();
        secondState.addElement(new Element("box", "opened"));
        secondState.addElement(createUnopenableDoor());

        Element key = new Element("key", "floor");
        key.addActionState("pick", "grabbed");
        key.addActionState("drop", "floor");

        secondState.addElement(key);

        State desiredStateOfSecondState = new State();
        desiredStateOfSecondState.addElement(new Element("door", "closed"));
        desiredStateOfSecondState.addElement(new Element("key", "grabbed"));
        desiredStateOfSecondState.addElement(new Element("box", "opened"));

        State stateThree = createThirdState();

        secondState.addDesiredAndNextState(desiredStateOfSecondState, stateThree);

        return secondState;
    }

    private State createThirdState() {

        State stateThree = new State();

        stateThree.addElement(new Element("key", "grabbed"));
        stateThree.addElement(new Element("box", "opened"));

        Element doorOpenable = createOpenableDoor();

        stateThree.addElement(doorOpenable);

        stateThree.addDesiredAndNextState(finalState, finalState);

        return stateThree;
    }

}
