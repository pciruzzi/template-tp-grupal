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
        try {
            driver.sendCommand("ask stick", 0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
            driver.sendCommand("pick stick", 0);
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinOpenDoorDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/OpenDoorConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        try {
            driver.sendCommand("pick key", 0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
            driver.sendCommand("open door", 0);
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinOpenDoor2DriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/OpenDoor2Configuration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        try {
            driver.sendCommand("open box", 0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
            driver.sendCommand("pick key", 0);
            driver.sendCommand("open door", 0);
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinEvilThingDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/EvilThingConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        try {
            driver.sendCommand("pick key", 0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
            driver.sendCommand("open door", 0);
            driver.sendCommand("talk to thief", 0);
            driver.sendCommand("open otherDoor", 0);
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinHanoiDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/HanoiConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        try {
            driver.sendCommand("move top stackOne stackThree", 0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
            driver.sendCommand("move top stackOne stackTwo", 0);
            driver.sendCommand("move top stackThree stackTwo", 0);
            driver.sendCommand("move top stackOne stackThree", 0);
            driver.sendCommand("move top stackTwo stackOne", 0);
            driver.sendCommand("move top stackTwo stackThree", 0);
            driver.sendCommand("move top stackOne stackThree", 0);
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinTreasureQuestDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/TreasureQuestConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        try {
            driver.sendCommand("pick pokemon",0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
            driver.sendCommand("open door",0);
            driver.sendCommand("open door to three",0);
            driver.sendCommand("drop pokemon",0);
            driver.sendCommand("pick key",0);
            driver.sendCommand("open door to four",0);
            driver.sendCommand("open wardrobe",0);
            driver.sendCommand("open chest",0);
            driver.sendCommand("open door to five",0);
            driver.sendCommand("open box two",0);
            driver.sendCommand("pick treasure",0);
            driver.sendCommand("open door to one",0);
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldLoseTreasureQuestDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/TreasureQuestConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        try {
            driver.sendCommand("pick pokemon",0);
            assertEquals(GameState.InProgress, driver.getCurrentState());
            driver.sendCommand("open door",0);
            driver.sendCommand("open door to three",0);
            driver.sendCommand("drop pokemon",0);
            driver.sendCommand("pick key",0);
            driver.sendCommand("open door to four",0);
            driver.sendCommand("open wardrobe",0);
            driver.sendCommand("open chest",0);
            driver.sendCommand("open door to five",0);
            driver.sendCommand("open box one",0);
            driver.sendCommand("open door to four",0);
        } catch (UnknownPlayerException e) {
            System.out.print(e.getMsg());
        }
        assertEquals(GameState.Lost, driver.getCurrentState());
    }
}
