package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Player;

public class AndExpression extends LogicalConector {

    public AndExpression(IInterpreter expressionOne, IInterpreter expressionTwo) {
        super(expressionOne, expressionTwo);
    }

    public boolean interpret() {
        return super.interpret(false);
    }

    public boolean interpret(Player player) {
        return super.interpret(false, player);
    }

    public String getFailMessage() {
        return this.failMessage;
    }
}
