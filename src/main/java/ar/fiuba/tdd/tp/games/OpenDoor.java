package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

public class OpenDoor extends Game {


    public OpenDoor() {
        console = new Console();
        name = "open door";
    }

//    protected State actualState;    // Este es el estado actual en el que esta el juego
//    protected State finalState;     // Este es el estado en el que se gana el juego
//    protected State desiredState;   // Este es el estado necesario para pasar al siguiente estado

    @Override
    public Game copy() {
        return new OpenDoor();
    }

    @Override
    public void createGame() {

        State nextState = new State();

        createActualState(nextState);

        State stateThree = new State();
        stateThree.addElement(new Element("key", "grabbed"));
        stateThree.addElement(new Element("door", "open"));
        stateThree.addElement(new Element("stick", "floor"));

        finalState = new State();
        finalState.addElement(new Element("key", "grabbed"));
        finalState.addElement(new Element("door", "open"));
        finalState.addElement(new Element("stick", "floor"));

        nextState.addDesiredState(stateThree);
        nextState.addNextState(finalState);


        console.write("Open Door game was created.");
    }

    private void createActualState(State nextState) {

        Element stick = new Element("stick", "floor");
        stick.addActionState("pick", "grabbed");
        stick.addActionState("drop", "floor");

        Element key = new Element("key", "floor");
        key.addActionState("pick", "grabbed");
        key.addActionState("drop", "floor");

        Element door = new Element("door", "closed");

        actualState = new State();
        actualState.addElement(door);
        actualState.addElement(stick);
        actualState.addElement(key);

        nextState.addElement(new Element("key", "grabbed"));

        Element doorTwo = new Element("door", "closed");
        doorTwo.addActionState("open", "open");
        doorTwo.addActionState("close", "closed");

        nextState.addElement(doorTwo);
        nextState.addElement(new Element("stick", "floor"));

        actualState.addDesiredState(nextState);
        actualState.addNextState(nextState);

    }

//    public String doAction(String action) {
//
//        if (action.equals("look around")) {
//            return showItems();
//        }
//
//        desiredState = actualState.getDesiredState();
//        String[] partes = action.split(" ");
//        if (partes.length < 2 ) {
//            return "Invalid input";
//        }
//        String returnMessage = actualState.doAction(partes[0], partes[1]);
//
//        if (actualState.isEqual(desiredState) ) {
//            actualState = actualState.getNextState();
//        }
//
//        if ( actualState.isEqual(finalState) ) {
//            return "Ganaste guachin";
//        } else {
//            return returnMessage;
//        }
//    }
}
