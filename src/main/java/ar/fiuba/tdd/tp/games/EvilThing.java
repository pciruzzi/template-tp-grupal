package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.ArrayList;

public class EvilThing extends Game {

    public EvilThing() {
        gameWon = false;
        finalStatesList = new ArrayList<State>();
        this.description = "Never feel sad if you are removed of a valuable item, sometiemes is the only way out.";
        name = "evil thing";
    }

    @Override
    public Game copy() {
        return new EvilThing();
    }

    @Override
    public void createGame() {
        createInitialState();

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

    private Element createThief() {
        Element thief = new Element("thief", "floor");
        thief.addActionState("talkTo", "stolenKey");
        return thief;
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
        State stateThree = new State();

        Element door = createUnopenableDoor();
        stateThree.addElement(door);

        Element thief = createThief();
        stateThree.addElement(thief);

        State desiredStateThree = new State();
        State stateFour = createStateFour();

        desiredStateThree.addElement(new Element("thief", "stolenKey"));
        desiredStateThree.addElement(new Element("door", "closed"));

        stateThree.addDesiredAndNextState(desiredStateThree, stateFour);

        return stateThree;
    }

    private State createStateFour() {
        Element finalDoor = createOpenableDoor();
        Element thief = createThief();

        State stateFour = new State();
        stateFour.addElement(finalDoor);
        stateFour.addElement(thief);

        State desiredStateFour = new State();
        desiredStateFour.addElement(new Element("door", "opened"));
        desiredStateFour.addElement(thief);

        State finalState = new State();
        finalState.addElement(new Element("door", "opened"));
        finalStatesList.add(finalState);

        stateFour.addDesiredAndNextState(desiredStateFour, finalState);

        return stateFour;
    }
}
