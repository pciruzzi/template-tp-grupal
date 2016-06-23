package ar.fiuba.tdd.tp.interpreter.logic;

import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.TerminalExpression;

public class TrueExpression extends TerminalExpression {

    public TrueExpression() {
        super(null, null);
    }

    public boolean interpret(Player player) {
        return true;
    }
}
