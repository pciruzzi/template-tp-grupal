package ar.fiuba.tdd.tp.interpreter.terminalexpressions;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.model.Game;

import java.util.List;
import java.util.Optional;

public class ElementsInSameContainer extends TerminalExpression {

    private Optional<Element> elementTwo;
    private Game game;

    public ElementsInSameContainer(Optional<Element> elementOne, Optional<Element> elementTwo, Game game) {
        super(elementOne, null);
        this.elementTwo = elementTwo;
        this.game = game;
    }

    @Override
    public boolean interpret(Player player) {
        Element elementoUno = getElement(element, player);
        Element elementoDos = getElement(elementTwo, player);
        boolean sameContainer = false;
        List<Element> containerList = game.getContainersList();
        String elementName = getElementName(elementoUno, player);
        String elementTwoName = getElementName(elementoDos, player);

        for ( Element container : containerList ) {
            if ( container.hasElement(elementName) && container.hasElement(elementTwoName) ) {
                sameContainer = true;
            }
        }
        return sameContainer;
    }

    private Element getElement(Optional<Element> element, Player player) {
        return element.isPresent() ? element.get() : player;
    }

}
