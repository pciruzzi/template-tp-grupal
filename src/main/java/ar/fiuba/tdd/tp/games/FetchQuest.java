package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

public class FetchQuest extends Game {

    public FetchQuest() {
        console = new Console();
        this.name = "fetch quest";
    }

//    protected State actualState;    // Este es el estado actual en el que esta el juego
//    protected State finalState;     // Este es el estado en el que se gana el juego
//    protected State desiredState;   // Este es el estado necesario para pasar al siguiente estado

    @Override
    public Game copy() {
        return new FetchQuest();
    }

    @Override
    public void createGame() {

        Element stick = new Element("stick", "floor");
        stick.addActionState("pick", "picked");
        stick.addActionState("drop", "floor");

        State initialState = new State();
        initialState.addElement(stick);

        actualState = initialState;

        Element pickedStick = new Element("stick", "picked");

        State proximoState = new State();
        proximoState.addElement(pickedStick);

        initialState.addDesiredState(proximoState);
        initialState.addNextState(proximoState);

        finalState = proximoState;


        console.write("Fetch Quest game was created.");
    }

//    @Override
//    public String doAction(String action) {
//
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
//        System.out.println("Element name: ");
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
