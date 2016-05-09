package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.interpreter.OrExpression;
import ar.fiuba.tdd.tp.interpreter.TerminalElement;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by gg on 5/9/2016.
 */
public class IInterpreterTest {

    @Test
    public void testInterpreterReturnsTrueWhenConteinsOneElement(){
        ElementTwo roomElement = new ElementTwo("room", true);
        ElementTwo stickElement =  new ElementTwo("stick", true);
        roomElement.addElement(stickElement);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");

        TerminalElement terminal = new TerminalElement(roomElement,stringList);

        assertTrue(terminal.interpret());
    }

    @Test
    public void testInterpreterReturnsFalseWhenRoomDoesntContainBroom(){
        ElementTwo roomElement = new ElementTwo("room", true);
        ElementTwo stickElement =  new ElementTwo("stick", true);
        roomElement.addElement(stickElement);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("broom");

        TerminalElement terminal = new TerminalElement(roomElement,stringList);

        assertFalse(terminal.interpret());
    }

    @Test
    public void testInterpreterReturnsFalseWhenConteinsStickAndBroomButDoesNotContainWindow(){
        ElementTwo roomElement = new ElementTwo("room", true);
        ElementTwo stickElement =  new ElementTwo("stick", true);
        ElementTwo broomElement =  new ElementTwo("broom", true);
        ElementTwo windowElement =  new ElementTwo("window", true);

        roomElement.addElement(stickElement);
        roomElement.addElement(broomElement);
        roomElement.addElement(windowElement);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");
        stringList.add("broom");

        TerminalElement terminal = new TerminalElement(roomElement,stringList);

        assertFalse(terminal.interpret());
    }

    @Test
    public void testOrInterpreterReturnsTrueWhenConteinsOneElementInRoomOneOrTwo(){
        ElementTwo roomElement = new ElementTwo("room", true);
        ElementTwo room2Element = new ElementTwo("room2", true);
        ElementTwo stickElement =  new ElementTwo("stick", true);
        roomElement.addElement(stickElement);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");

        TerminalElement terminalRoom1 = new TerminalElement(roomElement,stringList);
        TerminalElement terminalRoom2 = new TerminalElement(room2Element,stringList);
        OrExpression orExpression = new OrExpression(terminalRoom1,terminalRoom2);

        assertTrue(orExpression.interpret());
    }

    @Test
    public void testOrInterpreterReturnsFalseWhenRoom1HasBroomAndRoom2HasStick(){
        ElementTwo roomElement = new ElementTwo("room", true);
        ElementTwo room2Element = new ElementTwo("room2", true);
        ElementTwo stickElement =  new ElementTwo("stick", true);
        ElementTwo broomElement = new ElementTwo("broom", true);

        roomElement.addElement(broomElement);
        room2Element.addElement(stickElement);

        ArrayList<String> stringListRoom1 = new ArrayList<String>();
        stringListRoom1.add("stick");
        ArrayList<String> stringListRoom2 = new ArrayList<String>();
        stringListRoom2.add("broom");

        TerminalElement terminalRoom1 = new TerminalElement(roomElement,stringListRoom1);
        TerminalElement terminalRoom2 = new TerminalElement(room2Element,stringListRoom2);
        OrExpression orExpression = new OrExpression(terminalRoom1,terminalRoom2);

        assertFalse(orExpression.interpret());
    }


}
