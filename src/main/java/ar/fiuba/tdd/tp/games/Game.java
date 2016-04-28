package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.List;

public abstract class Game {

    protected String name;
    protected String description;
    protected boolean gameWon;

    protected State actualState;    // Este es el estado actual en el que esta el juego
    protected State finalState;     // Este es el estado en el que se gana el juego
    protected State desiredState;   // Este es el estado necesario para pasar al siguiente estado
    protected List<Element> elementsList;

    public abstract Game copy();


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


//    public abstract String doAction(String action);

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

    protected Element createOpenableDoor() {

        Element doorOpenable = new Element("door", "closed");
        doorOpenable.addActionState("open", "opened");
        doorOpenable.addActionState("close", "closed");

        return doorOpenable;
    }

    public String doAction(String action) {
        if ( checkQuestionMessage(action) ) {
            return answerQuestion(action);
        }

        String[] parts = action.split(" ");
        if (parts.length < 2 ) {
            return "Invalid input";
        } else if (action.equals("look around")) {
            return showItems();
        }

        return verifyChangeState(parts);
    }

    private String verifyChangeState(String[] parts) {
        desiredState = actualState.getDesiredState();
        String returnMessage = actualState.doAction(parts[0], parts[1]);

        if (actualState.isEqual(desiredState) ) {
            actualState = actualState.getNextState();
        }
        return update(returnMessage);
    }

    protected String update(String returnMessage) {
        if ( actualState.isEqual(finalState) ) {
            returnMessage = "You won the game!";
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