package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.ArrayList;
import java.util.List;

public class WolfSheepAndCabbage extends Game {

    private Element player;

    @Override
    public void createGame() {

        createFinalState();

        createActualState();
    }

    @Override
    public String doAction(String action) {

        String returnMessage = "Invalid Action";

        String[] parts = action.split(" ");

        if ( parts.length != 2 ) {
            return returnMessage;
        }

        String actionToDo = parts[0];
        String name = parts[1];

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

    private boolean noEating(String shore) {

        List<Element> elementsOfSameShore = getElementsOfTheShore(elementsList, shore);
        boolean noEats = true;
        for ( int i = 0; i < elementsOfSameShore.size() && noEats; i++) {
            for ( int j = 0; j < elementsOfSameShore.size() && noEats; j++) {
                if ( elementsOfSameShore.get(i).getStringProperty().equals(elementsOfSameShore.get(j).getName())) {
                    noEats = false;
                }
            }
        }
        return noEats;
    }

    private String doLeaving(String returnMessage, String name) {
        if ( player.getStringProperty().equals(name) ) {
            player.setStringProperty("nothing");
            returnMessage = actualState.doAction(player.getState(), name);
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
                    returnMessage = "You took the " + name;
                } else {
                    returnMessage = "You already have the " + player.getStringProperty();
                }
            }
        }
        return returnMessage;
    }

    private String doCrossing(String returnMessage, String shore) {
        if ( !player.getState().equals( shore ) ) {
            if ( noEating( player.getState() ) ) {
                returnMessage = actualState.doAction( shore, player.getName() );
            } else {
                returnMessage = "They will eat each other";
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

        Element sheep = new Element("sheep", "south", "col");
        sheep.addActionState("north", "north");
        sheep.addActionState("south", "south");

        Element wolf = new Element("wolf", "south", "sheep");
        wolf.addActionState("north", "north");
        wolf.addActionState("south", "south");

        Element col = new Element("col", "south", "");
        col.addActionState("north", "north");
        col.addActionState("south", "south");

        player = new Element("player", "south", "nothing");
        player.addActionState("north", "north");
        player.addActionState("south", "south");

        actualState = new State();
        actualState.addElement(sheep);
        actualState.addElement(wolf);
        actualState.addElement(col);
        actualState.addElement(player);

        elementsList = actualState.getElementList();
    }

    private void createFinalState() {
        finalState = new State();
        finalState.addElement(new Element("sheep", "north"));
        finalState.addElement(new Element("wolf", "north"));
        finalState.addElement(new Element("col", "north"));
        finalState.addElement(new Element("player", "north"));
    }
}
