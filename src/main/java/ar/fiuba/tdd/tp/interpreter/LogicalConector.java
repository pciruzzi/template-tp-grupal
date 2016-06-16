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

    public boolean interpret(boolean bool, Player player) {
        return interpretOptionOne(bool, player) || interpretOptionTwo(bool, player) || interpretOptionThree(bool, player);
    }

    // Puede ser que el player este solo en una de las dos expresiones, o en las dos,
    // Pruebo en la primera
    private boolean interpretOptionOne(boolean bool, Player player) {
        if (expressionOne.interpret(player) && expressionTwo.interpret()) {
            return true;
        } else if (expressionTwo.interpret()) {
            this.failMessage = expressionOne.getFailMessage();
            return bool;
        } else if (expressionOne.interpret(player)) {
            this.failMessage = expressionTwo.getFailMessage();
            return bool;
        }
        return false;
    }

    // Pruebo en la segunda
    private boolean interpretOptionTwo(boolean bool, Player player) {
        if (expressionOne.interpret() && expressionTwo.interpret(player)) {
            return true;
        } else if (expressionTwo.interpret(player)) {
            this.failMessage = expressionOne.getFailMessage();
            return bool;
        } else if (expressionOne.interpret()) {
            this.failMessage = expressionTwo.getFailMessage();
            return bool;
        }
        return false;
    }

    // Pruebo en la tercera
    private boolean interpretOptionThree(boolean bool, Player player) {
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
