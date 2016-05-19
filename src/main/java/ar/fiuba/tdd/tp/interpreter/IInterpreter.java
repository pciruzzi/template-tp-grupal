package ar.fiuba.tdd.tp.interpreter;

public interface IInterpreter {
    boolean interpret();

    String getFailMessage();

    void setFailMessage(String failMessage);
}
