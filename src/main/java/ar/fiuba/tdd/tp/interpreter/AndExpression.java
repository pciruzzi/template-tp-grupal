package ar.fiuba.tdd.tp.interpreter;

public class AndExpression extends LogicalConector {

    public AndExpression(IInterpreter expressionOne, IInterpreter expressionTwo) {
        super(expressionOne, expressionTwo);
    }

    public boolean interpret() {
        return (expressionOne.interpret() && expressionTwo.interpret());
    }
}
