package ar.fiuba.tdd.tp.engine;

import java.util.*;

public class State {

    private List<Element> elementList;
    private Map<String,Element> elementStateMap;

    private Map<State,State> desiredAndNextStateMap;

    public State() {
        this.elementList = new ArrayList<Element>();
        this.elementStateMap = new HashMap<String, Element>();
        this.desiredAndNextStateMap = new HashMap<State, State>();
    }

    public State copy() {
        State copy = new State();
        List<Element> othersElementsList = new ArrayList<Element>();
        for ( Element element : elementList ) {
            Element elementCopy = element.copy();
            othersElementsList.add(elementCopy);
        }

        Map<String,Element> othersStateMap = new HashMap<String, Element>(this.elementStateMap);
        for ( Map.Entry<String, Element> entry : elementStateMap.entrySet() ) {
            Element elementCopy = entry.getValue().copy();
            othersStateMap.put(entry.getKey(),elementCopy);
        }

        Map<State,State> othersDesiredAndNextStateMap = new HashMap<State, State>(this.desiredAndNextStateMap);
        copy.setElementList(othersElementsList);
        copy.setElementStateMap(othersStateMap);
        copy.setDesiredAndNextStateMap(othersDesiredAndNextStateMap);
        return copy;
    }

    public void setElementList(List<Element> elementsList) {
        this.elementList = elementsList;
    }

    public void setElementStateMap(Map<String, Element> elementStateMap) {
        this.elementStateMap = elementStateMap;
    }

    public void setDesiredAndNextStateMap(Map<State,State> desiredAndNextStateMap) {
        this.desiredAndNextStateMap = desiredAndNextStateMap;
    }

    public void addElement(Element element) {
        String name = element.getName();
        if ( elementStateMap.containsKey(name) ) {
            System.out.println("You are inserting a duplicated element in the state. Element name: " + name);
        } else {
            elementStateMap.put(element.getName(), element);
        }
        elementList.add(element);
    }

    public void addDesiredAndNextState(State desiredState, State nextState) {
        boolean duplicatedDesiredState = false;
        for ( Map.Entry<State,State> entry : desiredAndNextStateMap.entrySet()) {
            if ( entry.getKey().isEqual(desiredState)) {
                System.out.println("You are adding a duplicated desiredState.");
                duplicatedDesiredState = true;
            }
        }
        if ( !duplicatedDesiredState) {
            desiredAndNextStateMap.put(desiredState, nextState);
        }
    }

    public Map<State,State> getDesiredAndNextStateMap() {
        return desiredAndNextStateMap;
    }

    public Map<String,Element> getElementStateMap() {
        return elementStateMap;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public String doAction(String action, String elementName) {

        if ( elementStateMap.containsKey(elementName) ) {
            Element element = elementStateMap.get(elementName);
            return element.changeState(action);
        } else {
            return "It doesn't exist the item: " + elementName + " or the action: " + action + ".";
        }
    }

    private boolean checkIfTheElementMustBeShown(Element element) {

        return element.getState().equals("closed") || element.getState().equals("floor")
                || element.getState().equals("opened");
    }

    public String showStateItems() {

        StringBuilder elementsContained = new StringBuilder();
        elementsContained.append("There's a ");

        int elementsLizSize = elementList.size();
        for (int i = 0; i < elementsLizSize ; i++) {
            Element element = elementList.get(i);
            if (checkIfTheElementMustBeShown(element)) {
                elementsContained.append(elementList.get(i).getName());
                if (i == elementsLizSize - 2) {
                    elementsContained.append( " and a ");
                } else if (i != elementsLizSize - 1) {
                    elementsContained.append( ", a ");
                }
            }
        }
        elementsContained.append(" in the room.");

        return elementsContained.toString();
    }

    private boolean checkSize(Map<String,Element> othersElementStateMap) {
        int elementMapSize = this.elementStateMap.size();
        return elementMapSize == othersElementStateMap.size();
    }

    public boolean isEqual(State otherState) {

        boolean equal;

        Map<String,Element> othersElementStateMap = otherState.getElementStateMap();

        equal = checkSize(othersElementStateMap);

        Iterator elementsIterator = elementStateMap.entrySet().iterator();
        while (elementsIterator.hasNext() && equal) {
            Map.Entry pair = (Map.Entry) elementsIterator.next();
            String name = (String) pair.getKey();
            Element element = (Element) pair.getValue();
            Element othersElement = othersElementStateMap.get(name);
            if ( othersElement != null ) {
                if (! othersElement.getState().equals(element.getState()) ) {
                    equal = false;
                }
            } else {
                equal = false;
            }
        }
        return equal;
    }
}