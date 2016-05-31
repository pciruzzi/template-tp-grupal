package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class OpenDoor2Test extends InitializationsForTests{

    private static final int id = 0;

//    @Test
//    public void theKeyShouldNotAppearTwoTimesIfIOpenTheBoxTwice() {
//        Engine engine = initializeEngineOpenDoor2();
//        engine.doCommand(id, "open box");
//        engine.doCommand(id, "pick key");
//        engine.doCommand(id, "open box");
//        assertFalse(engine.doCommand(id, "look around").contains("key"));
//    }
//
//    @Test
//    public void lookAroundTest() {
//        Engine engine = initializeEngineOpenDoor2();
//        String output = engine.doCommand(id, "look around");
//
//        assertTrue(output.contains("door"));
//        assertTrue(output.contains("box"));
//    }
//
//    @Test
//    public void openLockedDoorShowsError() {
//        Engine engine = initializeEngineOpenDoor2();
//        assertEquals("Ey! You can't do that! The door is locked", engine.doCommand(id, "open door"));
//    }
//
//    @Test
//    public void openBoxIsAValidMove() {
//        Engine engine = initializeEngineOpenDoor2();
//        assertEquals("The box is opened.", engine.doCommand(id, "open box"));
//    }
//
//    @Test
//    public void openBoxAndLookAroundShowsKey() {
//        Engine engine = initializeEngineOpenDoor2();
//        engine.doCommand(id, "open box");
//        String output = engine.doCommand(id, "look around");
//        assertTrue(output.contains("box"));
//        assertTrue(output.contains("key"));
//        assertTrue(output.contains("door"));
//    }
//
//    @Test
//    public void pickKeyFromOpenBox() {
//        Engine engine = initializeEngineOpenDoor2();
//        engine.doCommand(id, "open box");
//        String output = engine.doCommand(id, "pick key");
//        assertEquals("You picked the key", output);
//    }
//
//    @Test
//    public void openDoorWithKeyWinsGame() {
//        Engine engine = initializeEngineOpenDoor2();
//        engine.doCommand(id, "open box");
//        engine.doCommand(id, "pick key");
//
//        assertEquals("You won!!!", engine.doCommand(id, "open door"));
//    }
}
