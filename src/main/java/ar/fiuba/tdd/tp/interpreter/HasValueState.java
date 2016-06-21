package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

import java.util.List;

public class HasValueState extends TerminalExpression {

    private boolean value;
    private String state;

    public HasValueState(Element element, String state, boolean value) {
        super(element, null);
        this.value = value;
        this.state = state;
    }

    @Override
    public boolean interpret() {
        return element.getValueOfState(state) == value;
    }

    public boolean interpret(Player player) {

        return player.getValueOfState(state) == value;
    }
}
