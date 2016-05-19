package ar.fiuba.tdd.tp.interpreter;

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

    public boolean interpret(boolean bool) {
        if (expressionOne.interpret() && expressionTwo.interpret()) {
            return true;
        } else if (expressionTwo.interpret()) {
            this.failMessage = expressionOne.getFailMessage();
            return bool;
        } else if (expressionOne.interpret()) {
            this.failMessage = expressionTwo.getFailMessage();
            return bool;
        }
        return false;
    }
}
