package ar.fiuba.tdd.tp;

import java.util.HashMap;
import java.util.Map;

public class Element {

    private String name;
    private String state;
    private int intProperty;
    private String stringProperty;

    private Map<String,String> actionStateMap;

    public Element(String name, String state) {
        this.name = name;
        this.state = state;
        this.actionStateMap = new HashMap<>();
    }

    public Element(String name, String state, int intProperty) {
        this.name = name;
        this.state = state;
        this.actionStateMap = new HashMap<>();
        this.intProperty = intProperty;
    }

    public Element(String name, String state, String stringProperty) {
        this.name = name;
        this.state = state;
        this.actionStateMap = new HashMap<>();
        this.stringProperty = stringProperty;
    }

    public Element(String name, String state, int intProperty, String stringProperty) {
        this.name = name;
        this.state = state;
        this.actionStateMap = new HashMap<>();
        this.intProperty = intProperty;
        this.stringProperty = stringProperty;
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
            return "There you go";
        } else {
            return "Hey! I'm a " + this.getName() + ", I can't do that!";
        }
    }
}
