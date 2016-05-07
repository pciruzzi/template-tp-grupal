package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.games.FetchQuest;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.icommand.Open;
import ar.fiuba.tdd.tp.icommand.Pick;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ICommandTest {

    @Test
    public void testOpen() {
        Game game = new FetchQuest();
        ElementTwo elementTwo;
        elementTwo = new ElementTwo("stick", "dropped");
        elementTwo.addCommand(new Pick());

        elementTwo.doCommand("pick");

        assertEquals(elementTwo.getState(), "picked");
    }
}
