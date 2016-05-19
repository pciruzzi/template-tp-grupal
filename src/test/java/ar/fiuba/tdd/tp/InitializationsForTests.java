package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.EvilThingConfiguration;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.OpenDoor2Configuration;
import ar.fiuba.tdd.tp.model.PoisonConfiguration;

public class InitializationsForTests {

    static Engine initializeEngineEvilThing() {
        Engine engine = new Engine();
        GameBuilder gameBuilder = new EvilThingConfiguration();
        engine.createGame(gameBuilder);
        return engine;
    }

    static Engine initializeEnginePoisonConfiguration() {
        Engine engine = new Engine();
        GameBuilder gameBuilder = new PoisonConfiguration();
        engine.createGame(gameBuilder);
        return engine;
    }


    static Engine initializeEngineOpenDoor2() {
        Engine engine = new Engine();
        GameBuilder gameBuilder = new OpenDoor2Configuration();
        engine.createGame(gameBuilder);

        return engine;
    }
}