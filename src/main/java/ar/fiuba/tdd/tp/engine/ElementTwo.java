package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.icommand.ICommand;

import java.util.*;

public class ElementTwo {

    private boolean state;
    private String name;
    private Map<String, ICommand> commandMap;
    private Map<String, ElementTwo> elementMap;
    private int size;
    private ElementTwo objetiveElement;
    private int capacity;


    public ElementTwo(String name) {
        commandMap = new HashMap<String, ICommand>();
        elementMap = new HashMap<String, ElementTwo>();
        this.name = name;
        this.state = false;
        this.capacity = 999;
        this.size = 1;
        this.objetiveElement = null;
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

    public boolean addElement(ElementTwo element) {
        if (this.capacity - element.getSize() >= 0) {
            elementMap.put(element.getName(),element);
            this.capacity = this.capacity - element.getSize();
            return true;
        }
        return false;
    }

    public void removeElement(ElementTwo element) {

        elementMap.remove(element.getName());
        this.capacity = this.capacity + element.getSize();
    }

    public List<ElementTwo> getElementList() {
        List<ElementTwo> returnElementList = new ArrayList<ElementTwo>(elementMap.values());
        return returnElementList;
    }

    public List<ICommand> getCommandList() {
        List<ICommand> returnCommandList = new ArrayList<ICommand>(commandMap.values());
        return returnCommandList;
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

    public ElementTwo getObjetiveElement() {
        return objetiveElement;
    }

    public void setObjetiveElement(ElementTwo objetiveElement) {
        this.objetiveElement = objetiveElement;
    }

    public Map<String, ElementTwo> getVisibleElements() {
        Map<String, ElementTwo> visibleElements = new HashMap<>();
        for (ElementTwo element: getElementList()) {
            if (element.getState()) {
                visibleElements.put(element.getName(),element);
            }
        }
        return visibleElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
