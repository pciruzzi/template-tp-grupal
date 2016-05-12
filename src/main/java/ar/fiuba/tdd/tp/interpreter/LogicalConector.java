package ar.fiuba.tdd.tp.interpreter;

public abstract class LogicalConector implements IInterpreter {

    protected IInterpreter expressionOne;
    protected IInterpreter expressionTwo;

    public LogicalConector(IInterpreter expressionOne, IInterpreter expressionTwo) {
        this.expressionOne = expressionOne;
        this.expressionTwo = expressionTwo;
    }
}
