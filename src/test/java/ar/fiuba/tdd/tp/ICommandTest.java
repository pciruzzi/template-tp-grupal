package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ICommandTest extends InitializationsForTests{

    private static final int id = 0;

//    @Test
//    public void changeVisibilityTest() {
//        //If I open the box and do look around, now pick key must be visible.
//        Engine engine = initializeEngineOpenDoor2();
//        assertFalse(engine.doCommand(id, "look around").contains("key"));
//        engine.doCommand(id, "open box");
//        assertTrue(engine.doCommand(id, "look around").contains("key"));
//    }
//
//    @Test
//    public void theKeyMovesFromRoomToPlayerInventory() {
//        Engine engine = initializeEngineOpenDoor2();
//        engine.doCommand(id, "open box");
//        engine.doCommand(id, "pick key");
//        assertTrue(engine.getGame().getPlayer().getElementMap().containsKey("key"));
//        assertFalse(engine.getGame().getCurrentPositionElements(id).containsKey("key"));
//    }
//
//    @Test
//    public void pickKeyAndThenDropTheKey() {
//        Engine engine = initializeEngineOpenDoor2();
//        engine.doCommand(id, "open box");
//        engine.doCommand(id, "pick key");
//        assertTrue(engine.getGame().getPlayer().getElementMap().containsKey("key"));
//        engine.doCommand(id, "drop key");
//        assertFalse(engine.getGame().getPlayer().getElementMap().containsKey("key"));
//        assertTrue(engine.getGame().getCurrentPositionElements(id).containsKey("key"));
//    }
//
//    @Test
//    public void openSomethingAndPlayerGetPoisoned() {
//        Engine engine = initializeEnginePoisonConfiguration();
//        engine.doCommand(id, "open chest");
//        assertTrue(engine.getGame().getPlayer().isPoisoned());
//    }
//
//    @Test
//    public void pickSomethingAndPlayerGetPoisoned() {
//        Engine engine = initializeEnginePoisonConfiguration();
//        engine.doCommand(id, "pick stick");
//        assertTrue(engine.getGame().getPlayer().isPoisoned());
//    }
//
//    @Test
//    public void pickAntidoteAndGetHealed() {
//        Engine engine = initializeEnginePoisonConfiguration();
//        engine.doCommand(id, "pick stick");
//        assertTrue(engine.getGame().getPlayer().isPoisoned());
//        engine.doCommand(id, "pick antidote");
//        assertFalse(engine.getGame().getPlayer().isPoisoned());
//    }

}
