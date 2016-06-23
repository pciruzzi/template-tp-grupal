package ar.fiuba.tdd.tp.interpreter.logic;

import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

public class OrExpression extends LogicalConector {

    public OrExpression(IInterpreter expressionOne, IInterpreter expressionTwo) {
        super(expressionOne, expressionTwo);
    }

    public boolean interpret(Player player) {
        return super.interpret(true, player);
    }

    public String getFailMessage() {
        return this.failMessage;
    }
}
