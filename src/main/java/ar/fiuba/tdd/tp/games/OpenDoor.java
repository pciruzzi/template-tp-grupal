package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

public class OpenDoor extends Game {

    public OpenDoor() {
        this.console = createConsole();
        this.name = "open door";
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

        console.write("Open Door game was created.");
    }

    private void createActualState(State nextState) {
        Element key = new Element("key", "floor");
        key.addActionState("pick", "grabbed");
        key.addActionState("drop", "floor");
        Element door = new Element("door", "closed", "Ey! Where do you go?! Room 2 is locked.");

        actualState = new State();
        actualState.addElement(door);
        actualState.addElement(key);

        nextState.addElement(new Element("key", "grabbed"));

        Element doorTwo = createOpenableDoor();
        nextState.addElement(doorTwo);

        actualState.addDesiredState(nextState);
        actualState.addNextState(nextState);
    }
}
