package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class FetchQuestTest extends InitializationsForTests {

    private static final int id = 0;

    @Test
    public void lookAroundTest() {
        Engine engine = initializeEngineFetchQuest();
        String output = engine.doCommand(id, "look around");
        assertTrue(output.contains("stick"));
    }

    @Test
    public void youShouldWinTheGameIfYouPickTheKey() {
        Engine engine = initializeEngineFetchQuest();
        assertEquals("You won!!!", engine.doCommand(id, "pick stick"));
    }

}
