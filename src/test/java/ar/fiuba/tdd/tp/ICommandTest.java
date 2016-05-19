package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.PoisonConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ICommandTest {

    private Engine initializeEnginePoisonConfiguration() {
        Engine engine = new Engine();
        GameBuilder gameBuilder = new PoisonConfiguration();
        engine.createGame(gameBuilder);
        return engine;
    }

    @Test
    public void openSomethingAndPlayerGetPoisoned() {
        Engine engine = initializeEnginePoisonConfiguration();
        engine.doCommand("open chest");
        assertTrue(engine.getGame().getPlayer().isPoisoned());
    }

    @Test
    public void pickSomethingAndPlayerGetPoisoned() {
        Engine engine = initializeEnginePoisonConfiguration();
        engine.doCommand("pick stick");
        assertTrue(engine.getGame().getPlayer().isPoisoned());
    }

    @Test
    public void pickAntidoteAndGetHealed() {
        Engine engine = initializeEnginePoisonConfiguration();
        engine.doCommand("pick stick");
        assertTrue(engine.getGame().getPlayer().isPoisoned());
        engine.doCommand("pick antidote");
        assertFalse(engine.getGame().getPlayer().isPoisoned());
    }

}
