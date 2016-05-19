package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.server.BuilderLoader;

public class DriverImplementation implements GameDriver {

    private Engine engine;
    private GameState status;

    @Override
    public void initGame(String jarPath) throws GameLoadFailedException {
        engine = new Engine();
        try {
            engine.createGame(BuilderLoader.load(jarPath));
            status = GameState.Ready;
        } catch (Exception e) {
            throw new GameLoadFailedException();
        }
    }

    @Override
    public String sendCommand(String cmd) {
        status = GameState.InProgress;
        String returnMessage = engine.doCommand(cmd);
        if (engine.isGameWon()) {
            status = GameState.Won;
        } else if (engine.isGameLost()) {
            status = GameState.Lost;
        }
        return returnMessage;
    }

    @Override
    public GameState getCurrentState() {
        return status;
    }
}
