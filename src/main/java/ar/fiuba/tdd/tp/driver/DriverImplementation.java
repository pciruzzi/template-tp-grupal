package ar.fiuba.tdd.tp.driver;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.server.BuilderLoader;

import java.io.IOException;

public class DriverImplementation implements GameDriver {

    private Engine engine;
    private GameState status;
    private int playerCount;

    @Override
    public void initGame(String jarPath) throws GameLoadFailedException {
        engine = new Engine();
        playerCount = 0;
        try {
            engine.createGame(BuilderLoader.load(jarPath));
            engine.createPlayer(playerCount);
            playerCount++;
            status = GameState.Ready;

            // Tengo que poner esto asi porque si no salta el findbugs del build de gradle,
            // antes funcionaba solo con el
            // catch (Exception e) -> throw new GameLoadFailedException();
            // Encontre la solucion en: https://sourceforge.net/p/findbugs/bugs/1124/
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new GameLoadFailedException();
        }
    }

    @Override
    public int joinPlayer() throws PlayerJoinFailedException {
        int result = engine.createPlayer(playerCount);
        if (result > 0) {
            return result;
        }
        throw new PlayerJoinFailedException("You couldn't initialize the new player");
    }

    @Override
    public String sendCommand(String cmd) {
        String error = "Estas usando el send command viejo de DriverImplementation.";
        System.out.println(error);
        return error;
//        status = GameState.InProgress;
//        int id = Character.getNumericValue(cmd.charAt(0));
//        cmd = cmd.substring(1);
//        String returnMessage = "";
//        if (cmd.equals("newPlayer")) {
//            returnMessage = engine.createPlayer(id);
//        } else {
//            returnMessage = engine.doCommand(id,cmd);
//        }
//        if (engine.isGameWon()) {
//            status = GameState.Won;
//        } else if (engine.isGameLost()) {
//            status = GameState.Lost;
//        }
//        return returnMessage;
    }

    @Override
    public String sendCommand(String cmd, int player) throws UnknownPlayerException {
        status = GameState.InProgress;
        String returnMessage = engine.doCommand(player,cmd);
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
