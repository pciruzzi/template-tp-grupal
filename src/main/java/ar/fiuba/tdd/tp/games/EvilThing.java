package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.ArrayList;

public class EvilThing extends Game {

    public EvilThing() {
        gameWon = false;
        name = "evil thing";
        this.description = "El evil thing consiste en...";
        finalStatesList = new ArrayList<>();
    }

    @Override
    public Game copy() {
        return new EvilThing();
    }



    private void createInitialState() {
        Element evil = new Element("key", "floor");
        evil.addActionState("pick", "grabbed");
        evil.addActionState("drop", "floor");

        actualState = new State();
        actualState.addElement(evil);
        Element unOpenableDoor = createUnopenableDoor();
        actualState.addElement(unOpenableDoor);
    }

    private State createStateTwo() {
        Element door = createOpenableDoor();
        Element thief = new Element("thief", "noStole");
        thief.addActionState("", "");

        State state = new State();
        state.addElement(door);
        state.addElement(thief);

        return state;
    }

    private State createStateThree() {
        State state = new State();
        return state;
    }

    private State createStateFour() {
        State state = new State();
        return state;
    }

    @Override
    public void createGame() {
        createInitialState();
        //State stateTwo = createStateTwo();
        //State stateThree = createStateThree();
        //State stateFour = createStateFour();
        finalState = new State();
        finalState.addElement(new Element("door", "opened"));
        finalStatesList.add(finalState);
    }
}
