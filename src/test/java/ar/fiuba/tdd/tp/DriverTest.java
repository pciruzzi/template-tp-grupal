package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DriverTest {

    @Test
    public void gameDriverTest() throws GameLoadFailedException {
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


}
