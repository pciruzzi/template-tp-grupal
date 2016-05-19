package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.EvilThingConfiguration;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.OpenDoorConfiguration;
import org.junit.Test;

import javax.xml.bind.annotation.XmlAttribute;

import static ar.fiuba.tdd.tp.ConstantVariables.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EvilThingTest {

    private Engine initializeEngineEvilThing() {
        boolean isOpenDoor2 = true;
        Engine engine = new Engine();
        GameBuilder gameBuilder = new EvilThingConfiguration();
        engine.createGame(gameBuilder);
        return engine;
    }

    @Test
    public void lookAroundTest() {
        Engine engine = this.initializeEngineEvilThing();
        assertTrue(engine.doCommand("look around").contains("key"));
        assertTrue(engine.doCommand("look around").contains("door"));
    }

    @Test
    public void crossRoomTest() {
        Engine engine = initializeEngineEvilThing();
        engine.doCommand("pick key");
        assertEquals("You have crossed", engine.doCommand("open door"));
    }

//    @Test
//    public void roomTwoHasAthief() {
//        Game game = this.initializeGame();
//        game.play(pickKey);
//        game.play(openDoor);
//        assertEquals(game.play(lookArround),"There's a door and a thief in the room.");
//    }

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
