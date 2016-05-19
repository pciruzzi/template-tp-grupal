package ar.fiuba.tdd.tp.interpreter;


public class NotExpression implements IInterpreter {

    private IInterpreter interpreter;
    private String failMessage;

    public NotExpression(IInterpreter interpreter) {
        this.interpreter = interpreter;
        this.failMessage = null;
    }

    public boolean interpret() {
        return !(interpreter.interpret());
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
