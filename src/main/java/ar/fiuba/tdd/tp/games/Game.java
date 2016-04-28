package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.List;

public abstract class Game {

    protected Console console;
    protected String name;
    protected State actualState;    // Este es el estado actual en el que esta el juego
    protected State finalState;     // Este es el estado en el que se gana el juego
    protected State desiredState;   // Este es el estado necesario para pasar al siguiente estado
    protected List<Element> elementsList;

    public abstract Game copy();

    public boolean checkGameName(String gameName) {
        gameName = gameName.toLowerCase();

        if (gameName.equals(name)) {
            return true;
        } else {
            return false;
        }
    }

    public String answerQuestion(String action) {

        String nameOfObject = action.replace("What can I do with ", "");
        nameOfObject = nameOfObject.replace("?", " ");
        nameOfObject = nameOfObject.trim();

        String possibleActions = null;

        for ( Element element : elementsList) {
            if (element.getName().equals(nameOfObject)) {
                possibleActions = element.getPossibleActions();
            }
        }

        if (possibleActions == null) {
            return "It doesn't exist a " + nameOfObject;
        }

        return possibleActions;

    }
    
//    public String getStringOfPossibleActions(List<String> actions) {
//
//        StringBuffer possibleActions = new StringBuffer();
//        possibleActions.append("You can ");
//
//        int actionsSize = actions.size();
//        for (int i = 0; i < actionsSize ; i++) {
//            possibleActions.append(actions.get(i));
//            if (i != actionsSize - 1) {
//                possibleActions.append( "/");
//            }
//        }
//        possibleActions.append(" the " + name);
//
//        return possibleActions.toString();
//    }

    public boolean checkQuestionMessage(String message) {
        message = message.toLowerCase();
        return message.contains("what can i do with ");
    }


    public String doAction(String action) {

        if ( checkQuestionMessage(action) ) {
            return answerQuestion(action);
        }

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

    protected String update(String returnMessage) {
        if ( actualState.isEqual(finalState) ) {
            returnMessage = "You won the game!";
        }
        return returnMessage;
    }

    public abstract void createGame();

    protected String showItems() {
        return actualState.showStateItems();
    }

}
