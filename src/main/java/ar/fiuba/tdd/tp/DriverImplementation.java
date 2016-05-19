package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.server.BuilderLoader;

public class DriverImplementation implements GameDriver {

    private Engine engine;

    @Override
    public void initGame(String jarPath) {
        engine = new Engine();
        try {
            engine.createGame(BuilderLoader.load(jarPath));
        } catch (Exception e) {
            Writer writer = new Console();
            writer.writeError("Couldn't load game from file " + jarPath);
            throw new RuntimeException("EXCEPTION: Couldn't load game from file " + jarPath);
        }
    }

    @Override
    public String sendCommand(String cmd) {
        return engine.doCommand(cmd);
    }
}
