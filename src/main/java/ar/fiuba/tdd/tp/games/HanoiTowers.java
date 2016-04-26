package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.State;

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

        List<Element> elements = actualState.getElementList();
        List<Element> elementsOfMyStack = getElementsOfTheStack(elements, parts[0]);
        List<Element> elementsOfOtherStack = getElementsOfTheStack(elements, parts[1]);

        int indexOfSmallest = getIndexOfSmallestDisk(elementsOfMyStack);
        int indexOfSmallestOfOtherStack = getIndexOfSmallestDisk(elementsOfOtherStack);

        if ( indexOfSmallest < 0 ) {
            return "The stack is empty.";
        }

        int smallest = elementsOfMyStack.get(indexOfSmallest).getSize();

        int smallestOfOtherStack = -1;

        if ( indexOfSmallestOfOtherStack >= 0 ) {
            smallestOfOtherStack = elementsOfOtherStack.get(indexOfSmallestOfOtherStack).getSize();

            if ( smallest > smallestOfOtherStack ) {
                return "You can't stack a bigger disk over smaller one.";
            }
        }

        String name = elementsOfMyStack.get(indexOfSmallest).getName();

        return update(parts[1], name);
    }

    private List<Element> getElementsOfTheStack(List<Element> elementsList, String stack) {
        List<Element> elementsOfMyStack = new ArrayList<>();
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
            int smallest = elementsList.get(iterator).getSize();
            for ( iterator = 1; iterator < elementsList.size(); iterator++) {
                if ( elementsList.get(iterator).getSize() < smallest ) {
                    smallest = elementsList.get(iterator).getSize();
                    indexOfSmallest = iterator;
                }
            }
        }

        return indexOfSmallest;
    }

    private String update(String action, String name) {
        String returnMessage = actualState.doAction(action,name);
        if ( actualState.isEqual(finalState)) {
            returnMessage = "You won the game";
        }
        return returnMessage;
    }

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

        actualState.addDesiredState(finalState);
        actualState.addNextState(finalState);
    }
}
