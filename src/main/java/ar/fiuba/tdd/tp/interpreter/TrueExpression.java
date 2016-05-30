package ar.fiuba.tdd.tp.interpreter;

public class TrueExpression extends TerminalExpression{

    public TrueExpression() {
        super(null, null);
    }

    public boolean interpret() {
        return true;
    }
}
