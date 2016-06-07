package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Player;

public class TrueExpression extends TerminalExpression{

    public TrueExpression() {
        super(null, null);
    }

    public boolean interpret() {
        return true;
    }

    public boolean interpret(Player player) {
        return true;
    }
}
