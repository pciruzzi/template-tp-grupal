package ar.fiuba.tdd.tp.driver;

public interface GameDriver {
    void initGame(String jarPath) throws GameLoadFailedException;

    String sendCommand(String cmd);

    GameState getCurrentState();
}
