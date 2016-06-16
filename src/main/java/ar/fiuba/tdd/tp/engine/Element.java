package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.ITimeCommand;
import ar.fiuba.tdd.tp.time.TimeCommand;

import java.util.*;

public class Element implements Cloneable {

    private String name;
    private Map<String, ICommand> commandMap;
    protected Map<String, ITimeCommand> timeCommandMap;
    private Map<String, Element> elementMap;
    private Map<String, State> stateMap;
    private State stateToAffect;
    private int size;
    private Element objectiveElement;
    private int capacity;


    public Element(String name) {
        commandMap = new HashMap<>();
        timeCommandMap = new HashMap<>();
        elementMap = new HashMap<>();
        stateMap = new HashMap<>();
        stateMap.put("visible", new State("visible", false, false));
        this.name = name;
        this.capacity = 999;
        this.size = 1;
        this.objectiveElement = null;
    }

    public Element getClone() {
        try {
            return (Element) super.clone();
        } catch (CloneNotSupportedException c) {
            System.out.println("Error cuando se quiere clonar un Element");
            return null;
        }
    }

    public String doCommand(String commandName, int playerId) {
        if (commandMap.containsKey(commandName)) {
            ICommand command = commandMap.get(commandName);
            return command.doAction(this, playerId);
        } else {
            return "I can't do that.";
        }
    }

    public String doCommand(String commandName, Element originElement, Element destElement, int playerId) {
        if (commandMap.containsKey(commandName)) {
            ICommand command = commandMap.get(commandName);
            return command.doAction(originElement, this, destElement, playerId);
        } else {
            return "I can't do that.";
        }
    }

    public void addTimeCommand(ITimeCommand command) {
        timeCommandMap.put(command.getName(), command);
    }

    public void addCommand(ICommand command) {
        commandMap.put(command.getName(), command);
    }

    public void addState(State state) {
        stateMap.put(state.getName(), state);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, ICommand> getCommandMap() {
        return commandMap;
    }

    public boolean addElement(Element element) {
        if (this.capacity - element.getSize() >= 0) {
            elementMap.put(element.getName(), element);
            this.capacity = this.capacity - element.getSize();
            return true;
        }
        return false;
    }

    public void removeElement(Element element) {
        elementMap.remove(element.getName());
        this.capacity = this.capacity + element.getSize();
    }

    public State getStateToAffect() {
        return stateToAffect;
    }

    public void setStateToAffect(State stateToAffect) {
        this.stateToAffect = stateToAffect;
    }

    public List<Element> getElementList() {
        return new ArrayList<>(elementMap.values());
    }

    public List<ICommand> getCommandList() {
        return new ArrayList<>(commandMap.values());
    }

    public Map<String, State> getStateList() {
        return stateMap;
    }

    public String getName() {
        return name;
    }

    public boolean hasElement(String element) {
        return this.elementMap.containsKey(element);
    }

    public Element getElement(String element) {
        return this.elementMap.get(element);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public Element getObjectiveElement() {
        return objectiveElement;
    }

    public void setObjectiveElement(Element objectiveElement) {
        this.objectiveElement = objectiveElement;
    }

    public Map<String, Element> getVisibleElements() {
        Map<String, Element> visibleElements = new HashMap<>();
        for (Element element : getElementList()) {
            if (element.getValueOfState("visible")) {
                for (Element insideElement : getElementList()) {
                    visibleElements.putAll(insideElement.getVisibleElements());
                }
                visibleElements.put(element.getName(), element);
            }
        }
        return visibleElements;
    }

    public boolean hasState(String state) {
        return stateMap.containsKey(state);
    }

    public boolean getValueOfState(String state) {
        if (hasState(state)) {
            return stateMap.get(state).isActive();
        } else {
            return false;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, Element> getElementMap() {
        return elementMap;
    }

    public void changeElementsState(String state, boolean value) {
        for (Element element : getElementList()) {
            element.changeState(state, value);
        }
    }

    public void changeState(String stateName, boolean value) {
        if ( stateMap.containsKey(stateName) ) {
            stateMap.get(stateName).setActive(value);
        } else {
            System.out.println("No pude cambiar el estado: " + stateName + " de " + name);
        }
    }

    public boolean hasAllElements(List<String> elementsToContain) {

        if (elementMap.size() == 0) {
            return false;
        }
        for (String elementName : elementsToContain) {
            if (!elementMap.keySet().contains(elementName)) {
                return false;
            }
        }
        return true;
    }

    public List<State> getTrueList() {
        List<State> returnList = new ArrayList<>();
        Set<String> keySet = stateMap.keySet();
        for (String state : keySet) {
            if (stateMap.get(state).isActive() && (stateMap.get(state).getName().compareTo("visible") != 0)) {
                returnList.add(stateMap.get(state));
            }
        }
        return returnList;
    }

}
