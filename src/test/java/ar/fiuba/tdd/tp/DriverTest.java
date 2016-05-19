package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DriverTest {

    @Test
    public void shouldWinFetchQuestDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/FetchQuestConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("ask stick");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("pick stick");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinOpenDoorDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/OpenDoorConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("pick key");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("open door");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinOpenDoor2DriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/OpenDoor2Configuration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("open box");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("pick key");
        driver.sendCommand("open door");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinEvilThingDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/EvilThingConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("pick key");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("open door");
        driver.sendCommand("talk to thief");
        driver.sendCommand("open otherDoor");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinHanoiDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/HanoiConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("move top stackOne stackThree");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("move top stackOne stackTwo");
        driver.sendCommand("move top stackThree stackTwo");
        driver.sendCommand("move top stackOne stackThree");
        driver.sendCommand("move top stackTwo stackOne");
        driver.sendCommand("move top stackTwo stackThree");
        driver.sendCommand("move top stackOne stackThree");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

}
