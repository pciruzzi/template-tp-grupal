package ar.fiuba.tdd.tp.interpreter;

import ar.fiuba.tdd.tp.engine.Player;

public interface IInterpreter {
    boolean interpret(Player player);

    String getFailMessage();

    void setFailMessage(String failMessage);

}
