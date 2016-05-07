package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.icommand.ICommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ElementTwo {

    private boolean state;

    private String name;
    private HashMap<String, ICommand> commandMap;

//    private int intProperty;
//    private String stringProperty;

    private HashMap<String, ElementTwo> elementMap;

    public ElementTwo(String name, boolean state) {
        commandMap = new HashMap<>();
        elementMap = new HashMap<>();
        this.name = name;
        this.state = state;
    }

    public String doCommand(String commandName) {

        if (commandMap.containsKey(commandName)) {
            ICommand command = commandMap.get(commandName);
            command.doAction(this);
            return "ok";
        } else {
            return "I can't do that";
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

    public HashMap<String, ICommand> getCommandMap() {
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
        for ( String key : elementMap.keySet() ) {
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


//        private String name;
//        private String state;


//        private Map<String,String> actionStateMap;


//        public ElementTwo(String name, String state) {
//            this.name = name;
//            this.state = state;
//            this.actionStateMap = new HashMap<String, String>();
//            this.intProperty = -1;
//            this.stringProperty = "";
//        }

//        public Element(String name, String state, int intProperty) {
//            this(name, state);
//            this.intProperty = intProperty;
//        }
//
//        public Element(String name, String state, String stringProperty) {
//            this(name, state);
//            this.stringProperty = stringProperty;
//        }
//
//        public Element(String name, String state, int intProperty, String stringProperty) {
//            this(name, state);
//            this.intProperty = intProperty;
//            this.stringProperty = stringProperty;
//        }

//        public Element copy() {
//            String state = this.getState();
//            Element copy = new Element(this.getName(),state,this.getIntProperty(),this.getStringProperty());
//            copy.setActionStateMap(this.getActionStateMap());
//            return copy;
//        }
//
//        public Map<String,String> getActionStateMap() {
//            return actionStateMap;
//        }
//
//        public void setActionStateMap(Map<String,String> actionStateMap) {
//            this.actionStateMap = actionStateMap;
//        }
//
//        public int getIntProperty() {
//            return intProperty;
//        }
//
//        public void setIntProperty(int intProperty) {
//            this.intProperty = intProperty;
//        }
//
//        public void setStringProperty(String stringProperty) {
//            this.stringProperty = stringProperty;
//        }
//
//        public String getStringProperty() {
//            return stringProperty;
//        }


//        public void addActionState(String action, String state) {
//            if ( actionStateMap.containsKey(action) ) {
//                System.out.println("You are inserting a duplicated action in the element. Element action: " + action);
//            } else {
//                actionStateMap.put(action, state);
//            }
//        }
//
//        public String changeState(String action) {
//            if ( actionStateMap.containsKey(action) ) {
//                state = actionStateMap.get(action);
//                if (getName().equals("thief")) {
//                    return "The thief has just stolen your object!";
//                }
//                return "Ok.";
//            } else {
//                if (! stringProperty.equals("")) {
//                    return stringProperty;
//                }
//                return "Hey! I'm a " + this.getName() + ", I can't do that!";
//            }
//        }
//
//        public String getPossibleActions() {
//
//            StringBuilder possibleActions = new StringBuilder();
//            possibleActions.append("You can ");
//
//            int counter = 0;
//            int actionsSize = actionStateMap.size();
//            for (Map.Entry<String, String> entry : actionStateMap.entrySet()) {
//                possibleActions.append(entry.getKey());
//                if ( counter != actionsSize - 1) {
//                    possibleActions.append( "/");
//                }
//                counter++;
//            }
//
//            possibleActions.append(" the ").append(name).append(".");
//
//            return possibleActions.toString();
//        }
//
//        public List<String> getActionsList() {
//            List<String> actionsList = new ArrayList<String>();
//            for (Map.Entry<String, String> entry : actionStateMap.entrySet()) {
//                actionsList.add(entry.getValue());
//            }
//            return actionsList;
//        }


}
