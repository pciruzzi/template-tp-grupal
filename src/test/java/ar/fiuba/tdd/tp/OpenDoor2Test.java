package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.OpenDoorConfiguration;
import org.junit.Test;

import static ar.fiuba.tdd.tp.ConstantVariables.*;
import static org.junit.Assert.assertEquals;


public class OpenDoor2Test {

//    private Game initializeGame() {
//        Game game = new OpenDoor2();
//        game.createGame();
//        return game;
//    }
//
    @Test
    public void lookArroundTest() {
        boolean isOpenDoor2 = true;
        Engine engine = new Engine();
        GameBuilder gameBuilder = new OpenDoorConfiguration(isOpenDoor2);
        engine.createGame(gameBuilder);
        String output = engine.doCommand("look around");

        assertEquals(output, "There's a door and a box in the room.");
    }

//    @Test
//    public void openLockedDoorShowsError() {
//        Game game = this.initializeGame();
//        assertEquals(game.doAction(openDoor), OPEN_DOOR_ERROR);
//    }
//
//    @Test
//    public void openBoxIsAVAlidMove() {
//        Game game = this.initializeGame();
//        assertEquals(game.doAction("open box"), movementAccepted);
//    }
//
//    @Test
//    public void openBoxAndLookArroundShowsKey() {
//        Game game = this.initializeGame();
//        game.doAction("open box");
//        assertEquals(game.doAction(lookArround),"There's a box, a door and a key in the room.");
//    }
//
//
//    @Test
//    public void pickKeyFromOpenBox() {
//        Game game = this.initializeGame();
//        game.doAction("open box");
//        assertEquals(game.doAction(pickKey),movementAccepted);
//    }
//
//    @Test
//    public void openDoorWithKeyWinsGame() {
//        Game game = this.initializeGame();
//        game.doAction("open box");
//        game.doAction(pickKey);
//        assertEquals(game.doAction(openDoor),wonGame);
//    }
}
