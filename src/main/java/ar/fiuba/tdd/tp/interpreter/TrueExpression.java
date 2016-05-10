package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.ElementTwo;

import java.util.ArrayList;


public class TrueExpression extends TerminalExpression{
    public TrueExpression() {
        super(null, null);
    }

    public boolean interpret() {
        return true;
    }
}
