package ar.fiuba.tdd.tp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        StringBuffer elementNames = new StringBuffer();
        for (Element element : elementList) {
            elementNames.append(element.getName() + " ");
        }
        return elementNames.toString();
    }
}
