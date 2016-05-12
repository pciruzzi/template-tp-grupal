package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.WSCConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class WSCConfigurationTest {

//    private static final String take = "take";
//    private static final String leave = "leave";
//    private static final String sheep = "sheep";
//    private static final String wolf = "wolf";
//    private static final String cabbage = "cabbage";
//    private static final String cross = "cross";
//    private static final String north = "north-shore";
//    private static final String south = "south-shore";
//    private static final String invalidAction = "Invalid Action.";
//    private static final String pickSuccesfull = "Ok.";
//    private static final String crossSuccesfull = "You have crossed";
//    private static final String error = "error";
//
//
//    private Game initializeGame() {
//        WSCConfiguration WSC = new WSCConfiguration();
//        Game game = WSC.build();
//        return game;
//    }
//
//    @Test
//    public void takeSheepOnBoatTest() {
//        Game game = this.initializeGame();
//        assertEquals(game.play(take,sheep),pickSuccesfull);
//    }
//
//    @Test
//    public void takeSheepAndLeaveOnTheOtherShoreTest() {
//        Game game = this.initializeGame();
//        game.play(take,sheep);
//        game.play(cross,north);
//        game.play(leave,sheep);
//        assertEquals(game.play(cross,south),crossSuccesfull);
//    }
//
//    @Test
//    public void whenBoatIsFullCantTakeOtherThingTest() {
//        Game game = this.initializeGame();
//        game.play(take,sheep);
//        assertEquals(game.play(take,wolf),"You can't do that! You already have the sheep");
//    }
//
//    @Test
//    public void whenMoveSheepToOtherShoreTest() {
//        Game game = this.initializeGame();
//        game.play(take,sheep);
//        assertEquals(game.play(cross,north),crossSuccesfull);
//    }
//
//    @Test
//    public void canTakeWolfButCantCrossToShoreTest() {
//        Game game = this.initializeGame();
//        game.play(take,wolf);
//        assertEquals(game.play(cross,north),error);
//    }
//
//    @Test
//    public void canTakeWolfAndLeaveItInTheSameShoreTest() {
//        Game game = this.initializeGame();
//        game.play(take,wolf);
//        assertEquals(game.play(leave, wolf),pickSuccesfull);
//    }
//
//    @Test
//    public void cantLeaveShoreWithEmptyBeatTest() {
//        Game game = this.initializeGame();
//        assertEquals(game.play(cross,north),error);
//    }
//
//    @Test
//    public void cantMoveToSouthShoreFromSouthShoreTest() {
//        Game game = this.initializeGame();
//        assertEquals(game.play(cross,south),invalidAction);
//    }
//
//    @Test
//    public void withTheRightMovementsYouWinTheGameTest() {
//        Game game = this.initializeGame();
//        game.play(take,sheep);
//        game.play(cross,north);
//        game.play(leave,sheep);
//        game.play(cross,south);
//        game.play(take,wolf);
//        game.play(cross,north);
//        game.play(leave,wolf);
//        game.play(take,sheep);
//        game.play(cross,south);
//        game.play(take,sheep);
//        game.play(take,cabbage);
//        game.play(cross,north);
//        game.play(leave,cabbage);
//        game.play(cross,south);
//        game.play(take,sheep);
//        game.play(cross,north);
//        assertEquals(game.play(leave,sheep),"You won!!!");
//    }

}
