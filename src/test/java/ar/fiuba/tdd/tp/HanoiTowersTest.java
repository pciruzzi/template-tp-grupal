package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.*;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.server.queue.EventQueue;
import org.junit.Before;
import org.junit.Test;

import static ar.fiuba.tdd.tp.Constants.*;

import static org.junit.Assert.*;

public class HanoiTowersTest {

    public static final String moveOneToTwo = "move top stackOne stackTwo";
    public static final String moveOneToThree = "move top stackOne stackThree";
    public static final String moveTwoToOne = "move top stackTwo stackOne";
    public static final String moveTwoToThree = "move top stackTwo stackThree";
    public static final String moveThreeToOne = "move top stackThree stackOne";
    public static final String moveThreeToTwo = "move top stackThree stackTwo";

    public static final String HANOI_MOVESUCCESFULL = "You moved the disk!";
    public static final String HANOI_QUESTION = "You can check top/move top";
    public static final String HANOI_CHECKSIZE = "The size of the top is ";
    public static final String HANOI_MOVEERRORSTACKEMPTY = "The stack from where you are trying to move is empty";
    public static final String HANOI_SIZE = "The stack you are trying to check is empty.";
    public static final String HANOI_MOVEERROR = "You can't move a bigger disk over a smaller one!";

    private static final int id = 0;

    private Engine engine;

    @Before
    public void setUp() {
        BroadcastQueue queue = new EventQueue();
        engine = new Engine(queue);
        GameBuilder gameBuilder = new HanoiConfiguration();
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer(0);
    }

    @Test
    public void moveFromStackOneTooTwo() {
        assertEquals(engine.doCommand(id, moveOneToTwo),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void moveFromStackOneTooThree() {
        assertEquals(engine.doCommand(id, moveOneToThree),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void moveFromStackOneToStackTwoTwoTimes() {
        engine.doCommand(id, moveOneToTwo);
        assertEquals(engine.doCommand(id, moveTwoToOne),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void moveFromStackOneToTwoAndBackToStackOne() {
        engine.doCommand(id, moveOneToTwo);
        assertEquals(engine.doCommand(id, moveTwoToOne),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void moveTwoDiskToStackThreeInAValidMove() {
        engine.doCommand(id, moveOneToTwo);
        engine.doCommand(id, moveOneToThree);
        assertEquals(engine.doCommand(id, moveTwoToThree),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void cantMoveBiggerDiskOnTopOfSmallerDisk() {
        engine.doCommand(id, moveOneToTwo);
        engine.doCommand(id, moveOneToThree);
        assertEquals(engine.doCommand(id, moveThreeToTwo),HANOI_MOVEERROR);
    }

    @Test
    public void theGameIsWinnableInStackThree() {
        engine.doCommand(id, moveOneToThree);
        engine.doCommand(id, moveOneToTwo);
        engine.doCommand(id, moveThreeToTwo);
        engine.doCommand(id, moveOneToThree);
        engine.doCommand(id, moveTwoToOne);
        engine.doCommand(id, moveTwoToThree);
        assertEquals(engine.doCommand(id, moveOneToThree),GAME_WON);
    }

    @Test
    public void theGameIsWinnableInStackTwo() {
        engine.doCommand(id, moveOneToTwo);
        engine.doCommand(id, moveOneToThree);
        engine.doCommand(id, moveTwoToThree);
        engine.doCommand(id, moveOneToTwo);
        engine.doCommand(id, moveThreeToOne);
        engine.doCommand(id, moveThreeToTwo);
        assertEquals(engine.doCommand(id, moveOneToTwo),GAME_WON);
    }

    @Test
    public void checkAvailableActions() {
        assertEquals(engine.doCommand(id, "ask stackOne"), HANOI_QUESTION + " the stackOne.");
    }

    @Test
    public void checkSizeOfStack() {
        assertEquals(engine.doCommand(id, "check top stackOne"), HANOI_CHECKSIZE + "1.");
    }

    @Test
    public void cantCheckTopEmptyStack() {
        assertEquals(engine.doCommand(id, "check top stackTwo"),HANOI_SIZE);
    }

    @Test
    public void cantMoveTopEmptyStack() {
        assertEquals(engine.doCommand(id, moveTwoToOne), HANOI_MOVEERRORSTACKEMPTY);
    }

}
