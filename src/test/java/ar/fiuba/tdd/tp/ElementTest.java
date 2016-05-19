package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementTest {

    @Test
    public void testElementSizeSet() {
        Element element = new Element("chest");
        assertEquals(999,  element.getCapacity());
    }

    @Test
    public void testElementSizeDecreasesWhen() {
        Element element = new Element("chest");
        assertEquals(999,  element.getCapacity());
        Element stick = new Element("stick");
        element.addElement(stick);
        assertEquals(998, element.getCapacity());
    }

    @Test
    public void testElementBigStickDecreases() {
        Element element = new Element("chest");
        assertEquals(999,  element.getCapacity());
        Element stick = new Element("big stick");
        stick.setSize(3);
        element.addElement(stick);
        assertEquals(996, element.getCapacity());
    }

    @Test
    public void testElementRemoveElementFromElementResetSize() {
        Element element = new Element("chest");
        assertEquals(999,  element.getCapacity());
        Element stick = new Element("big stick");
        stick.setSize(3);
        element.addElement(stick);
        assertEquals(996, element.getCapacity());
        element.removeElement(stick);
        assertEquals(999, element.getCapacity());
    }

    @Test
    public void testPlayerCanPickOneStickWithCapacity1() {
        Element player = new Element("player");
        player.setCapacity(1);

        Element stick = new Element("stick");
        player.addElement(stick);
        assertEquals(true,player.hasElement("stick"));
        assertEquals(0,player.getCapacity());
    }

    @Test
    public void testPlayerCantPickTwoSticks() {
        Element player = new Element("player");
        player.setCapacity(1);

        Element stick = new Element("stick");
        player.addElement(stick);
        assertEquals(true,player.hasElement("stick"));

        Element broom = new Element("broom");
        player.addElement(broom);

        assertEquals(false, player.addElement(broom));
    }

    @Test
    public void testPlayerCanPickVenomSize0() {
        Element player = new Element("player");
        player.setCapacity(1);

        Element stick = new Element("stick");
        Element venom = new Element("venom");
        venom.setSize(0);
        player.addElement(stick);
        assertEquals(true,player.hasElement("stick"));
        assertEquals(true, player.addElement(venom));
    }
}