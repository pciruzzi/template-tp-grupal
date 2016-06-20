package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.*;
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

    static Engine initializeEngineOpenDoor() {
        BroadcastQueue queue = new EventQueue();
        Engine engine = new Engine(queue);
        GameBuilder gameBuilder = new OpenDoorConfiguration();
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer();
        return engine;
    }

    static Engine initializeEngineFetchQuest() {
        BroadcastQueue queue = new EventQueue();
        Engine engine = new Engine(queue);
        GameBuilder gameBuilder = new FetchQuestConfiguration();
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer();
        return engine;
    }

    static Engine initializeEngineTempleQuest() {
        BroadcastQueue queue = new EventQueue();
        Engine engine = new Engine(queue);
        GameBuilder gameBuilder = new TempleQuestConfiguration();
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer();
        return engine;
    }
}