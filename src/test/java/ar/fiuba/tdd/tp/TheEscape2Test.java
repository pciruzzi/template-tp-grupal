package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.*;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.HanoiConfiguration;
import ar.fiuba.tdd.tp.model.TheEscape22Configuration;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.server.queue.EventQueue;
import ar.fiuba.tdd.tp.time.MockedTimedAction;
import ar.fiuba.tdd.tp.time.ScheduledTimedAction;
import ar.fiuba.tdd.tp.time.TimeCommand;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static org.junit.Assert.assertEquals;

public class TheEscape2Test {

    private Engine engine;
    private MockedTimedAction enojar;
    private MockedTimedAction despertar;
    private MockedTimedAction mover;

    @Test
    public void shouldLostWhenMeetsAngryBibliotecario() throws GameLoadFailedException {
        setUp();
        engine.doCommand(0, "goto Salon1");
        engine.doCommand(0, "pick Botella");
        engine.doCommand(0, "goto Pasillo");
        engine.doCommand(0, "goto BibliotecaAcceso");
        engine.doCommand(0, "give Licor Bibliotecario");
        engine.doCommand(0, "goto doorBiblioteca");
        despertar.startMockedAction();
        enojar.startMockedAction();
        assertEquals(GAME_LOST, engine.doCommand(0, "goto BibliotecaAcceso"));
    }

    @Test
    public void shouldLostWhenBibliotecarioFoundsPlayer1InBiblioteca() throws GameLoadFailedException {
        setUp();
        engine.getGame().createPlayer(1);


        engine.doCommand(1, "goto BibliotecaAcceso");
        engine.doCommand(1, "goto doorBiblioteca");



        engine.doCommand(0, "goto Salon1");
        engine.doCommand(0, "pick Botella");
        engine.doCommand(0, "goto Pasillo");
        engine.doCommand(0, "goto BibliotecaAcceso");
        engine.doCommand(0, "give Licor Bibliotecario");
        engine.doCommand(0, "goto doorBiblioteca");

        engine.doCommand(1, "goto BibliotecaAcceso");
        engine.doCommand(1, "goto Pasillo");

        despertar.startMockedAction();
        enojar.startMockedAction();
//        mover.startMockedAction();

        assertEquals(GAME_LOST, mover.startMockedAction());
//        assertEquals("You have Crossed", engine.doCommand(1, "goto Salon1"));
    }

    private void setUp() throws GameLoadFailedException {


        enojar      = new MockedTimedAction(1200000, "enojar Bibliotecario");
        despertar   = new MockedTimedAction(1200000, "despertar Bibliotecario");
        mover       = new MockedTimedAction(1200000, "move Bibliotecario");

        GameBuilder gameBuilder = new TheEscape22Configuration(despertar, enojar, mover);
        BroadcastQueue queue = new EventQueue();

        engine = new Engine(queue);
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer(0);
    }
}
