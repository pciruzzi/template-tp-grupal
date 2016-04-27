package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.ArrayList;
import java.util.List;

public class HanoiTowers extends Game {

    @Override
    public void createGame() {

        finalState = new State();
        finalState.addElement(new Element("diskOne", "columnThree"));
        finalState.addElement(new Element("diskTwo", "columnThree"));
        finalState.addElement(new Element("diskThree", "columnThree"));

        createActualState();
    }

    @Override
    public String doAction(String action) {

        String[] parts = action.split(" ");
        String[] checkedInput = checkInput(parts);
        String movingFromStack  = checkedInput[0];
        String movingToStack    = checkedInput[1];

        List<Element> elementsOfMyStack = getElementsOfTheStack(movingFromStack);
        List<Element> elementsOfOtherStack = getElementsOfTheStack(movingToStack);

        int indexOfSmallest = getIndexOfSmallestDisk(elementsOfMyStack);
        int indexOfSmallestOfOtherStack = getIndexOfSmallestDisk(elementsOfOtherStack);

        if ( indexOfSmallest < 0 ) {
            return "The stack is empty.";
        }

        int smallest = elementsOfMyStack.get(indexOfSmallest).getIntProperty();

        int smallestOfOtherStack = -1;

        if ( indexOfSmallestOfOtherStack >= 0 ) {
            smallestOfOtherStack = elementsOfOtherStack.get(indexOfSmallestOfOtherStack).getIntProperty();

            if ( smallest > smallestOfOtherStack ) {
                return "You can't stack a bigger disk over smaller one.";
            }
        }

        String name = elementsOfMyStack.get(indexOfSmallest).getName();

        String returnMessage = actualState.doAction(movingToStack,name);

        return update(returnMessage);
    }

    private List<Element> getElementsOfTheStack(String stack) {
        List<Element> elementsOfMyStack = new ArrayList<Element>();
        for ( Element element : elementsList) {
            if (element.getState().equals(stack)) {
                elementsOfMyStack.add(element);
            }
        }

        return elementsOfMyStack;
    }

    private int getIndexOfSmallestDisk(List<Element> elementsList) {

        int iterator = 0;
        int indexOfSmallest = -1;
        if ( elementsList.size() > 0 ) {
            indexOfSmallest = iterator;
            int smallest = elementsList.get(iterator).getIntProperty();
            for ( iterator = 1; iterator < elementsList.size(); iterator++) {
                if ( elementsList.get(iterator).getIntProperty() < smallest ) {
                    smallest = elementsList.get(iterator).getIntProperty();
                    indexOfSmallest = iterator;
                }
            }
        }

        return indexOfSmallest;
    }

    private String[] checkInput(String[] parts) {

        if ( parts.length != 2) {
            String badInput = "bad input";
            return badInput.split(" ");
        }
        return parts;
    }

//    private String update(String action, String name) {
//        if ( actualState.isEqual(finalState)) {
//            returnMessage = "You won the game";
//        }
//        return returnMessage;
//    }

    private void createActualState() {

        Element diskOne = new Element("diskOne", "columnOne", 1);
        diskOne.addActionState("columnOne", "columnOne");
        diskOne.addActionState("columnTwo", "columnTwo");
        diskOne.addActionState("columnThree", "columnThree");
        Element diskTwo = new Element("diskTwo", "columnOne", 2);
        diskTwo.addActionState("columnOne", "columnOne");
        diskTwo.addActionState("columnTwo", "columnTwo");
        diskTwo.addActionState("columnThree", "columnThree");
        Element diskThree = new Element("diskThree", "columnOne", 3);
        diskThree.addActionState("columnOne", "columnOne");
        diskThree.addActionState("columnTwo", "columnTwo");
        diskThree.addActionState("columnThree", "columnThree");

        actualState = new State();
        actualState.addElement(diskOne);
        actualState.addElement(diskTwo);
        actualState.addElement(diskThree);

//        actualState.addDesiredState(finalState);
//        actualState.addNextState(finalState);

        elementsList = actualState.getElementList();
    }
}
