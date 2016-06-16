package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.model.Game;

import java.util.List;

public class ElementsInSameContainer extends TerminalExpression {

    private Element elementTwo;
    private Game game;

    public ElementsInSameContainer(Element elementOne, Element elementTwo, Game game) {
        super(elementOne, null);
        this.elementTwo = elementTwo;
        this.game = game;
    }

    @Override
    public boolean interpret() {
        List<Element> containerList = game.getContainersList();

        for ( Element container : containerList ) {
            if ( container.hasElement(element.getName()) && container.hasElement(elementTwo.getName()) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean interpret(Player player) {
        boolean sameContainer = false;
        List<Element> containerList = game.getContainersList();
        String elementName = element.getName();
        if (elementName.contains("player")) {
            elementName = "player " + player.getPlayerID();
            System.out.println("Concatenando el id al element en ElementsInSameContainer: " + elementName);
        }
        String elementTwoName = elementTwo.getName();
        if (elementTwoName.contains("player")) {
            elementTwoName = "player " + player.getPlayerID();
            System.out.println("Concatenando el id al elementTwo en ElementsInSameContainer: " + elementTwoName);
        }

        for ( Element container : containerList ) {
            if ( container.hasElement(elementName) && container.hasElement(elementTwoName) ) {
                sameContainer = true;
            }
        }
        return sameContainer;
    }
}
