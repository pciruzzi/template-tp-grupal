package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.ArrayList;

public class DoesNotContainElements extends TerminalExpression {

    public DoesNotContainElements(Element element, ArrayList<String> elementsNames) {
        super(element, elementsNames);
    }

    public boolean interpret() {
        // Para los casos en que sea con el player generico, ya que el hasAllElements siempre devolveria false,
        // y negado haria que este metodo devuelva true siempre, lo cual es incorrecto.
        if (element.getClass().equals(Player.class)) {
            return false;
        }
        return !element.hasAllElements(elementsListNames);
    }

    public boolean interpret(Player player) {
        return !player.hasAllElements(elementsListNames);
    }
}
