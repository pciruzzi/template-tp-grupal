package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.Engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class OpenDoorTest extends InitializationsForTests {

    private static final int id = 0;

    @Test
    public void theKeyShouldNotAppearIfIPickIt() {
        Engine engine = initializeEngineOpenDoor();
        engine.doCommand(id, "pick key");
        assertFalse(engine.doCommand(id, "look around").contains("key"));
    }

    @Test
    public void theKeyShouldAppearIfIPickItAndDropIt() {
        Engine engine = initializeEngineOpenDoor();
        engine.doCommand(id, "pick key");
        engine.doCommand(id, "drop key");
        assertTrue(engine.doCommand(id, "look around").contains("key"));
    }

    @Test
    public void lookAroundTest() {
        Engine engine = initializeEngineOpenDoor();
        String output = engine.doCommand(id, "look around");

        assertTrue(output.contains("door"));
        assertTrue(output.contains("key"));
    }

    @Test
    public void openLockedDoorShowsError() {
        Engine engine = initializeEngineOpenDoor();
        assertEquals("Ey! You can't do that! The door is locked", engine.doCommand(id, "open door"));
    }

    @Test
    public void youShouldWinTheGameIfYouPickTheKeyAndOpenTheDoor() {
        Engine engine = initializeEngineOpenDoor();
        engine.doCommand(id, "pick key");
        assertEquals("You won!!!", engine.doCommand(id, "open door"));
    }
}
