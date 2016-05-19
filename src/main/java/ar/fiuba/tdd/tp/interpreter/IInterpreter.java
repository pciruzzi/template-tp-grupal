package ar.fiuba.tdd.tp.interpreter;

public interface IInterpreter {
    public boolean interpret();

    public String getFailMessage();

    public void setFailMessage(String failMessage);
}
