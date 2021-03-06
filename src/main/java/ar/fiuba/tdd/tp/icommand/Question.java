package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

import java.util.List;

public class Question extends ICommand {

    public Question(String name) {
        this.name = name;
    }

    public String doAction(Element element, int playerId) {
        StringBuilder returnMessage = new StringBuilder();
        returnMessage.append("You can ");

        List<ICommand> commandList = element.getCommandList();
        int commandListSize = commandList.size();
        for (int i = 0; i < commandListSize; i++) {
            ICommand actualCommand = commandList.get(i);
            // Esto es para que no se agregue la pregunta
            if ( !actualCommand.getName().equals(this.getName()) ) {
                returnMessage.append(actualCommand.getName());
                if (i != commandListSize - 1) {
                    returnMessage.append( "/");
                }
            }
        }
        returnMessage = checkReturnMessage(returnMessage);
        returnMessage.append(" the ").append(element.getName()).append(".");
        return returnMessage.toString();
    }

    private StringBuilder checkReturnMessage(StringBuilder returnMessage) {
        int indexOfSlash = returnMessage.lastIndexOf("/");
        if (indexOfSlash == (returnMessage.length() - 1)) {
            returnMessage = returnMessage.deleteCharAt(indexOfSlash);
        }
        return returnMessage;
    }
}
