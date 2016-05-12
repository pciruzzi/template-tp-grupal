package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;

import java.util.List;

public class Question extends ICommand {

    public Question(String name) {
        this.name = name;
    }

    public String doAction(ElementTwo element) {
        StringBuilder returnMessage = new StringBuilder();
        returnMessage.append("You can ");

        List<ICommand> commandList = element.getCommandList();
        int commandListSize = commandList.size();

        for (int i = 0; i < commandListSize; i++) {
            ICommand actualCommand = commandList.get(i);
            returnMessage.append(actualCommand.getName());
            if (i != commandListSize - 1) {
                returnMessage.append( "/");
            }
        }
        returnMessage.append(" the ").append(element.getName()).append(".");

        return returnMessage.toString();
    }
}