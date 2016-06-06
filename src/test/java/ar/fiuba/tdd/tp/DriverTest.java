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
        driver.sendCommand("0ask stick");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("0pick stick");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinOpenDoorDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/OpenDoorConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("0pick key");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("0open door");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinOpenDoor2DriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/OpenDoor2Configuration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("0open box");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("0pick key");
        driver.sendCommand("0open door");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinEvilThingDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/EvilThingConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("0pick key");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("0open door");
        driver.sendCommand("0talk to thief");
        driver.sendCommand("0open otherDoor");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinHanoiDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/HanoiConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("0move top stackOne stackThree");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("0move top stackOne stackTwo");
        driver.sendCommand("0move top stackThree stackTwo");
        driver.sendCommand("0move top stackOne stackThree");
        driver.sendCommand("0move top stackTwo stackOne");
        driver.sendCommand("0move top stackTwo stackThree");
        driver.sendCommand("0move top stackOne stackThree");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldWinTreasureQuestDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/TreasureQuestConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("0pick pokemon");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("0open door");
        driver.sendCommand("0open door to three");
        driver.sendCommand("0drop pokemon");
        driver.sendCommand("0pick key");
        driver.sendCommand("0open door to four");
        driver.sendCommand("0open wardrobe");
        driver.sendCommand("0open chest");
        driver.sendCommand("0open door to five");
        driver.sendCommand("0open box two");
        driver.sendCommand("0pick treasure");
        driver.sendCommand("0open door to one");
        assertEquals(GameState.Won, driver.getCurrentState());
    }

    @Test
    public void shouldLoseTreasureQuestDriverTest() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/TreasureQuestConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        driver.sendCommand("0pick pokemon");
        assertEquals(GameState.InProgress, driver.getCurrentState());
        driver.sendCommand("0open door");
        driver.sendCommand("0open door to three");
        driver.sendCommand("0drop pokemon");
        driver.sendCommand("0pick key");
        driver.sendCommand("0open door to four");
        driver.sendCommand("0open wardrobe");
        driver.sendCommand("0open chest");
        driver.sendCommand("0open door to five");
        driver.sendCommand("0open box one");
        driver.sendCommand("0open door to four");
        assertEquals(GameState.Lost, driver.getCurrentState());
    }
}
