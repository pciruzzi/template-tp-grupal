package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Engine;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EvilThingTest extends InitializationsForTests {

    private static final int id = 0;

    @Test
    public void lookAroundTest() {
        Engine engine = initializeEngineEvilThing();
        assertTrue(engine.doCommand(id, "look around").contains("key"));
        assertTrue(engine.doCommand(id, "look around").contains("door"));
    }

    @Test
    public void crossRoomTest() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand(id, "pick key");
        assertEquals("You have crossed", engine.doCommand(id, "open door"));
    }

    @Test
    public void roomTwoHasAthief() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand(id, "pick key");
        engine.doCommand(id, "open door");
        String output = engine.doCommand(id, "look around");
        assertTrue(output.contains("thief"));
        assertTrue(output.contains("door"));
    }

    @Test
    public void youCantEnterRoom3WithRoom() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand(id, "pick key");
        engine.doCommand(id, "open door");
        assertEquals("Ey! You can't do that! The otherDoor is locked", engine.doCommand(id, "open otherDoor"));
    }

    @Test
    public void youAreAbleToSpeakToTheThief() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand(id, "pick key");
        engine.doCommand(id, "open door");
        assertEquals("Hi!\nThe thief has just stolen your key", engine.doCommand(id, "talk to thief"));
    }

    @Test
    public void ifTheThiefStealsYouYouCanOpenDoor2AndWin() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand(id, "pick key");
        engine.doCommand(id, "open door");
        engine.doCommand(id, "talk to thief");
        assertEquals("You won!!!", engine.doCommand(id, "open otherDoor"));
    }
}
