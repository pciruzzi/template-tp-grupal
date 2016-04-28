package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.games.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WolfSheepAndCabbageTest {

    private static final String takeSheep = "take sheep";
    private static final String takeWolf = "take wolf";
    private static final String takeCabbage = "take col";
    private static final String leaveSheep = "leave sheep";
    private static final String leaveWolf = "leave wolf";
    private static final String leaveCabbage = "leave col";
    private static final String crossSuccesfull = "You have crossed!";
    private static final String crossNorth = "cross north-shore";
    private static final String crossSouth = "cross south-shore";
    private static final String invalidAction = "Invalid Action.";

    private Game initializeGame() {
        Game game = new WolfSheepAndCabbage();
        game.createGame();
        return game;
    }

    @Test
    public void takeSheepOnBoatTest() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(takeSheep),"You took the sheep.");
    }

    @Test
    public void whenBoatIsFullCantTakeOtherThingTest() {
        Game game = this.initializeGame();
        game.doAction(takeSheep);
        assertEquals(game.doAction(takeWolf),"You can't do that! You already have the sheep");
    }

    @Test
    public void whenMoveSheepToOtherShoreTest() {
        Game game = this.initializeGame();
        game.doAction(takeSheep);
        assertEquals(game.doAction(crossNorth),crossSuccesfull);
    }

    @Test
    public void canTakeWolfButCantCrossToShoreTest() {
        Game game = this.initializeGame();
        game.doAction(takeWolf);
        assertEquals(game.doAction(crossNorth),"You can't do that! The sheep will eat the col");
    }

    @Test
    public void canTakeWolfAndLeaveItInTheSameShoreTest() {
        Game game = this.initializeGame();
        game.doAction(takeWolf);
        assertEquals(game.doAction(leaveWolf),"You leave the wolf.");
    }

    @Test
    public void cantLeaveShoreWithEmptyBeatTest() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(crossNorth),"You can't do that! The wolf will eat the sheep");
    }

    @Test
    public void cantMoveToSouthShoreFromSouthShoreTest() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(crossSouth),invalidAction);
    }

    @Test
    public void withTheRightMovementsYouWinTheGameTest() {
        Game game = this.initializeGame();
        game.doAction(takeSheep);
        game.doAction(crossNorth);
        game.doAction(leaveSheep);
        game.doAction(crossSouth);
        game.doAction(takeWolf);
        game.doAction(crossNorth);
        game.doAction(leaveWolf);
        game.doAction(takeSheep);
        game.doAction(crossSouth);
        game.doAction(leaveSheep);
        game.doAction(takeCabbage);
        game.doAction(crossNorth);
        game.doAction(leaveCabbage);
        game.doAction(crossSouth);
        game.doAction(takeSheep);
        game.doAction(crossNorth);
        assertEquals(game.doAction(leaveSheep),"You won the game!");
    }
}
