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

    @Test
    public void roomTwoHasAthief() {
        Engine engine = this.initializeEngineEvilThing();
        engine.doCommand("pick key");
        engine.doCommand("open door");
        String output = engine.doCommand("look around");
        assertTrue(output.contains("thief"));
        assertTrue(output.contains("door"));
    }

    @Test
    public void youCantEnterRoom3WithRoom() {
        Engine engine = this.initializeEngineEvilThing();
        engine.doCommand("pick key");
        engine.doCommand("open door");
        assertEquals("Ey! You can't do that! The otherDoor is locked", engine.doCommand("open otherDoor"));
    }

    @Test
    public void youAreAbleToSpeakToTheThief() {
        Engine engine = this.initializeEngineEvilThing();
        engine.doCommand("pick key");
        engine.doCommand("open door");
        assertEquals("Hi!\nThe thief has just stolen your object!", engine.doCommand("talk to thief"));
    }

    @Test
    public void ifTheThiefStealsYouYouCanOpenDoor2AndWin() {
        Engine engine = this.initializeEngineEvilThing();
        engine.doCommand("pick key");
        engine.doCommand("open door");
        engine.doCommand("talk to thief");

        assertEquals("You won!!!", engine.doCommand("open otherDoor"));
    }

}
