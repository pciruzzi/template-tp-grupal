package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.icommand.ICommand;

import java.util.*;

public class ElementTwo {

    private boolean state;

    private String name;
    private Map<String, ICommand> commandMap;
    private Map<String, ElementTwo> elementMap;

    public ElementTwo(String name, boolean state) {
        commandMap = new HashMap<String, ICommand>();
        elementMap = new HashMap<String, ElementTwo>();
        this.name = name;
        this.state = state;
    }

    public String doCommand(String commandName) {
        if (commandMap.containsKey(commandName)) {
            ICommand command = commandMap.get(commandName);
            command.doAction(this);
            return "Ok.";
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
}
