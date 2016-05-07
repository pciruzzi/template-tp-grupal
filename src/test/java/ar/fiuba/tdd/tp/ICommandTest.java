package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.games.FetchQuest;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.icommand.Close;
import ar.fiuba.tdd.tp.icommand.Open;
import ar.fiuba.tdd.tp.icommand.Pick;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ICommandTest {

    @Test
    public void testOpen() {
        Game game = new FetchQuest();
        ElementTwo elementTwo;
        elementTwo = new ElementTwo("chest", false);
        elementTwo.addCommand(new Open());

        elementTwo.doCommand("open");

        assertTrue(elementTwo.getState());
    }

    @Test
    public void testClose() {
        ElementTwo elementTwo = new ElementTwo("chest", true);
        elementTwo.addCommand(new Close());

        elementTwo.doCommand("close");

        assertFalse(elementTwo.getState());
    }

}
