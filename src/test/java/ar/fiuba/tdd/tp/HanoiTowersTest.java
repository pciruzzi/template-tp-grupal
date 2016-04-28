package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.games.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gg on 4/28/2016.
 */
public class HanoiTowersTest {

    public static final String moveOneToTwo = "move top stack1 stack2";
    public static final String moveOneToThree = "move top stack1 stack3";
    public static final String moveTwoToOne = "move top stack2 stack1";
    public static final String moveTwoToThree = "move top stack2 stack3";
    public static final String moveThreeToOne = "move top stack3 stack1";
    public static final String moveThreeToTwo = "move top stack3 stack2";
    public static final String moveSuccesfull = "Ok.";
    public static final String moveFailed = "You can't stack a bigger disk over smaller one.";

    private Game initializeGame() {
        Game game = new HanoiTowers();
        game.createGame();
        return game;
    }

    @Test
    public void moveFromStackOneTooTwo() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(moveOneToTwo),moveSuccesfull);
    }

    @Test
    public void moveFromStackOneTooThree() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(moveOneToThree),moveSuccesfull);
    }

//    @Test
//    public void moveFromStackTwoWithoutDiskError() {
//        Game game = this.initializeGame();
//        assertEquals(game.doAction(moveTwoToOne),moveFailed);
//    }

    @Test
    public void moveFromStackOneToStackTwoTwoTimesReturnError() {
        Game game = this.initializeGame();
        game.doAction(moveOneToTwo);
        assertEquals(game.doAction(moveTwoToOne),moveSuccesfull);
    }

    @Test
    public void moveFromStackOneToTwoAndBackToStackOne() {
        Game game = this.initializeGame();
        game.doAction(moveOneToTwo);
        assertEquals(game.doAction(moveTwoToOne),moveSuccesfull);
    }

    @Test
    public void moveTwoDiskToStackThreeInAValidMove() {
        Game game = this.initializeGame();
        game.doAction(moveOneToTwo);
        game.doAction(moveOneToThree);
        assertEquals(game.doAction(moveTwoToThree),moveSuccesfull);
    }

    @Test
    public void cantMoveBiggerDiskOnTopOfSmallerDisk() {
        Game game = this.initializeGame();
        game.doAction(moveOneToTwo);
        game.doAction(moveOneToThree);
        assertEquals(game.doAction(moveThreeToTwo),moveFailed);
    }

    @Test
    public void theGameIsWinnableInStackThree() {
        Game game = this.initializeGame();
        game.doAction(moveOneToThree);
        game.doAction(moveOneToTwo);
        game.doAction(moveThreeToTwo);
        game.doAction(moveOneToThree);
        game.doAction(moveTwoToOne);
        game.doAction(moveTwoToThree);
        assertEquals(game.doAction(moveOneToThree),"You won the game!");
    }

    @Test
    public void theGameIsWinnableInStackTwo() {
        Game game = this.initializeGame();
        game.doAction(moveOneToTwo);
        game.doAction(moveOneToThree);
        game.doAction(moveTwoToThree);
        game.doAction(moveOneToTwo);
        game.doAction(moveThreeToOne);
        game.doAction(moveThreeToTwo);
        assertEquals(game.doAction(moveOneToTwo),"You won the game!");
    }


}
