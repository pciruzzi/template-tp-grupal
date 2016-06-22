package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Player;

public abstract class LogicalConector implements IInterpreter {

    protected IInterpreter expressionOne;
    protected IInterpreter expressionTwo;
    protected String failMessage;

    public LogicalConector(IInterpreter expressionOne, IInterpreter expressionTwo) {
        this.expressionOne = expressionOne;
        this.expressionTwo = expressionTwo;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public boolean interpret(boolean bool, Player player) {
        if (expressionOne.interpret(player) && expressionTwo.interpret(player)) {
            return true;
        } else if (expressionTwo.interpret(player)) {
            this.failMessage = expressionOne.getFailMessage();
            return bool;
        } else if (expressionOne.interpret(player)) {
            this.failMessage = expressionTwo.getFailMessage();
            return bool;
        }
        return false;
    }
}
