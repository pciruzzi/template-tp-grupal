package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.ElementTwo;
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
        CommandParser commandParser = new CommandParser();
        return commandParser;
    }

    private ElementTwo initializeContainingElement() {
        ElementTwo element = new ElementTwo("room");
        return element;
    }

    @Test
    public void getFirstElementTest() {
        CommandParser commandParser = this.initializeCommandParser();
        ElementTwo containingElement = this.initializeContainingElement();
        containingElement.addElement(new ElementTwo(stick));
        containingElement.addElement(new ElementTwo(chest));
        List<ElementTwo> elementList = containingElement.getElementList();
        ElementTwo firstElement = commandParser.getFirstElement(pickStick, elementList);
        System.out.println(pickStick);
        String firstElementName = firstElement.getName();
        assertNotEquals(firstElement,null);
        assertEquals(firstElementName, stick);
    }

    @Test
    public void getSecondElementTest() {
        CommandParser commandParser = this.initializeCommandParser();
        ElementTwo containingElement = this.initializeContainingElement();
        containingElement.addElement(new ElementTwo(stick));
        containingElement.addElement(new ElementTwo(chest));
        List<ElementTwo> elementList = containingElement.getElementList();
        String firstElementName = commandParser.getFirstElement(pickStickChest, elementList).getName();
        assertEquals( firstElementName, stick);
        ElementTwo secondElement = commandParser.getSecondElement(pickStickChest, firstElementName, elementList);
        String secondElementName = secondElement.getName();
        assertEquals( secondElementName, chest);
    }

    @Test
    public void getCommandTest() {
        CommandParser commandParser = this.initializeCommandParser();
        ElementTwo containingElement = this.initializeContainingElement();
        containingElement.addElement(new ElementTwo(stick));
        containingElement.addElement(new ElementTwo(chest));
        List<ElementTwo> elementList = containingElement.getElementList();
        ElementTwo firstElement = commandParser.getFirstElement(pickStickChest, elementList);
        assertNotEquals(firstElement,null);
        String firstElementName = firstElement.getName();
        assertEquals(stick,firstElementName);
        ElementTwo seconElement = commandParser.getSecondElement(pickStickChest, firstElementName, elementList);
        String secondElementName = seconElement.getName();
        assertEquals( secondElementName, chest);
        String command = commandParser.getCommand(pickStickChest, firstElementName);
        assertEquals(command, pick);
    }
}
