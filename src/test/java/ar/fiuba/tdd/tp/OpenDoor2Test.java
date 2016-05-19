package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.OpenDoor2Configuration;
import org.junit.Test;

import static ar.fiuba.tdd.tp.ConstantVariables.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class OpenDoor2Test extends InitializationsForTests{

    @Test
    public void theKeyShouldNotAppearTwoTimesIfIOpenTheBoxTwice() {
        Engine engine = initializeEngineOpenDoor2();
        engine.doCommand("open box");
        engine.doCommand("pick key");
        engine.doCommand("open box");
        assertFalse(engine.doCommand("look around").contains("key"));
    }

    @Test
    public void lookAroundTest() {
        Engine engine = initializeEngineOpenDoor2();
        String output = engine.doCommand("look around");

        assertTrue(output.contains("door"));
        assertTrue(output.contains("box"));
    }

    @Test
    public void openLockedDoorShowsError() {
        Engine engine = initializeEngineOpenDoor2();
        assertEquals(OPEN_DOOR_ERROR, engine.doCommand("open door"));
    }

    @Test
    public void openBoxIsAValidMove() {
        Engine engine = initializeEngineOpenDoor2();
        assertEquals("The box is opened.", engine.doCommand("open box"));
    }

    @Test
    public void openBoxAndLookAroundShowsKey() {
        Engine engine = initializeEngineOpenDoor2();
        engine.doCommand("open box");
        String output = engine.doCommand("look around");
        assertTrue(output.contains("box"));
        assertTrue(output.contains("key"));
        assertTrue(output.contains("door"));
    }
    
    @Test
    public void pickKeyFromOpenBox() {
        Engine engine = initializeEngineOpenDoor2();
        engine.doCommand("open box");
        String output = engine.doCommand("pick key");
        assertEquals("You picked the key", output);
    }

    @Test
    public void openDoorWithKeyWinsGame() {
        Engine engine = initializeEngineOpenDoor2();
        engine.doCommand("open box");
        engine.doCommand("pick key");

        assertEquals("You won!!!", engine.doCommand("open door"));
    }
}
