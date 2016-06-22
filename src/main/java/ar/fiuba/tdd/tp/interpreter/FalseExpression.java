package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Player;

public class FalseExpression extends TerminalExpression {

    public FalseExpression() {
        super(null, null);
    }

    public boolean interpret(Player player) {
        return false;
    }
}
