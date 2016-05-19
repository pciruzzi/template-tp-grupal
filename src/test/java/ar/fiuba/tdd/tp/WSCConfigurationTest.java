package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;
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
    private static final String pickSuccesfull = "Ok.";
    private static final String crossSuccesfull = "You have crossed";
    private static final String error = "Error";

    private Game initializeGame() {
        WSCConfiguration gameConfiguration = new WSCConfiguration();
        Game game = gameConfiguration.build();
        return game;
    }

    @Test
    public void takeSheepOnBoatTest() {
        Game game = this.initializeGame();
        assertEquals(game.play(take,sheep),"You picked the sheep");
    }

    @Test
    public void takeSheepAndLeaveOnTheOtherShoreTest() {
        Game game = this.initializeGame();
        game.play(take,sheep);
        game.play(cross,north);
        game.play(leave,sheep);
        assertEquals(game.play(cross,south),crossSuccesfull);
    }

    @Test
    public void whenMoveSheepToOtherShoreTest() {
        Game game = this.initializeGame();
        game.play(take,sheep);
        assertEquals(game.play(cross,north),"You have crossed");
    }

    @Test
    public void canTakeWolfButCantCrossToShoreTest() {
        Game game = this.initializeGame();
        game.play(take,wolf);
        assertEquals(game.play(cross,north),"The sheep will eat the cabbage!");
    }

    @Test
    public void canTakeCabbageButCantCrossToShoreTest() {
        Game game = this.initializeGame();
        game.play(take,cabbage);
        assertEquals(game.play(cross,north),"The wolf will eat the sheep!");
    }

    @Test
    public void canTakeWolfAndLeaveItInTheSameShoreTest() {
        Game game = this.initializeGame();
        game.play(take,wolf);
        assertEquals(game.play(leave, wolf),"You dropped the wolf");
    }

    @Test
    public void cantLeaveShoreWithEmptyBeatTest() {
        Game game = this.initializeGame();
        assertEquals(game.play(cross,north),"You can't do that! They'll eat other!");
    }

    @Test
    public void withTheRightMovementsYouWinTheGameTest() {
        Game game = this.initializeGame();
        game.play(take,sheep);
        game.play(cross,north);
        game.play(leave,sheep);
        game.play(cross,south);
        game.play(take,wolf);
        game.play(cross,north);
        game.play(leave,wolf);
        game.play(take,sheep);
        game.play(cross,south);
        game.play(take,sheep);
        game.play(take,cabbage);
        game.play(cross,north);
        game.play(leave,cabbage);
        game.play(cross,south);
        game.play(take,sheep);
        game.play(cross,north);
        assertEquals(game.play(leave,sheep),"You dropped the sheep");
    }

    @Test
    public void cantLeaveNorthShoreWithSheepAndCabbageThere() {
        Game game = this.initializeGame();
        game.play(take,sheep);
        game.play(cross,north);
        game.play(leave,sheep);
        game.play(cross,south);
        game.play(take,cabbage);
        game.play(cross,north);
        game.play(leave,cabbage);
        assertEquals(game.play(cross,south),"The sheep will eat the cabbage!");
    }

    @Test
    public void cantLeaveNorthShoreWithSheepAndWolfThere() {
        Game game = this.initializeGame();
        game.play(take,sheep);
        game.play(cross,north);
        game.play(leave,sheep);
        game.play(cross,south);
        game.play(take,wolf);
        game.play(cross,north);
        game.play(leave,wolf);
        assertEquals(game.play(cross,south),"The wolf will eat the sheep!");
    }

    @Test
    public void cantPickWolfFromNorthShore() {
        Game game = this.initializeGame();
        game.play(take,sheep);
        game.play(cross,north);
        assertEquals(game.play(take,wolf),"It doesn't exist a wolf in the game WSC");
    }

    @Test
    public void whenBoatIsFullCantTakeOtherThingTest() {
        Game game = this.initializeGame();
        game.play(take,sheep);
        assertEquals(game.play(take,wolf),"You can't do that, the boat is full");
    }

    @Test
    public void cantMoveToSouthShoreFromSouthShoreTest() {
        Game game = this.initializeGame();
        assertEquals(game.play(cross,south),"It doesn't exist a south-shore in the game WSC");
    }
}
