package ar.fiuba.tdd.tp.interpreter;

/**
 * Created by gg on 5/9/2016.
 */
public class OrExpression implements IInterpreter {
    private TerminalElement expressionOne;
    private TerminalElement expressionTwo;

    public OrExpression(TerminalElement expressionOne, TerminalElement expressionTwo) {
        this.expressionOne = expressionOne;
        this.expressionTwo = expressionTwo;
    }

    public boolean interpret() {
        return expressionOne.interpret() || expressionTwo.interpret();
    }
}
