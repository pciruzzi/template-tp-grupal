package ar.fiuba.tdd.tp.interpreter.terminalexpressions;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContainsPlayer extends TerminalExpression {

    public ContainsPlayer(Optional<Element> element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
    }

    public boolean interpret(Player player) {
        Element elemento = element.isPresent() ? element.get() : player;
        List<String> newList = new ArrayList<>();
        for (String element : elementsListNames) {
            String newElement = element;
            if (element.contains("player")) {
                newElement = "player " + player.getPlayerID();
            }
            newList.add(newElement);
        }
        return elemento.hasAllElements(newList);
    }
}
