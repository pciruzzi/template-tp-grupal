package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.EvilThingConfiguration;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.OpenDoor2Configuration;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.server.queue.EventQueue;

public class InitializationsForTests {

    static Engine initializeEngineEvilThing() {
        BroadcastQueue queue = new EventQueue();
        Engine engine = new Engine(queue);
        GameBuilder gameBuilder = new EvilThingConfiguration();
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer();
        return engine;
    }

    static Engine initializeEngineOpenDoor2() {
        BroadcastQueue queue = new EventQueue();
        Engine engine = new Engine(queue);
        GameBuilder gameBuilder = new OpenDoor2Configuration();
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer();
        return engine;
    }
}