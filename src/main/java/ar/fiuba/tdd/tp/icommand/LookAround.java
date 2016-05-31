package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.model.Game;

import java.util.List;

public class LookAround extends ICommand {
    private Game game;

    public LookAround(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public String doAction(Element element, int playerId) {
        List<Element> elementList = game.getVisibleElementList();
        int elementsListSize = elementList.size();

        if (elementsListSize == 0) {
            return "The room is empty.";
        }

        StringBuilder returnMessage = new StringBuilder();
        returnMessage.append("There's a ");

        for (int i = 0; i < elementsListSize; i++) {
            Element actualElement = elementList.get(i);
            returnMessage.append(actualElement.getName());
            appendMessage(elementsListSize, returnMessage, i);
        }
        returnMessage.append(" in the room.");

        return returnMessage.toString();
    }

    private void appendMessage(int elementsListSize, StringBuilder returnMessage, int index) {
        if (index == elementsListSize - 2) {
            returnMessage.append( " and a ");
        } else if (index != elementsListSize - 1) {
            returnMessage.append( ", a ");
        }
    }
}
