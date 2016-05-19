package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EvilThingTest extends ICommandTest{

    @Test
    public void lookAroundTest() {
        Engine engine = initializeEngineEvilThing();
        assertTrue(engine.doCommand("look around").contains("key"));
        assertTrue(engine.doCommand("look around").contains("door"));
    }

    @Test
    public void crossRoomTest() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand("pick key");
        assertEquals("You have crossed", engine.doCommand("open door"));
    }

    @Test
    public void roomTwoHasAthief() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand("pick key");
        engine.doCommand("open door");
        String output = engine.doCommand("look around");
        assertTrue(output.contains("thief"));
        assertTrue(output.contains("door"));
    }

    @Test
    public void youCantEnterRoom3WithRoom() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand("pick key");
        engine.doCommand("open door");
        assertEquals("Ey! You can't do that! The otherDoor is locked", engine.doCommand("open otherDoor"));
    }

    @Test
    public void youAreAbleToSpeakToTheThief() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand("pick key");
        engine.doCommand("open door");
        assertEquals("Hi!\nThe thief has just stolen your key", engine.doCommand("talk to thief"));
    }

    @Test
    public void ifTheThiefStealsYouYouCanOpenDoor2AndWin() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand("pick key");
        engine.doCommand("open door");
        engine.doCommand("talk to thief");
        assertEquals("You won!!!", engine.doCommand("open otherDoor"));
    }
}
