package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.icommand.ICommand;

import java.util.*;

public class Element implements Cloneable {

    private boolean state;
    private String name;
    private Map<String, ICommand> commandMap;
    private Map<String, Element> elementMap;
    private int size;
    private Element objectiveElement;
    private int capacity;
    private boolean poisoned;

    private boolean isAntidote;

    public Element(String name) {
        commandMap = new HashMap<>();
        elementMap = new HashMap<>();
        this.name = name;
        this.state = false;
        this.capacity = 999;
        this.size = 1;
        this.objectiveElement = null;
        this.poisoned = false;
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

    public boolean addElement(Element element) {
        if (this.capacity - element.getSize() >= 0) {
            elementMap.put(element.getName(),element);
            this.capacity = this.capacity - element.getSize();
            return true;
        }
        return false;
    }

    public void removeElement(Element element) {
        elementMap.remove(element.getName());
        this.capacity = this.capacity + element.getSize();
    }

    public List<Element> getElementList() {
        return new ArrayList<>(elementMap.values());
    }

    public List<ICommand> getCommandList() {
        return new ArrayList<>(commandMap.values());
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
        for (Element element: getElementList()) {
            if (element.getState()) {
                for (Element insideElement : getElementList()) {
                    visibleElements.putAll(insideElement.getVisibleElements());
                }
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

    public Map<String, Element> getElementMap() {
        return elementMap;
    }

    public void changeElementsState(boolean state) {
        for (Element element : getElementList()) {
            element.setState(state);
        }
    }

    public boolean hasAllElements(ArrayList<String> elementsToContain) {

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

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    public boolean isAntidote() {
        return isAntidote;
    }

    public void setAntidote(boolean antidote) {
        isAntidote = antidote;
    }
}
