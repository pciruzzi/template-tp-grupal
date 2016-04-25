package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.State;

public abstract class Game {

    protected State actualState;    // Este es el estado actual en el que esta el juego
    protected State finalState;     // Este es el estado en el que se gana el juego
    protected State desiredState;   // Este es el estado necesario para pasar al siguiente estado

//    public abstract String doAction(String action);

    public String doAction(String action) {

        String[] partes = action.split(" ");

        if (partes.length < 2 ) {
            return "Invalid input";
        } else if (action.equals("look around")) {
            return showItems();
        }

        desiredState = actualState.getDesiredState();

        String returnMessage = actualState.doAction(partes[0], partes[1]);

        if (actualState.isEqual(desiredState) ) {
            actualState = actualState.getNextState();
        }

        return update(returnMessage);

    }

//    private String checkLookAround(String action) {
//
//    }



    private String update(String returnMessage) {
        if ( actualState.isEqual(finalState) ) {
            return "Ganaste guachin";
        } else {
            return returnMessage;
        }
    }

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

    public abstract void createGame();

    public String showItems() {
        return actualState.showStateItems();
    }

}
