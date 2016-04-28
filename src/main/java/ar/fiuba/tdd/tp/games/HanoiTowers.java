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
        description = "El hanoi towers consiste en...";
    }

    @Override
    public Game copy() {
        return new HanoiTowers();
    }

    protected void createFinalStateHanoiTowers() {
        finalState = new State();
        for (int i = 1; i <= HANOI_AMOUNT_DISKS; i++) {
//            for (int j = 2; j <= HANOI_STACKS; j++) {
            finalState.addElement(new Element(HANOI_DISK + i, HANOI_STACK + 3/*j*/));
//            }
        }
    }

    private void createActualState() {
        actualState = new State();
        for (int i = 1; i <= HANOI_AMOUNT_DISKS; i++) {
            Element disk = new Element(HANOI_DISK + i, HANOI_STACK + 1, i);
            for (int j = 1; j <= HANOI_STACKS; j++) {
                disk.addActionState(HANOI_STACK + j, HANOI_STACK + j);
            }
            actualState.addElement(disk);
        }
        elementsList = actualState.getElementList();
    }

    @Override
    public void createGame() {
        createFinalStateHanoiTowers();
        createActualState();
    }

    private String facadeAction(String action, String actualMessage) {
        if (action.matches("move top .*")) {
            action = action.replaceAll("^move top ", "");
            return action;
        }
        return actualMessage;
    }

    private boolean validStackName(String stack) {
        return stack.matches("^" + HANOI_STACK + "[1-" + HANOI_STACKS + "]$");
    }

    private String facadeQuestionOrCheckTop(String action, String actualMessage) {
        action = action.toLowerCase();
        if (action.matches("what can i do with .*")) {
            return HANOI_QUESTION;
        }
        if (action.matches("check top .*")) {
            return checkTopStack(action);
        }
        return actualMessage;
    }

    private String checkTopStack(String action) {
        String returnMessage = HANOI_CHECKSIZE;
        String stack = action.replaceAll("check top ", "");
        if (! validStackName(stack)) {
            return HANOI_STACK_ERROR;
        }
        List<Element> elements = getElementsOfTheStack(stack);
        int index = getIndexOfSmallestDisk(elements);
        if (index < 0) {
            return HANOI_SIZE;
        }
        Element element = elements.get(index);
        int stackSize = element.getIntProperty();
        returnMessage = returnMessage.concat(stackSize + ".");
        return returnMessage;
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

        if (shouldTryToMove(action, returnMessage)) {
            String[] parts = action.split(" ");
            String[] checkedInput = checkInput(parts);
            String movingFromStack  = checkedInput[0];
            String movingToStack    = checkedInput[1];

            List<Element> elementsOfMyStack = getElementsOfTheStack(movingFromStack);
            List<Element> elementsOfOtherStack = getElementsOfTheStack(movingToStack);

            returnMessage = facadeSmallestOfOtherStack(elementsOfMyStack, elementsOfOtherStack, returnMessage);
            if (!returnMessage.equals(HANOI_MOVEERROR)) {
                int indexOfSmallest = getIndexOfSmallestDisk(elementsOfMyStack);
                String name = elementsOfMyStack.get(indexOfSmallest).getName();
                returnMessage = actualState.doAction(movingToStack,name);
            }
        }
        return update(returnMessage);
    }

    private boolean shouldTryToMove(String action, String returnMessage) {
        return  (!action.equals(INVALID_ACTION)) && (!returnMessage.equals(HANOI_QUESTION))
                && (!returnMessage.matches("^" + HANOI_CHECKSIZE + ".*")) && (!returnMessage.equals(HANOI_SIZE))
                && (!returnMessage.equals(HANOI_STACK_ERROR));
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
}