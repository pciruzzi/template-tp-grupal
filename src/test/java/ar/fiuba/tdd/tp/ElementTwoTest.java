package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gg on 5/12/2016.
 */
public class ElementTwoTest {

    @Test
    public void testElementTwoSizeSet() {
        ElementTwo element = new ElementTwo("chest");
        assertEquals(999,  element.getCapacity());

    }

    @Test
    public void testElementTwoSizeDecreasesWhen() {
        ElementTwo element = new ElementTwo("chest");
        assertEquals(999,  element.getCapacity());
        ElementTwo stick = new ElementTwo("stick");
        element.addElement(stick);
        assertEquals(998, element.getCapacity());
    }

    @Test
    public void testElementTwoBigStickDecreases() {
        ElementTwo element = new ElementTwo("chest");
        assertEquals(999,  element.getCapacity());
        ElementTwo stick = new ElementTwo("big stick");
        stick.setSize(3);
        element.addElement(stick);
        assertEquals(996, element.getCapacity());
    }

    @Test
    public void testElementTwoRemoveElementFromElementResetSize() {
        ElementTwo element = new ElementTwo("chest");
        assertEquals(999,  element.getCapacity());
        ElementTwo stick = new ElementTwo("big stick");
        stick.setSize(3);
        element.addElement(stick);
        assertEquals(996, element.getCapacity());
        element.removeElement(stick);
        assertEquals(999, element.getCapacity());
    }

    @Test
    public void testPlayerCanPickOneStickWithCapacity1() {
        ElementTwo player = new ElementTwo("player");
        player.setCapacity(1);

        ElementTwo stick = new ElementTwo("stick");
        player.addElement(stick);
        assertEquals(true,player.hasElement("stick"));
        assertEquals(0,player.getCapacity());
    }

    @Test
    public void testPlayerCantPickTwoSticks() {
        ElementTwo player = new ElementTwo("player");
        player.setCapacity(1);

        ElementTwo stick = new ElementTwo("stick");
        player.addElement(stick);
        assertEquals(true,player.hasElement("stick"));

        ElementTwo broom = new ElementTwo("broom");
        player.addElement(broom);

        assertEquals(false, player.addElement(broom));
    }

    @Test
    public void testPlayerCanPickVenomSize0() {
        ElementTwo player = new ElementTwo("player");
        player.setCapacity(1);

        ElementTwo stick = new ElementTwo("stick");
        ElementTwo venom = new ElementTwo("venom");
        venom.setSize(0);
        player.addElement(stick);
        assertEquals(true,player.hasElement("stick"));
        assertEquals(true, player.addElement(venom));
    }

}
