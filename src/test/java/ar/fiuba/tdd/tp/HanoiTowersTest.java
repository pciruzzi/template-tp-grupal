package ar.fiuba.tdd.tp;

import org.junit.Before;
import org.junit.Test;

import static ar.fiuba.tdd.tp.Constants.*;
import static org.junit.Assert.assertEquals;

public class HanoiTowersTest {

    public static final String moveOneToTwo = "move top stack1 stack2";
    public static final String moveOneToThree = "move top stack1 stack3";
    public static final String moveTwoToOne = "move top stack2 stack1";
    public static final String moveTwoToThree = "move top stack2 stack3";
    public static final String moveThreeToOne = "move top stack3 stack1";
    public static final String moveThreeToTwo = "move top stack3 stack2";
    public static final String moveSuccesfull = "Ok.";
    public static final String moveFailed = HANOI_MOVEERROR;
    public static final String gameWon = "You won the game!";

//    private Game game;
//
//    @Before
//    public void setUp() {
//        game = new HanoiTowers();
//        game.createGame();
//    }
//
//    @Test
//    public void moveFromStackOneTooTwo() {
//        assertEquals(game.doAction(moveOneToTwo),moveSuccesfull);
//    }
//
//    @Test
//    public void moveFromStackOneTooThree() {
//        assertEquals(game.doAction(moveOneToThree),moveSuccesfull);
//    }
//
//    @Test
//    public void moveFromStackOneToStackTwoTwoTimes() {
//        game.doAction(moveOneToTwo);
//        assertEquals(game.doAction(moveTwoToOne),moveSuccesfull);
//    }
//
//    @Test
//    public void moveFromStackOneToTwoAndBackToStackOne() {
//        game.doAction(moveOneToTwo);
//        assertEquals(game.doAction(moveTwoToOne),moveSuccesfull);
//    }
//
//    @Test
//    public void moveTwoDiskToStackThreeInAValidMove() {
//        game.doAction(moveOneToTwo);
//        game.doAction(moveOneToThree);
//        assertEquals(game.doAction(moveTwoToThree),moveSuccesfull);
//    }
//
//    @Test
//    public void cantMoveBiggerDiskOnTopOfSmallerDisk() {
//        game.doAction(moveOneToTwo);
//        game.doAction(moveOneToThree);
//        assertEquals(game.doAction(moveThreeToTwo),moveFailed);
//    }
//
//    @Test
//    public void theGameIsWinnableInStackThree() {
//        game.doAction(moveOneToThree);
//        game.doAction(moveOneToTwo);
//        game.doAction(moveThreeToTwo);
//        game.doAction(moveOneToThree);
//        game.doAction(moveTwoToOne);
//        game.doAction(moveTwoToThree);
//        assertEquals(game.doAction(moveOneToThree),gameWon);
//    }
//
//    @Test
//    public void theGameIsWinnableInStackTwo() {
//        game.doAction(moveOneToTwo);
//        game.doAction(moveOneToThree);
//        game.doAction(moveTwoToThree);
//        game.doAction(moveOneToTwo);
//        game.doAction(moveThreeToOne);
//        game.doAction(moveThreeToTwo);
//        assertEquals(game.doAction(moveOneToTwo),gameWon);
//    }
//
//    @Test
//    public void cantCheckTopEmptyStack() {
//        assertEquals(game.doAction(moveTwoToOne),HANOI_SIZE);
//    }
//
//    @Test
//    public void cantCheckTopInvalidStack() {
//        assertEquals(game.doAction("check top hola"), HANOI_STACK_ERROR);
//    }
//
//    @Test
//    public void cantMoveTopEmptyStack() {
//        assertEquals(game.doAction(moveTwoToOne),HANOI_SIZE);
//    }
//
//    @Test
//    public void cantMoveTopInvalidStack() {
//        assertEquals(game.doAction("move top stack1 hola"), HANOI_STACK_ERROR);
//    }
}
