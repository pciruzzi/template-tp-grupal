package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.server.BuilderLoader;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;

public class DriverImplementation implements GameDriver {

    private Engine engine;
    private GameState status;
    private BroadcastQueue queue;

    public DriverImplementation(BroadcastQueue queue) {
        this.queue = queue;
    }

    @Override
    public void initGame(String jarPath) throws GameLoadFailedException {
        engine = new Engine(queue);
        try {
            engine.createGame(BuilderLoader.load(jarPath));
            status = GameState.Ready;
        } catch (Exception e) {
            throw new GameLoadFailedException();
        }
    }

    @Override
    public int joinPlayer() throws PlayerJoinFailedException {
        int result = engine.createPlayer();
        if (result >= 0) {
            return result;
        }
        throw new PlayerJoinFailedException("A connection has been refused because the maximum players of the game has been reached");
    }

    @Override
    @Deprecated
    public String sendCommand(String cmd) {
        String error = "Estas usando el send command viejo de DriverImplementation.";
        System.out.println(error);
        return error;
    }

    @Override
    public String sendCommand(String cmd, int player) throws UnknownPlayerException {
        status = GameState.InProgress;
        String returnMessage = engine.doCommand(player,cmd);
        if (engine.isGameWon()) {
            status = GameState.Won;
        } else if (engine.isGameLost()) { //Sirve solo si es para un unico jugador!!!
            status = GameState.Lost;
        }
        return returnMessage;
    }

    @Override
    public GameState getCurrentState() {
        return status;
    }
}
