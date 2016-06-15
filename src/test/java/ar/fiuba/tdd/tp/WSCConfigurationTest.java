package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.WSCConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WSCConfigurationTest {

    private static final String take = "take";
    private static final String leave = "leave";
    private static final String sheep = "sheep";
    private static final String wolf = "wolf";
    private static final String cabbage = "cabbage";
    private static final String cross = "cross";
    private static final String north = "north-shore";
    private static final String south = "south-shore";
    private static final String crossSuccesfull = "You have crossed";
    private static final int id = 0;

    private Game initializeGame() {
        WSCConfiguration gameConfiguration = new WSCConfiguration();
        return gameConfiguration.build();
    }

    @Test
    public void takeSheepOnBoatTest() {
        Game game = this.initializeGame();
        game.createPlayer();
        assertEquals(game.play(id, take,sheep),"You picked the sheep");
    }

    @Test
    public void takeSheepAndLeaveOnTheOtherShoreTest() {
        Game game = this.initializeGame();
        game.createPlayer();
        game.play(id, take,sheep);
        game.play(id, cross,north);
        game.play(id, leave,sheep);
        assertEquals(game.play(id, cross,south),crossSuccesfull);
    }

    @Test
    public void whenMoveSheepToOtherShoreTest() {
        Game game = this.initializeGame();
        game.createPlayer();
        game.play(id, take,sheep);
        assertEquals(game.play(id, cross,north),"You have crossed");
    }

    @Test
    public void canTakeWolfButCantCrossToShoreTest() {
        Game game = this.initializeGame();
        game.createPlayer();
        game.play(id, take,wolf);
        assertEquals(game.play(id, cross,north),"The sheep will eat the cabbage!");
    }

    @Test
    public void canTakeCabbageButCantCrossToShoreTest() {
        Game game = this.initializeGame();
        game.createPlayer();
        game.play(id, take,cabbage);
        assertEquals(game.play(id, cross,north),"The wolf will eat the sheep!");
    }

    @Test
    public void canTakeWolfAndLeaveItInTheSameShoreTest() {
        Game game = this.initializeGame();
        game.createPlayer();
        game.play(id, take,wolf);
        assertEquals(game.play(id, leave, wolf),"You dropped the wolf");
    }

//    @Test
//    public void cantLeaveShoreWithEmptyBeatTest() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        assertEquals(game.play(id, cross,north),"You can't do that! They'll eat other!");
//    }

//    @Test
//    public void withTheRightMovementsYouWinTheGameTest() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, take,sheep);
//        game.play(id, cross,north);
//        game.play(id, leave,sheep);
//        game.play(id, cross,south);
//        game.play(id, take,wolf);
//        game.play(id, cross,north);
//        game.play(id, leave,wolf);
//        game.play(id, take,sheep);
//        game.play(id, cross,south);
//        game.play(id, take,sheep);
//        game.play(id, take,cabbage);
//        game.play(id, cross,north);
//        game.play(id, leave,cabbage);
//        game.play(id, cross,south);
//        game.play(id, take,sheep);
//        game.play(id, cross,north);
//        assertEquals(game.play(id, leave,sheep),"You dropped the sheep");
//    }

    @Test
    public void cantLeaveNorthShoreWithSheepAndCabbageThere() {
        Game game = this.initializeGame();
        game.createPlayer();
        game.play(id, take,sheep);
        game.play(id, cross,north);
        game.play(id, leave,sheep);
        game.play(id, cross,south);
        game.play(id, take,cabbage);
        game.play(id, cross,north);
        game.play(id, leave,cabbage);
        assertEquals(game.play(id, cross,south),"The sheep will eat the cabbage!");
    }

    @Test
    public void cantLeaveNorthShoreWithSheepAndWolfThere() {
        Game game = this.initializeGame();
        game.createPlayer();
        game.play(id, take,sheep);
        game.play(id, cross,north);
        game.play(id, leave,sheep);
        game.play(id, cross,south);
        game.play(id, take,wolf);
        game.play(id, cross,north);
        game.play(id, leave,wolf);
        assertEquals(game.play(id, cross,south),"The wolf will eat the sheep!");
    }

    @Test
    public void cantPickWolfFromNorthShore() {
        Game game = this.initializeGame();
        game.createPlayer();
        game.play(id, take,sheep);
        game.play(id, cross,north);
        assertEquals(game.play(id, take, wolf),"It doesn't exist a wolf in the game WSC");
    }

//    @Test
//    public void whenBoatIsFullCantTakeOtherThingTest() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, take,sheep);
//        assertEquals(game.play(id, take,wolf),"You can't do that, the boat is full");
//    }

    @Test
    public void cantMoveToSouthShoreFromSouthShoreTest() {
        Game game = this.initializeGame();
        game.createPlayer();
        assertEquals(game.play(id, cross,south),"It doesn't exist a south-shore in the game WSC");
    }
}
