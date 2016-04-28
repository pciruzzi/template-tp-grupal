package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

public class EvilThing extends Game {

    public EvilThing() {
        gameWon = false;
        name = "evil thing";
        this.description = "El evil thing consiste en...";
    }

    @Override
    public Game copy() {
        return new EvilThing();
    }

    @Override
    public void createGame() {
        createInitialState();

        finalState = new State();
        finalState.addElement(new Element("door", "opened"));

        State desiredState = new State();
        desiredState.addElement(new Element("door", "closed"));
        desiredState.addElement(new Element("key", "grabbed"));

        State stateTwo = createStateTwo();
        actualState.addDesiredAndNextState(desiredState, stateTwo);
    }

    private void createInitialState() {
        Element evil = new Element("key", "floor");
        evil.addActionState("pick", "grabbed");

        actualState = new State();
        actualState.addElement(evil);
        Element unOpenableDoor = createUnopenableDoor();
        actualState.addElement(unOpenableDoor);
    }

    private State createStateTwo() {
        Element door = createOpenableDoor();
        Element key = new Element("key", "grabbed");

        State stateTwo = new State();
        stateTwo.addElement(door);
        stateTwo.addElement(key);

        State stateThree = createStateThree();

        State desiredStateTwo = new State();
        desiredStateTwo.addElement(new Element("door", "opened"));
        desiredStateTwo.addElement(new Element("key", "grabbed"));

        stateTwo.addDesiredAndNextState(desiredStateTwo, stateThree);

        return stateTwo;
    }

    private State createStateThree() {
        Element door = createUnopenableDoor();
        Element thief = new Element("thief", "room");
        Element key = new Element("key", "grabbed");
        key.addActionState("talk", "stolen");

        State stateThree = new State();
        stateThree.addElement(door);
        stateThree.addElement(thief);
        stateThree.addElement(key);

        State desiredStateThree = new State();
        desiredStateThree.addElement(new Element("door", "closed"));
        desiredStateThree.addElement(new Element("key", "stolen"));
        desiredStateThree.addElement(thief);

        State stateFour = createStateFour();
        stateThree.addDesiredAndNextState(desiredStateThree, stateFour);

        return stateThree;
    }

    private State createStateFour() {
        Element finalDoor = createOpenableDoor();
        Element thief = new Element("thief", "room");

        State stateFour = new State();
        stateFour.addElement(finalDoor);
        stateFour.addElement(thief);

        State desiredStateFour = new State();
        desiredStateFour.addElement(new Element("door", "opened"));
        desiredStateFour.addElement(thief);

        stateFour.addDesiredAndNextState(desiredStateFour, finalState);

        return stateFour;
    }
}
