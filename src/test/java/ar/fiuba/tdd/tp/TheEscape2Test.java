//package ar.fiuba.tdd.tp;
//
//import ar.fiuba.tdd.tp.driver.*;
//import ar.fiuba.tdd.tp.engine.Engine;
//import ar.fiuba.tdd.tp.icommand.ITimeCommand;
//import ar.fiuba.tdd.tp.icommand.Move;
//import ar.fiuba.tdd.tp.icommand.MovePlayerTo;
//import ar.fiuba.tdd.tp.icommand.MoveRandom;
//import ar.fiuba.tdd.tp.model.GameBuilder;
//import ar.fiuba.tdd.tp.model.HanoiConfiguration;
//import ar.fiuba.tdd.tp.model.TheEscape22Configuration;
//import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
//import ar.fiuba.tdd.tp.server.queue.EventQueue;
//import ar.fiuba.tdd.tp.time.MockedTimedAction;
//import ar.fiuba.tdd.tp.time.ScheduledTimedAction;
//import ar.fiuba.tdd.tp.time.TimeCommand;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class TheEscape2Test {
//
//    private Engine engine;
//    private MockedTimedAction enojar;
//    private MockedTimedAction despertar;
//    private MockedTimedAction mover;
//    private MoveRandom moveRandom;
//
//    @Test
//    public void shouldLostWhenMeetsAngryBibliotecario() throws GameLoadFailedException {
//        GameDriver driver = getGameDriver();
//        try {
//            driver.sendCommand("goto Salon1", 0);
//            driver.sendCommand("pick Botella", 0);
//            driver.sendCommand("goto Pasillo", 0);
//            driver.sendCommand("goto BibliotecaAcceso", 0);
//            driver.sendCommand("give Licor Bibliotecario", 0);
//            driver.sendCommand("goto doorBiblioteca", 0);
//            despertar.startMockedAction();
//            enojar.startMockedAction();
//            mover.startMockedAction();
//            driver.sendCommand("goto BibliotecaAcceso", 0);
//        } catch (UnknownPlayerException e) {
//            e.printStackTrace();
//        }
//        assertEquals(GameState.Lost, driver.getCurrentState());
//    }
//
//    @Test
//    public void shouldLostWhenBibliotecarioFoundsPlayer1InBiblioteca() throws GameLoadFailedException {
//        GameDriver driver = getGameDriver();
//        try {
//            driver.sendCommand("goto BibliotecaAcceso", 1);
//            driver.sendCommand("goto doorBiblioteca", 1);
//            driver.sendCommand("goto BibliotecaAcceso", 1);
//            driver.sendCommand("goto Pasillo", 1);
//
//
//            driver.sendCommand("goto Salon1", 0);
//            driver.sendCommand("pick Botella", 0);
//            driver.sendCommand("goto Pasillo", 0);
//            driver.sendCommand("goto BibliotecaAcceso", 0);
//            driver.sendCommand("give Licor Bibliotecario", 0);
//            driver.sendCommand("goto doorBiblioteca", 0);
//            driver.sendCommand("goto BibliotecaAcceso", 0);
//        } catch (UnknownPlayerException e) {
//            e.printStackTrace();
//        }
//        assertEquals(GameState.Lost, driver.getCurrentState());
//        assertEquals(GameState.InProgress, driver.getCurrentState());
//    }
//
//    private GameDriver getGameDriver() throws GameLoadFailedException {
//
//
//        enojar      = new MockedTimedAction(1200000, "enojar Bibliotecario");
//        despertar   = new MockedTimedAction(1200000, "despertar Bibliotecario");
//        mover       = new MockedTimedAction(1200000, "movePasillo Bibliotecario");
//
//        moveRandom = new MoveRandom("movePasillo");
//        moveRandom.auxiliarMessage("El Bibliotecario se desperto y esta enfurecido!!!\n");
//
//        GameBuilder gameBuilder = new TheEscape22Configuration(despertar, enojar, mover, moveRandom);
//        BroadcastQueue queue = new EventQueue();
//
//        engine = new Engine(queue);
//        engine.createGame(gameBuilder);
//        engine.getGame().createPlayer(0);
//
//        GameDriver driver = new DriverImplementation(queue);
//        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/TheEscape22Configuration.jar");
//        assertEquals(GameState.Ready, driver.getCurrentState());
//        return driver;
//    }
//}
