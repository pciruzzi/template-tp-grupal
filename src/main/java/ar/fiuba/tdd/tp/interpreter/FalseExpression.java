package ar.fiuba.tdd.tp.interpreter;

public class FalseExpression extends TerminalExpression {

    public FalseExpression() {
        super(null, null);
    }

    public boolean interpret() {
        return false;
    }
}
