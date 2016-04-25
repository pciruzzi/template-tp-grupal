package ar.fiuba.tdd.tp.juegos;

import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.State;

/**
 * Created by panchoubuntu on 21/04/16.
 */
public class OpenDoor extends Game {

//    protected State actualState;    // Este es el estado actual en el que esta el juego
    protected State finalState;     // Este es el estado en el que se gana el juego
    protected State desiredState;   // Este es el estado necesario para pasar al siguiente estado
//    private State nextState;

    @Override
    public void createGame() {

        Element stick = new Element("stick", "floor");
        stick.addActionState("pick", "grabbed");
        stick.addActionState("drop", "floor");

        Element door = new Element("door", "closed");
//        door.addActionState("open", "open");
//        door.addActionState("close", "closed");

        Element key = new Element("key", "floor");
        key.addActionState("pick", "grabbed");
        key.addActionState("drop", "floor");

        actualState = new State();
        actualState.addElement(stick);
        actualState.addElement(door);
        actualState.addElement(key);

        State nextState = new State();
        nextState.addElement(new Element("key", "grabbed"));

        Element doorTwo = new Element("door", "closed");
        doorTwo.addActionState("open", "open");
        doorTwo.addActionState("close", "closed");

        nextState.addElement(doorTwo);
        nextState.addElement(new Element("stick", "floor"));

        actualState.addDesiredState(nextState);
        actualState.addNextState(nextState);

        State stateThree = new State();
        stateThree.addElement(new Element("key", "grabbed"));
        stateThree.addElement(new Element("door", "open"));
        stateThree.addElement(new Element("stick", "floor"));

        nextState.addDesiredState(stateThree);
        nextState.addNextState(finalState);

        State stateFour = new State();
        stateFour.addElement(new Element("key", "grabbed"));
        stateFour.addElement(new Element("door", "open"));
        stateFour.addElement(new Element("stick", "floor"));

        finalState = stateFour;

        finalState.addNextState(finalState);
        finalState.addDesiredState(finalState);

    }

    public String doAction(String action) {

        if (action.equals("look around")) {
            return showItems();
        }

        desiredState = actualState.getDesiredState();
        String[] partes = action.split(" ");
        if (partes.length < 2 ) {
            return "Invalid input";
        }
        String returnMessage = actualState.doAction(partes[0], partes[1]);

        if (actualState.iguales(desiredState) ) {
            actualState = actualState.getNextState();
            System.out.println("Cambie el actual State");
        }

        if ( actualState.iguales(finalState) ) {
            return "Ganaste guachin";
        } else {
            return returnMessage;
        }
    }
}
