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
        boolean sameContainer = false;
        List<Element> containerList = game.getContainersList();

        for ( Element container : containerList ) {
            if ( container.hasElement(element.getName()) && container.hasElement(elementTwo.getName()) ) {
                sameContainer = true;
            }
        }
        return sameContainer;
    }

    @Override
    public boolean interpret(Player player) {
        return false;
    }
}
