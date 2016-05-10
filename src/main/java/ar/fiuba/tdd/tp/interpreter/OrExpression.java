package ar.fiuba.tdd.tp.interpreter;

/**
 * Created by gg on 5/9/2016.
 */
public class OrExpression extends LogicalConector {

    public OrExpression(IInterpreter expressionOne, IInterpreter expressionTwo) {
        super(expressionOne, expressionTwo);
    }

    public boolean interpret() {
        return (expressionOne.interpret() || expressionTwo.interpret());
    }
}
