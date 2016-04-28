package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.ArrayList;
import java.util.List;

public class WolfSheepAndCabbage extends Game {

    private Element player;

    public WolfSheepAndCabbage() {
        gameWon = false;
        name = "wolf sheep and cabbage";
        description = "El wolf sheep and cabbage consiste en...";
    }

    @Override
    public void createGame() {
//        createFinalStateWolfSheepAndCabbage();

        createActualState();

        finalState = new State();
        finalState.addElement(new Element("sheep", "north-shore"));
        finalState.addElement(new Element("wolf", "north-shore"));
        finalState.addElement(new Element("col", "north-shore"));
        finalState.addElement(new Element("player", "north-shore"));

//        console.write("Wolf Sheep and Cabbage game was created.");
        createActualState();
    }

    @Override
    public Game copy() {
        return new WolfSheepAndCabbage();
    }

    @Override
    public String doAction(String action) {
        if ( checkQuestionMessage(action) ) {
            return answerQuestion(action);
        }
        String returnMessage = "Invalid Action.";
        String[] parts = action.split(" ");

        if ( parts.length != 2 ) {
            return returnMessage;
        }
        String actionToDo = parts[0];
        String name = parts[1];
        return getReturnMessage(returnMessage, actionToDo, name);
    }

    private String getReturnMessage(String returnMessage, String actionToDo, String name) {
        switch (actionToDo) {
            case "leave":
                returnMessage = doLeaving(returnMessage, name);
                break;
            case "take":
                returnMessage = doTaking(returnMessage, name);
                break;
            case "cross":
                returnMessage = doCrossing(returnMessage, name);
                break;
            default:
                break;
        }
        return update(returnMessage);
    }

    private String noEating(String shore) {

        List<Element> elementsOfSameShore = getElementsOfTheShore(elementsList, shore);
        String describeEating = "";
        for ( int i = 0; i < elementsOfSameShore.size() ; i++) {
            for ( int j = 0; j < elementsOfSameShore.size() ; j++) {
                String predatorPrey = elementsOfSameShore.get(i).getStringProperty();
                String prey = elementsOfSameShore.get(j).getName();
                if ( predatorPrey.equals(elementsOfSameShore.get(j).getName())) {
                    describeEating = "The " + elementsOfSameShore.get(i).getName()  + " will eat the " + prey;
//                    noEats = false;
                }
            }
        }
        return describeEating;
    }

    private String doLeaving(String returnMessage, String name) {
        if ( player.getStringProperty().equals(name) ) {
            player.setStringProperty("nothing");
            actualState.doAction(player.getState(), name);
            returnMessage = "You leave the " + name + ".";
        } else {
            returnMessage = "You don't have the " + name + ", you have " + player.getStringProperty();
        }
        return returnMessage;
    }


    private String doTaking(String returnMessage, String name) {
        int index = getIndexOf(name);
        if ( index >= 0 ) {
            Element element = elementsList.get(index);
            // Chequeo que esten los dos en la misma orilla
            if ( player.getState().equals(element.getState()) ) {
                // Si el player no tiene ningun elemento
                if ( player.getStringProperty().equals("nothing") ) {
                    // Le asigno el elemento que takie
                    player.setStringProperty( name );
                    returnMessage = "You took the " + name + ".";
                } else {
                    returnMessage = "You can't do that! You already have the " + player.getStringProperty();
                }
            }
        }
        return returnMessage;
    }

    private String doCrossing(String returnMessage, String shore) {
        if ( !player.getState().equals( shore ) ) {
            String describeEating = noEating(player.getState());
            if ( describeEating.equals("") ) {
                actualState.doAction(shore, player.getName());
                returnMessage = "You have crossed!";
            } else {
                returnMessage = "You can't do that! " + describeEating;
            }
        }
        return returnMessage;
    }

    private List<Element> getElementsOfTheShore(List<Element> elementsList, String shore) {
        List<Element> elementsOfMyStack = new ArrayList<Element>();
        for ( Element element : elementsList) {
            if (element.getState().equals(shore) && !element.getName().equals(player.getStringProperty())) {
                elementsOfMyStack.add(element);
            }
        }

        return elementsOfMyStack;
    }

    private int getIndexOf(String elementName) {
        int indexOfElement = -1;
        for ( int i = 0; i < elementsList.size() && indexOfElement < 0; i++ ) {
            if ( elementsList.get(i).getName().equals( elementName ) ) {
                indexOfElement = i;
            }
        }
        return indexOfElement;
    }

    private void createActualState() {

        Element sheep = new Element("sheep", "south-shore", "col");
        sheep.addActionState("north-shore", "north-shore");
        sheep.addActionState("south-shore", "south-shore");

        Element wolf = new Element("wolf", "south-shore", "sheep");
        wolf.addActionState("north-shore", "north-shore");
        wolf.addActionState("south-shore", "south-shore");

        Element col = new Element("col", "south-shore", "");
        col.addActionState("north-shore", "north-shore");
        col.addActionState("south-shore", "south-shore");

        player = new Element("farmer", "south-shore", "nothing");
        player.addActionState("north-shore", "north-shore");
        player.addActionState("south-shore", "south-shore");

        actualState = new State();
        actualState.addElement(sheep);
        actualState.addElement(wolf);
        actualState.addElement(col);
        actualState.addElement(player);

        elementsList = actualState.getElementList();
    }

//    protected void createFinalStateWolfSheepAndCabbage() {
//        finalState = new State();
//        finalState.addElement(new Element("sheep", "north-shore"));
//        finalState.addElement(new Element("wolf", "north-shore"));
//        finalState.addElement(new Element("col", "north-shore"));
//        finalState.addElement(new Element("player", "north-shore"));
//    }
}
