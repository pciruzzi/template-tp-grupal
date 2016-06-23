package ar.fiuba.tdd.tp.interpreter.logic;

import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

public class NotExpression implements IInterpreter {

    private IInterpreter interpreter;
    private String failMessage;

    public NotExpression(IInterpreter interpreter) {
        this.interpreter = interpreter;
        this.failMessage = null;
    }

    public boolean interpret(Player player) {
        return !(interpreter.interpret(player));
    }

    public String getFailMessage() {
        if (failMessage == null) {
            return this.interpreter.getFailMessage();
        }
        return this.failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }
}
