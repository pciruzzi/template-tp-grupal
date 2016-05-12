package ar.fiuba.tdd.tp;

public interface GameDriver {
    void initGame(String jarPath);

    String sendCommand(String cmd);
}
