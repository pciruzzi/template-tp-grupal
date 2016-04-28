package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.games.*;
import org.junit.Test;

import static ar.fiuba.tdd.tp.ConstantVariables.*;
import static org.junit.Assert.assertEquals;


/**
 * Created by gg on 4/28/2016.
 */
public class OpenDoor2Test {

/*    private Game initializeGame() {
        Game game = new OpenDoor2();
        game.createGame();
        return game;
    }

    @Test
    public void lookArroundTest() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(lookArround), "There's a door and a box in the room.");
    }

    @Test
    public void openLockedDoorShowsError() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(openDoor), OPEN_DOOR_ERROR);
    }

    @Test
    public void openBoxIsAVAlidMove() {
        Game game = this.initializeGame();
        assertEquals(game.doAction("open box"), movementAccepted);
    }

    @Test
    public void openBoxAndLookArroundShowsKey() {
        Game game = this.initializeGame();
        game.doAction("open box");
        assertEquals(game.doAction(lookArround),"");
    }

    @Test
    public void openAndCloseBoxDoesntShowKey() {
        Game game = this.initializeGame();
        game.doAction("open box");
        game.doAction("close box");
        assertEquals(game.doAction(lookArround),"There's a door and a box in the room.");
    }

    @Test
    public void pickKeyFromOpenBox() {
        Game game = this.initializeGame();
        game.doAction("open box");
        assertEquals(game.doAction(pickKey),movementAccepted);
    }

    @Test
    public void openDoorWithKeyWinsGame() {
        Game game = this.initializeGame();
        game.doAction("open box");
        game.doAction(pickKey);
        assertEquals(game.doAction(openDoor),wonGame);
    }
*/


}
