package ar.fiuba.tdd.tp;

import java.util.*;

public class State {

    private List<Element> elementList;
    private Map<String,State> actionMap;

    public State() {
        this.elementList = new ArrayList<>();
        this.actionMap = new HashMap<>();
    }

    public void addElement(Element element) {
        elementList.add(element);
    }

    public void addTransition(String action, State state) {

        this.actionMap.put(action, state);
    }

    public State getNextState(String action) {

        if ( actionMap.containsKey(action) ) {
            return actionMap.get(action);
        } else {
            return null;
//            throw new Exception("Invalid" + action);
        }

    }

    public String showStateItems() {

        StringBuffer elementsContained = new StringBuffer();
        elementsContained.append("There's a ");

        int elementsLizSize = elementList.size();
        for (int i = 0; i < elementsLizSize ; i++) {
            elementsContained.append(elementList.get(i).getName());
            if (i == elementsLizSize - 2) {
                elementsContained.append( " and a ");
            } else if (i != elementsLizSize - 1){
                elementsContained.append( ", ");
            }
        }
        elementsContained.append(" in the room");

        return elementsContained.toString();
    }
}
