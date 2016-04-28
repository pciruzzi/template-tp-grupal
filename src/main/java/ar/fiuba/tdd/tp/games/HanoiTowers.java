package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.tp.Constants.*;

public class HanoiTowers extends Game {


    public HanoiTowers() {
        gameWon = false;
        name = "hanoi towers";
        this.description = "El hanoi towers consiste en...";
    }

    @Override
    public Game copy() {
        return new HanoiTowers();
    }

    @Override
    public void createGame() {

        finalState = new State();
        finalState.addElement(new Element("diskOne", "columnThree"));
        finalState.addElement(new Element("diskTwo", "columnThree"));
        finalState.addElement(new Element("diskThree", "columnThree"));

        createActualState();
    }

//<<<<<<< Updated upstream
    private String facadeAction(String action, String actualMessage) {
        if (action.matches("move top .*")) {
            action = action.replaceAll("^move top ", "");
            return action;
        }
        return actualMessage;
    }
//=======
//    @Override
//    public String doAction(String action) {
//
////        if ( checkQuestionMessage(action) ) {
////            System.out.println(action.lastIndexOf("What can I do with "));
////            String nameOfObject = action.substring(19);
////            nameOfObject = nameOfObject.replace("?", " ");
////            nameOfObject = nameOfObject.trim();
////            return answerQuestion(nameOfObject);
////        }
//
//        String[] parts = action.split(" ");
//        String[] checkedInput = checkInput(parts);
//        String movingFromStack  = checkedInput[0];
//        String movingToStack    = checkedInput[1];
//
//        List<Element> elementsOfMyStack = getElementsOfTheStack(movingFromStack);
//        List<Element> elementsOfOtherStack = getElementsOfTheStack(movingToStack);
//>>>>>>> Stashed changes

    private String facadeQuestionOrCheckTop(String action, String actualMessage) {
        if (action.matches("What can I do with .*")) {
            return HANOI_QUESTION;
        }
        if (action.matches("check top .*")) {
            String returnMessage = HANOI_CHECKSIZE;
//            returnMessage.concat(getSizeOfColumn(string column));
            return returnMessage.concat(".");
            //TODO: Falta implementar la funcion para obtener el tamaño del stack
//            return returnMessage;
        }
        return actualMessage;
    }

    private String facadeIndexSmallest(int indexOfSmallest, String actualMessage) {
        if ( indexOfSmallest < 0 ) {
            return HANOI_SIZE;
        }
        return actualMessage;
    }

    private String facadeSmallestOfOtherStack(List<Element> elementsOfMyStack, List<Element> elementsOfOtherStack, String actualMessage) {
        int indexOfSmallestOfOtherStack = getIndexOfSmallestDisk(elementsOfOtherStack);
        int indexOfSmallest = getIndexOfSmallestDisk(elementsOfMyStack);
        int smallest = elementsOfMyStack.get(indexOfSmallest).getIntProperty();

        int smallestOfOtherStack = -1;

        if ( indexOfSmallestOfOtherStack >= 0 ) {
            smallestOfOtherStack = elementsOfOtherStack.get(indexOfSmallestOfOtherStack).getIntProperty();

            if ( smallest > smallestOfOtherStack ) {
                return HANOI_MOVEERROR;
            }
        }
        return actualMessage;
    }

    @Override
    public String doAction(String action) {

        String returnMessage = INVALID_ACTION;

        returnMessage = facadeQuestionOrCheckTop(action, returnMessage);
        action = facadeAction(action, returnMessage);

        if ((!action.equals(INVALID_ACTION)) && (!returnMessage.equals(HANOI_QUESTION)) && (!returnMessage.matches(HANOI_CHECKSIZE))) {
            String[] parts = action.split(" ");
            String[] checkedInput = checkInput(parts);
            String movingFromStack  = checkedInput[0];
            String movingToStack    = checkedInput[1];

            List<Element> elementsOfMyStack = getElementsOfTheStack(movingFromStack);
            List<Element> elementsOfOtherStack = getElementsOfTheStack(movingToStack);

            int indexOfSmallest = getIndexOfSmallestDisk(elementsOfMyStack);
            returnMessage = facadeIndexSmallest(indexOfSmallest, returnMessage);
            if (!returnMessage.equals(HANOI_SIZE)) {
                returnMessage = facadeSmallestOfOtherStack(elementsOfMyStack, elementsOfOtherStack, returnMessage);
                if (!returnMessage.equals(HANOI_MOVEERROR)) {
                    String name = elementsOfMyStack.get(indexOfSmallest).getName();
                    returnMessage = actualState.doAction(movingToStack,name);
                }
            }
        }
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

        elementsList = actualState.getElementList();
    }
}
