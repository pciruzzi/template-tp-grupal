package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.games.*;
import org.junit.Test;

import javax.xml.bind.annotation.XmlAttribute;

import static ar.fiuba.tdd.tp.ConstantVariables.*;
import static org.junit.Assert.assertEquals;


/**
 * Created by gg on 4/28/2016.
 */
public class EvilThingTest {

    private Game initializeGame() {
        Game game = new EvilThing();
        game.createGame();
        return game;
    }

    @Test
    public void lookArroundTest() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(lookArround), "There's a key and a door in the room.");
    }

    @Test
    public void pickKeyTest() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(pickKey), movementAccepted);
    }

    @Test
    public void openDoorWithKey() {
        Game game = this.initializeGame();
        game.doAction(pickKey);
        assertEquals(game.doAction(openDoor),movementAccepted);
    }

    @Test
    public void roomTwoHasAthief() {
        Game game = this.initializeGame();
        game.doAction(pickKey);
        game.doAction(openDoor);
        assertEquals(game.doAction(lookArround),"There's a door and a thief in the room.");
    }

    @Test
    public void youCantEnterRoom3WithRoom() {
        Game game = this.initializeGame();
        game.doAction(pickKey);
        game.doAction(openDoor);
        assertEquals(game.doAction(openDoor),OPEN_DOOR_ERROR);
    }

    @Test
    public void youAreAbleToSpeakToTheThief() {
        Game game = this.initializeGame();
        game.doAction(pickKey);
        game.doAction(openDoor);
        assertEquals(game.doAction("talkTo thief"),"The thief has just stolen your object!");
    }


    @Test
    public void ifTheThiefStealsYouYouCanOpenDoor2AndWin() {
        Game game = this.initializeGame();
        game.doAction(pickKey);
        game.doAction(openDoor);
        game.doAction("talkTo thief");
        assertEquals(game.doAction(openDoor),wonGame);
    }






}
