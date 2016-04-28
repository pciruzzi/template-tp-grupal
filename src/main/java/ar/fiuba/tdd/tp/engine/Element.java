package ar.fiuba.tdd.tp.engine;

import java.util.*;

public class Element {

    private String name;
    private String state;
    private int intProperty;
    private String stringProperty;

    private Map<String,String> actionStateMap;


    public Element(String name, String state) {
        this.name = name;
        this.state = state;
        this.actionStateMap = new HashMap<String, String>();
        this.intProperty = -1;
        this.stringProperty = "";
    }

    public Element(String name, String state, int intProperty) {
        this(name, state);
        this.intProperty = intProperty;
    }

    public Element(String name, String state, String stringProperty) {
        this(name, state);
        this.stringProperty = stringProperty;
    }

    public Element(String name, String state, int intProperty, String stringProperty) {
        this(name, state);
        this.intProperty = intProperty;
        this.stringProperty = stringProperty;
    }

    public Element copy() {
        String state = this.getState();
        Element copy = new Element(this.getName(),state,this.getIntProperty(),this.getStringProperty());
        copy.setActionStateMap(this.getActionStateMap());
        return copy;
    }

    public Map<String,String> getActionStateMap() {
        return actionStateMap;
    }

    public void setActionStateMap(Map<String,String> actionStateMap) {
        this.actionStateMap = actionStateMap;
    }

    public int getIntProperty() {
        return intProperty;
    }

    public void setIntProperty(int intProperty) {
        this.intProperty = intProperty;
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public String getStringProperty() {
        return stringProperty;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public void addActionState(String action, String state) {
        if ( actionStateMap.containsKey(action) ) {
            System.out.println("You are inserting a duplicated action in the element. Element action: " + action);
        } else {
            actionStateMap.put(action, state);
        }
    }

    public String changeState(String action) {
        if ( actionStateMap.containsKey(action) ) {
            state = actionStateMap.get(action);
            return "Ok.";
        } else {
            if (! stringProperty.equals("")) {
                return stringProperty;
            }
            return "Hey! I'm a " + this.getName() + ", I can't do that!";
        }
    }

    public String getPossibleActions() {

        StringBuffer possibleActions = new StringBuffer();
        possibleActions.append("You can ");

        int counter = 0;
        int actionsSize = actionStateMap.size();
        for (Map.Entry<String, String> entry : actionStateMap.entrySet()) {
            possibleActions.append(entry.getKey());
            if ( counter != actionsSize - 1) {
                possibleActions.append( "/");
            }
            counter++;
        }

        possibleActions.append(" the " + name);

        return possibleActions.toString();
    }

    public List<String> getActionsList() {
        List<String> actionsList = new ArrayList<>();
        for (Map.Entry<String, String> entry : actionStateMap.entrySet()) {
            actionsList.add(entry.getValue());
        }
        return actionsList;
    }
}
