package ar.fiuba.tdd.tp.juegos;

import ar.fiuba.tdd.tp.State;

public abstract class Game {

    protected State actualState;    // Este es el estado actual en el que esta el juego
    protected State finalState;     // Este es el estado en el que se gana el juego
    protected State desiredState;   // Este es el estado necesario para pasar al siguiente estado

    public abstract String doAction(String action);

//    public String doAction(String action) {
//
//
//        if (action.equals("look around")) {
//            return showItems();
//        }
//
//        desiredState = actualState.getDesiredState();
//        String[] partes = action.split(" ");
//        String returnMessage = actualState.doAction(partes[0], partes[1]);
//
//        if (actualState.iguales(desiredState) ) {
//            actualState = actualState.getNextState();
//        }
//
//        if ( actualState.iguales(finalState) ) {
//            return "Ganaste guachin";
//        } else {
//            return returnMessage;
//        }
//    }

    public abstract void createGame();

    public String showItems() {
        return actualState.showStateItems();
    }

}
