package ar.fiuba.tdd.tp;

import java.util.*;

public class State {

    private List<Element> elementList;
    private Map<String,Element> elementStateMap;

    private State desiredState;
    private State nextState;

    public State() {
        this.elementList = new ArrayList<Element>();
        this.elementStateMap = new HashMap<String, Element>();
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

    public void addDesiredState(State desiredState) {
        this.desiredState = desiredState;
    }

    public void addNextState(State nextState) {
        this.nextState = nextState;
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
            return "It doesn't exist the item: " + elementName + " or the action: " + action;
        }
    }

    public State getNextState() {
        return nextState;
    }

    public State getDesiredState() {
        return desiredState;
    }



    public String showStateItems() {

        StringBuffer elementsContained = new StringBuffer();
        elementsContained.append("There's a ");

        int elementsLizSize = elementList.size();
        for (int i = 0; i < elementsLizSize ; i++) {
            elementsContained.append(elementList.get(i).getName());
            if (i == elementsLizSize - 2) {
                elementsContained.append( " and a ");
            } else if (i != elementsLizSize - 1) {
                elementsContained.append( ", a ");
            }
        }
        elementsContained.append(" in the room");

        return elementsContained.toString();
    }

    private boolean checkSize(Map<String,Element> othersElementStateMap) {
        int elementMapSize = this.elementStateMap.size();
        return elementMapSize == othersElementStateMap.size();
    }

    public boolean isEqual(State otherState) {

        boolean equal = true;

        Map<String,Element> othersElementStateMap = otherState.getElementStateMap();

        equal = checkSize(othersElementStateMap);

        Iterator elementsIterator = elementStateMap.entrySet().iterator();
        while (elementsIterator.hasNext() && equal) {
            Map.Entry pair = (Map.Entry) elementsIterator.next();
            String name = (String) pair.getKey();
            Element element = (Element) pair.getValue();

            Element othersElement = othersElementStateMap.get(name);

            if ( othersElement != null ) {
                if ( !othersElement.getState().equals(element.getState()) ) {
                    equal = false;
                }
            } else {
                equal = false;
            }
        }
        return equal;
    }
}
