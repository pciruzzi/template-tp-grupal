package ar.fiuba.tdd.tp.interpreter;


public class NotExpression implements IInterpreter {

    IInterpreter interpreter;

    public NotExpression(IInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    public boolean interpret() {
        return !(interpreter.interpret());
    }
}
