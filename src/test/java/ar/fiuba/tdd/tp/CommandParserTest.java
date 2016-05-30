package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CommandParserTest {

    public static final String stick = "stick";
    public static final String chest = "chest";
    public static final String pick  = "pick";
    public static final String pickStick = pick + " " + stick;
    public static final String pickStickChest = pick + " " + stick + " " + chest;

    private CommandParser initializeCommandParser() {
        return new CommandParser();
    }

    private Element initializeContainingElement() {
        return new Element("room");
    }

    @Test
    public void getFirstElementTest() {
        CommandParser commandParser = this.initializeCommandParser();
        Element containingElement = this.initializeContainingElement();
        containingElement.addElement(new Element(stick));
        containingElement.addElement(new Element(chest));
        List<Element> elementList = containingElement.getElementList();
        Element firstElement = commandParser.getFirstElement(pickStick, elementList);
        String firstElementName = firstElement.getName();
        assertNotEquals(firstElement,null);
        assertEquals(firstElementName, stick);
    }

    @Test
    public void getSecondElementTest() {
        CommandParser commandParser = this.initializeCommandParser();
        Element containingElement = this.initializeContainingElement();
        containingElement.addElement(new Element(stick));
        containingElement.addElement(new Element(chest));
        List<Element> elementList = containingElement.getElementList();
        String firstElementName = commandParser.getFirstElement(pickStickChest, elementList).getName();
        assertEquals( firstElementName, stick);
        Element secondElement = commandParser.getSecondElement(pickStickChest, firstElementName, elementList);
        String secondElementName = secondElement.getName();
        assertEquals( secondElementName, chest);
    }

    @Test
    public void getCommandTest() {
        CommandParser commandParser = this.initializeCommandParser();
        Element containingElement = this.initializeContainingElement();
        containingElement.addElement(new Element(stick));
        containingElement.addElement(new Element(chest));
        List<Element> elementList = containingElement.getElementList();
        Element firstElement = commandParser.getFirstElement(pickStickChest, elementList);
        assertNotEquals(firstElement,null);
        String firstElementName = firstElement.getName();
        assertEquals(stick,firstElementName);
        Element seconElement = commandParser.getSecondElement(pickStickChest, firstElementName, elementList);
        String secondElementName = seconElement.getName();
        assertEquals( secondElementName, chest);
        String command = commandParser.getCommand(pickStickChest, firstElementName);
        assertEquals(command, pick);
    }
}
