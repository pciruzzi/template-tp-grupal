package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.ArrayList;
import java.util.List;

public class ContainsPlayer extends TerminalExpression {

    public ContainsPlayer(Element element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
    }

    public boolean interpret() {
        return element.hasAllElements(elementsListNames);
    }

    public boolean interpret(Player player) {
        List<String> newList = new ArrayList<>();
        for (String element : elementsListNames) {
            String newElement = element;
            if (element.contains("player")) {
                newElement = "player " + player.getPlayerID();
            }
            newList.add(newElement);
        }
        return element.hasAllElements(newList);
    }
}
