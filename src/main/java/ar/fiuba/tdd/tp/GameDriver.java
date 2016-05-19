package ar.fiuba.tdd.tp;

public interface GameDriver {
    void initGame(String jarPath) throws GameLoadFailedException;

    String sendCommand(String cmd);

    GameState getCurrentState();
}
