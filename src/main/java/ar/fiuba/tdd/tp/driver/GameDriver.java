package ar.fiuba.tdd.tp.driver;

public interface GameDriver {
    void initGame(String jarPath) throws GameLoadFailedException;

    int joinPlayer() throws PlayerJoinFailedException;

    /** Sends command to default player.
     * @deprecated use {@link #joinPlayer()} and {@link #sendCommand(String cmd, int player)} instead
     */
    @Deprecated
    String sendCommand(String cmd);

    String sendCommand(String cmd, int player) throws UnknownPlayerException;
    
    GameState getCurrentState();
}
