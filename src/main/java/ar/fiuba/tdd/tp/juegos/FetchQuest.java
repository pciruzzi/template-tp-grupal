package ar.fiuba.tdd.tp.juegos;

import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.State;

/**
 * Created by panchoubuntu on 21/04/16.
 */
public class FetchQuest extends Game {

    public void createGame() {
        Element stick = new Element("stick", false);
        State initialState =  new State();
        initialState.addElement(stick);

        actualState = initialState;

        Element grabbedStick = new Element("stick", true);
        State finalState = new State();
        finalState.addElement(grabbedStick);

        initialState.addTransition("pick stick", finalState);
    }

    public String doAction(String action) {

        if (action.equals("look around")) {
            return showItems();
        }
        State nextState = actualState.getNextState(action);
        if ( nextState == null ) {
            return "Invalid action";
        } else {
            return "Ganaste guachin";
        }
    }
}
