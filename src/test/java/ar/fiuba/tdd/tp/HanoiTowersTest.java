package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.*;
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

    private Engine engine;

    @Before
    public void setUp() {
        engine = new Engine();
        GameBuilder gameBuilder = new HanoiConfiguration();
        engine.createGame(gameBuilder);
    }

    @Test
    public void moveFromStackOneTooTwo() {
        assertEquals(engine.doCommand(moveOneToTwo),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void moveFromStackOneTooThree() {
        assertEquals(engine.doCommand(moveOneToThree),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void moveFromStackOneToStackTwoTwoTimes() {
        engine.doCommand(moveOneToTwo);
        assertEquals(engine.doCommand(moveTwoToOne),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void moveFromStackOneToTwoAndBackToStackOne() {
        engine.doCommand(moveOneToTwo);
        assertEquals(engine.doCommand(moveTwoToOne),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void moveTwoDiskToStackThreeInAValidMove() {
        engine.doCommand(moveOneToTwo);
        engine.doCommand(moveOneToThree);
        assertEquals(engine.doCommand(moveTwoToThree),HANOI_MOVESUCCESFULL);
    }

    @Test
    public void cantMoveBiggerDiskOnTopOfSmallerDisk() {
        engine.doCommand(moveOneToTwo);
        engine.doCommand(moveOneToThree);
        assertEquals(engine.doCommand(moveThreeToTwo),HANOI_MOVEERROR);
    }

    @Test
    public void theGameIsWinnableInStackThree() {
        engine.doCommand(moveOneToThree);
        engine.doCommand(moveOneToTwo);
        engine.doCommand(moveThreeToTwo);
        engine.doCommand(moveOneToThree);
        engine.doCommand(moveTwoToOne);
        engine.doCommand(moveTwoToThree);
        assertEquals(engine.doCommand(moveOneToThree),GAME_WON);
    }

    @Test
    public void theGameIsWinnableInStackTwo() {
        engine.doCommand(moveOneToTwo);
        engine.doCommand(moveOneToThree);
        engine.doCommand(moveTwoToThree);
        engine.doCommand(moveOneToTwo);
        engine.doCommand(moveThreeToOne);
        engine.doCommand(moveThreeToTwo);
        assertEquals(engine.doCommand(moveOneToTwo),GAME_WON);
    }

    @Test
    public void checkAvailableActions() {
        assertEquals(engine.doCommand("ask stackOne"), HANOI_QUESTION + " the stackOne.");
    }

    @Test
    public void checkSizeOfStack() {
        assertEquals(engine.doCommand("check top stackOne"), HANOI_CHECKSIZE + "1.");
    }

    @Test
    public void cantCheckTopEmptyStack() {
        assertEquals(engine.doCommand("check top stackTwo"),HANOI_SIZE);
    }

    @Test
    public void cantMoveTopEmptyStack() {
        assertEquals(engine.doCommand(moveTwoToOne), HANOI_MOVEERRORSTACKEMPTY);
    }

}
