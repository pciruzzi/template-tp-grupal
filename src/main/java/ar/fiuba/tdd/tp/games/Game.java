package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;
import com.sun.scenario.effect.impl.state.AccessHelper;

import java.util.List;
import java.util.Map;

public abstract class Game {

    protected String name;
    protected String description;
    protected boolean gameWon;

    protected State actualState;    // Este es el estado actual en el que esta el juego
    protected State finalState;     // Este es el estado en el que se gana el juego
//    protected State desiredState;   // Este es el estado necesario para pasar al siguiente estado
    protected List<Element> elementsList;

    public abstract Game copy();


    public String answerQuestion(String action) {
        action = action.toLowerCase();
        String nameOfObject = action.replace("what can i do with ", "");
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

    protected Element createUnopenableDoor() {
        Element doorUnopenable = new Element("door", "closed", "Ey! Where do you go?! The door is locked.");
        return doorUnopenable;
    }

    public String doAction(String action) {
        if ( checkQuestionMessage(action) ) {
            return answerQuestion(action);
        }

        String[] parts = action.split(" ");
        if (parts.length < 2 ) {
            return invalidInputOrExit(action);
        } else if (action.equals("look around")) {
            return showItems();
        }

        return verifyChangeState(parts);
    }

    private String invalidInputOrExit(String action) {
        if (action.equals("exit")) {
            return "Goodbye! See you next time :)";
        }
        return "Invalid input";
    }

    private String verifyChangeState(String[] parts) {
//        desiredState = actualState.getDesiredState();
        String returnMessage = checkMultipleStateChanges(parts);
        if ( returnMessage.equals("") ) {
            returnMessage = actualState.doAction(parts[0], parts[1]);
        }

//        if (actualState.isEqual(desiredState) ) {
//            actualState = actualState.getNextState();
//        }

        return update(returnMessage);
    }

    private String checkMultipleStateChanges(String[] parts) {
        State copy = actualState.copy();
        String returnMessage = copy.doAction(parts[0], parts[1]);

        Map<State,State> multipleDesiredAndNextStates = copy.getDesiredAndNextStateMap();
        boolean stateChange = false;
        for ( Map.Entry<State,State> entry : multipleDesiredAndNextStates.entrySet()) {

            if ( copy.isEqual(entry.getKey()) ) {
                actualState = entry.getValue();
                System.out.println("cambie de estado");
                stateChange = true;
            }

            System.out.println("termine de comparar");

        }
        if ( !stateChange ) {
            returnMessage = "";
        }
        return returnMessage;
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