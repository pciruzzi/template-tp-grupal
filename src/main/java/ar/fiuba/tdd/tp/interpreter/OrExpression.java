package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Player;

public class OrExpression extends LogicalConector {

    public OrExpression(IInterpreter expressionOne, IInterpreter expressionTwo) {
        super(expressionOne, expressionTwo);
    }

    public boolean interpret() {
        return super.interpret(true);
    }

    public boolean interpret(Player player) {
        return super.interpret(true);
    }

    public String getFailMessage() {
        return this.failMessage;
    }
}
