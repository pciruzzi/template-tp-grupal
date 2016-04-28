package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.games.*;
import org.junit.Test;

import static ar.fiuba.tdd.tp.ConstantVariables.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by gg on 4/28/2016.
 */
public class OpenDoorTest {

    private Game initializeGame(){
        Game game = new OpenDoor();
        game.createGame();
        return game;
    }

    @Test
    public void testLookArroundListElements(){
        Game game = this.initializeGame();
        assertEquals(game.doAction(lookArround),"There's a door and a key in the room.");
    }

    @Test
    public void testPickKeyPicksKey(){
        Game game = this.initializeGame();
        assertEquals(game.doAction(pickKey),movementAccepted);
    }

    @Test
    public void testPickKeyPicksKeyOpensDoorAndWinsGame(){
        Game game = this.initializeGame();
        assertEquals(game.doAction(pickKey),movementAccepted);
        assertEquals(game.doAction(openDoor),wonGame);
    }

    @Test
    public void testOpenDoorWithoutKeyReturnsError(){
        Game game = this.initializeGame();
        assertEquals(game.doAction(openDoor),"Ey! Where do you go?! Room 2 is locked.");
    }

    @Test
    public void testOpenDoorWithoutKeyReturnsErrorWhenPicksKeyLetsOpenAndWins(){
        Game game = this.initializeGame();
        assertEquals(game.doAction(openDoor),"Ey! Where do you go?! Room 2 is locked.");
        assertEquals(game.doAction(pickKey),movementAccepted);
        assertEquals(game.doAction(openDoor),wonGame);
    }
}
