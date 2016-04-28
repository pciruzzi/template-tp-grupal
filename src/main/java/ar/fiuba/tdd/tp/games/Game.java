package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.List;

public abstract class Game {

    protected String name;
    protected String description;

    protected State actualState;    // Este es el estado actual en el que esta el juego

    protected State finalState;     // Este es el estado en el que se gana el juego
    protected State desiredState;   // Este es el estado necesario para pasar al siguiente estado
    protected List<Element> elementsList;

    protected boolean gameWon;

//    public abstract String doAction(String action);
    public abstract Game copy();

//    public boolean checkGameName(String gameName) {
//
//        gameName = gameName.toLowerCase();
//
//        if (gameName.equals(name)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public String getGameName() {
        return name;
    }

    public String getDescription() {
        return this.description;
    }

    public String doAction(String action) {

        String[] parts = action.split(" ");

        if (parts.length < 2 ) {
            return "Invalid input";
        } else if (action.equals("look around")) {
            return showItems();
        }

        desiredState = actualState.getDesiredState();

        String returnMessage = actualState.doAction(parts[0], parts[1]);

        if (actualState.isEqual(desiredState) ) {
            actualState = actualState.getNextState();
        }

        return update(returnMessage);

    }

    protected String update(String returnMessage) {
        if ( actualState.isEqual(finalState) ) {
            returnMessage = "You won!!!";
            this.gameWon = true;
        }
        return returnMessage;
    }

    public abstract void createGame();

    protected String showItems() {
        return actualState.showStateItems();
    }

    public boolean getGameWon() {
        return this.gameWon;
    }

}
