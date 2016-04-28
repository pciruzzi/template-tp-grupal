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
//        createInitialState();

//        State stateTwo = createStateTwo();

  //      finalState = new State();
    //    finalState.addElement(new Element("key", "grabbed"));
    //    finalState.addElement(new Element("door", "opened"));
    }

    private void createInitialState() {
//        Element evil = new Element("key", "floor");
//        evil.addActionState("pick", "grabbed");
//        evil.addActionState("drop", "floor");
//        Element unOpenableDoor = createUnopenableDoor();

//        actualState = new State();
//        actualState.addElement(evil);
//        actualState.addElement(unOpenableDoor);
    }

    private State createStateTwo() {
 //       Element door = createOpenableDoor();
 //       Element thief = new Element("thief", "noStole");
 //       thief.addActionState("", "");

  //      State state = new State();
  //      state.addElement(door);
  //      state.addElement(thief);
        return new State();

    }
}
