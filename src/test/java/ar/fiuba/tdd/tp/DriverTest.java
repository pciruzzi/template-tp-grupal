package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.*;

import ar.fiuba.tdd.tp.server.queue.EventQueue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DriverTest {

    private GameDriver createDriver(String path) throws GameLoadFailedException, PlayerJoinFailedException {
        EventQueue queue = new EventQueue();
        GameDriver driver = new DriverImplementation(queue);
        driver.initGame(path);
        driver.joinPlayer();
        return driver;
    }


    @Test
    public void testGameLoadIsReady() throws GameLoadFailedException, PlayerJoinFailedException {
        GameDriver driver = this.createDriver("build/classes/main/ar/fiuba/tdd/tp/model/DummyGameConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
    }


    @Test
    public void testWhenPickSwordGameIsInProgress() throws GameLoadFailedException, PlayerJoinFailedException {
        GameDriver driver = this.createDriver("build/classes/main/ar/fiuba/tdd/tp/model/DummyGameConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        try {
            driver.sendCommand("pick sword", 0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
    }

    @Test
    public void testWhenPickTheGoldenStickGameIsWon() throws GameLoadFailedException, PlayerJoinFailedException {
        GameDriver driver = this.createDriver("build/classes/main/ar/fiuba/tdd/tp/model/DummyGameConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        try {
            driver.sendCommand("open chest", 0);
            driver.sendCommand("pick key", 0);
            driver.sendCommand("open door", 0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
            driver.sendCommand("pick goldenStick", 0);
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void testWhenPickTheWoodenStickGameIsLost() throws GameLoadFailedException, PlayerJoinFailedException {
        GameDriver driver = this.createDriver("build/classes/main/ar/fiuba/tdd/tp/model/DummyGameConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        try {
            driver.sendCommand("open chest", 0);
            driver.sendCommand("pick key", 0);
            driver.sendCommand("open door", 0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
            driver.sendCommand("pick woodenStick", 0);
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
        assertEquals(GameState.Lost, driver.getCurrentState());
    }
}
