package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.Optional;

public class HasValueState extends TerminalExpression {

    private boolean value;
    private String state;

    public HasValueState(Optional<Element> element, String state, boolean value) {
        super(element, null);
        this.value = value;
        this.state = state;
    }

    public boolean interpret(Player player) {
        if (element.isPresent()) {
            return element.get().getValueOfState(state) == value;
        } else {
            return player.getValueOfState(state) == value;
        }
    }
}
