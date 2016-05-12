package ar.fiuba.tdd.tp;

import org.junit.Test;

import javax.xml.bind.annotation.XmlAttribute;

import static ar.fiuba.tdd.tp.ConstantVariables.*;
import static org.junit.Assert.assertEquals;


public class EvilThingTest {

//    private Game initializeGame() {
//        Game game = new EvilThing();
//        game.createGame();
//        return game;
//    }
//
//    @Test
//    public void lookArroundTest() {
//        Game game = this.initializeGame();
//        assertEquals(game.play(lookArround), "There's a key and a door in the room.");
//    }
//
//    @Test
//    public void pickKeyTest() {
//        Game game = this.initializeGame();
//        assertEquals(game.play(pickKey), movementAccepted);
//    }
//
//    @Test
//    public void openDoorWithKey() {
//        Game game = this.initializeGame();
//        game.play(pickKey);
//        assertEquals(game.play(openDoor),movementAccepted);
//    }
//
//    @Test
//    public void roomTwoHasAthief() {
//        Game game = this.initializeGame();
//        game.play(pickKey);
//        game.play(openDoor);
//        assertEquals(game.play(lookArround),"There's a door and a thief in the room.");
//    }
//
//    @Test
//    public void youCantEnterRoom3WithRoom() {
//        Game game = this.initializeGame();
//        game.play(pickKey);
//        game.play(openDoor);
//        assertEquals(game.play(openDoor),OPEN_DOOR_ERROR);
//    }
//
//    @Test
//    public void youAreAbleToSpeakToTheThief() {
//        Game game = this.initializeGame();
//        game.play(pickKey);
//        game.play(openDoor);
//        assertEquals(game.play("talkTo thief"),"The thief has just stolen your object!");
//    }
//
//    @Test
//    public void ifTheThiefStealsYouYouCanOpenDoor2AndWin() {
//        Game game = this.initializeGame();
//        game.play(pickKey);
//        game.play(openDoor);
//        game.play("talkTo thief");
//        assertEquals(game.play(openDoor),wonGame);
//    }
}
