package ar.fiuba.tdd.tp.interpreter;

public class OrExpression extends LogicalConector {

    public OrExpression(IInterpreter expressionOne, IInterpreter expressionTwo) {
        super(expressionOne, expressionTwo);
    }

    public boolean interpret() {
        return (expressionOne.interpret() || expressionTwo.interpret());
    }
}
