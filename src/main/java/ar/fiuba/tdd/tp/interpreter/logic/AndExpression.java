package ar.fiuba.tdd.tp.interpreter.logic;

import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

public class AndExpression extends LogicalConector {

    public AndExpression(IInterpreter expressionOne, IInterpreter expressionTwo) {
        super(expressionOne, expressionTwo);
    }

    public boolean interpret(Player player) {
        return super.interpret(false, player);
    }

    public String getFailMessage() {
        return this.failMessage;
    }
}
