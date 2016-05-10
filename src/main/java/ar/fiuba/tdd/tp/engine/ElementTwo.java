package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.icommand.ICommand;

import java.util.*;

public class ElementTwo {

    private boolean state;

    private String name;
    private Map<String, ICommand> commandMap;
    private Map<String, ElementTwo> elementMap;

    private int capacity;

    public ElementTwo(String name, boolean state) {
        commandMap = new HashMap<String, ICommand>();
        elementMap = new HashMap<String, ElementTwo>();
        this.name = name;
        this.state = state;
        this.capacity = 999;
    }

    public String doCommand(String commandName) {
        if (commandMap.containsKey(commandName)) {
            ICommand command = commandMap.get(commandName);
            return command.doAction(this);
        } else {
            return "I can't do that.";
        }
    }

    public void addCommand(ICommand command) {
        commandMap.put(command.getName(), command);
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, ICommand> getCommandMap() {
        return commandMap;
    }

    public void addElement(ElementTwo element) {

        elementMap.put(element.getName(),element);
    }

    public void removeElement(ElementTwo element) {
        elementMap.remove(element.getName());
    }

    public List<ElementTwo> getElementList() {
        List<ElementTwo> returnList = new ArrayList<ElementTwo>();
        for (String key : elementMap.keySet()) {
            returnList.add(elementMap.get(key));
        }
        return returnList;
    }

    public boolean getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public boolean hasElement(String element) {
        return this.elementMap.containsKey(element);
    }

    public ElementTwo getElement(String element) {
        return this.elementMap.get(element);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<String, ElementTwo> getVisibleElements() {
        HashMap<String, ElementTwo> visibleElements = new HashMap<>();

        for (ElementTwo element: getElementList()) {
            if (element.getState()) {
                visibleElements.put(element.getName(),element);
            }
        }
        return visibleElements;
    }
}
