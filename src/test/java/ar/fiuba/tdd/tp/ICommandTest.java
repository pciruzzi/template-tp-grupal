package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.DummyGameConfiguration;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.server.queue.EventQueue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ICommandTest {

    private static final int id = 0;
    private Engine engine;

    @Before
    public void setUp() {
        BroadcastQueue queue = new EventQueue();
        engine = new Engine(queue);
        GameBuilder gameBuilder = new DummyGameConfiguration();
        engine.createGame(gameBuilder);
        engine.createPlayer();
    }

    @Test
    public void testChangeVisibilityTest() {
        //If I open the box and do look around, now pick key must be visible.
        assertFalse(engine.doCommand(id,"look around").contains("key"));
        engine.doCommand(id,"open chest");
        assertTrue(engine.doCommand(id,"look around").contains("key"));
    }

    @Test
    public void testMoveToPlayerKeyMovesFromRoomToPlayerInventory() {
        engine.doCommand(id,"open chest");
        engine.doCommand(id,"pick key");
        assertTrue(engine.getGame().getPlayer(id).getElementMap().containsKey("key"));
        assertFalse(engine.getGame().getPlayerPosition(id).getElementMap().containsKey("key"));
    }

    @Test
    public void testPickKeyAndThenDropTheKey() {
        engine.doCommand(id,"open chest");
        engine.doCommand(id,"pick key");
        assertTrue(engine.getGame().getPlayer(id).getElementMap().containsKey("key"));
        engine.doCommand(id,"drop key");
        assertFalse(engine.getGame().getPlayer(id).getElementMap().containsKey("key"));
        assertTrue(engine.getGame().getPlayerPosition(id).getElementMap().containsKey("key"));
    }

    @Test
    public void testDropOnPositionPickKeyAndThenDropTheKey() {
        engine.doCommand(id,"open chest");
        engine.doCommand(id,"pick key");
        assertTrue(engine.getGame().getPlayer(id).getElementMap().containsKey("key"));
        engine.doCommand(id,"drop key");
        assertFalse(engine.getGame().getPlayer(id).getElementMap().containsKey("key"));
        assertTrue(engine.getGame().getPlayerPosition(id).getElementMap().containsKey("key"));
    }

    @Test
    public void testMovePlayerToOpenDoorWithoutKeyDoesNotMovesPlayer() {
        engine.doCommand(id,"open door");
        assertEquals(engine.getGame().getPlayerPosition(id).getName(), "room");
    }

    @Test
    public void testMovePlayerToOpenDoorWithKeyMovesPlayer() {
        engine.doCommand(id,"open chest");
        engine.doCommand(id,"pick key");
        engine.doCommand(id,"open door");
        assertEquals(engine.getGame().getPlayerPosition(id).getName(), "finalRoom");
    }

    @Test
    public void testQuestionAskChestWhatCanDo() {
        assertEquals(engine.doCommand(id,"ask chest"), "You can open the chest.");
    }

    @Test
    public void testMoveToPlayerPickGoldenStickAndWin() {
        engine.doCommand(id,"open chest");
        engine.doCommand(id,"pick key");
        engine.doCommand(id,"open door");
        assertEquals(engine.doCommand(id,"pick goldenStick"),"You won!!!");
    }

    @Test
    public void testMoveToPlayerPickWoodenStickAndLose() {
        engine.doCommand(id,"open chest");
        engine.doCommand(id,"pick key");
        engine.doCommand(id,"open door");
        assertEquals(engine.doCommand(id,"pick woodenStick"),"You lost!!!");
    }
}