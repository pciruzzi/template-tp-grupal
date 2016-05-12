package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.*;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class IInterpreterTest {

    @Test
    public void testInterpreterReturnsTrueWhenConteinsOneElement() {
        Element room = new Element("room");
        Element stick =  new Element("stick");
        room.addElement(stick);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");

        TerminalExpression terminal = new ContainsElements(room,stringList);

        assertTrue(terminal.interpret());
    }

    @Test
    public void testInterpreterReturnsFalseWhenRoomDoesntContainStick() {
        Element room = new Element("room");

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");

        TerminalExpression terminal = new ContainsElements(room,stringList);

        assertFalse(terminal.interpret());
    }

    @Test
    public void testInterpreterReturnsTrueWhenConteinsStickAndBroomButDoesNotContainWindow() {
        Element room = new Element("room");
        Element stick =  new Element("stick");
        Element broom =  new Element("broom");
        Element window =  new Element("window");

        room.addElement(stick);
        room.addElement(broom);
        room.addElement(window);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");
        stringList.add("broom");

        TerminalExpression terminal = new ContainsElements(room,stringList);

        assertTrue(terminal.interpret());
    }

    @Test
    public void testOrInterpreterReturnsTrueWhenConteinsOneElementInRoomOneOrTwo() {
        Element room = new Element("room");
        Element room2 = new Element("room2");
        Element stick =  new Element("stick");
        room.addElement(stick);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");

        TerminalExpression terminalRoom1 = new ContainsElements(room,stringList);
        TerminalExpression terminalRoom2 = new ContainsElements(room2,stringList);
        OrExpression orExpression = new OrExpression(terminalRoom1,terminalRoom2);

        assertTrue(orExpression.interpret());
    }

    @Test
    public void testOrInterpreterReturnsFalseWhenRoom1HasBroomAndRoom2HasStick() {
        Element room = new Element("room");
        Element room2 = new Element("room2");
        Element stick =  new Element("stick");
        Element broom = new Element("broom");

        room.addElement(broom);
        room2.addElement(stick);

        ArrayList<String> stringListRoom1 = new ArrayList<String>();
        stringListRoom1.add("stick");
        ArrayList<String> stringListRoom2 = new ArrayList<String>();
        stringListRoom2.add("broom");

        TerminalExpression terminalRoom1 = new ContainsElements(room,stringListRoom1);
        TerminalExpression terminalRoom2 = new ContainsElements(room2,stringListRoom2);
        OrExpression orExpression = new OrExpression(terminalRoom1,terminalRoom2);

        assertFalse(orExpression.interpret());
    }

    @Test
    public void testAndInterpretorReturnsTrueWhenRoomOneAndTwoHasStick() {
        Element room = new Element("room");
        Element room2 = new Element("room2");
        Element stick =  new Element("stick");

        room.addElement(stick);
        room2.addElement(stick);

        ArrayList<String> stringListRoom1 = new ArrayList<String>();
        stringListRoom1.add("stick");
        ArrayList<String> stringListRoom2 = new ArrayList<String>();
        stringListRoom2.add("stick");

        TerminalExpression terminalRoom1 = new ContainsElements(room,stringListRoom1);
        TerminalExpression terminalRoom2 = new ContainsElements(room2,stringListRoom2);
        AndExpression andExpression = new AndExpression(terminalRoom1,terminalRoom2);

        assertTrue(andExpression.interpret());
    }

    @Test
    public void testAndInterpretorReturnsFalseWhenRoomOneHasStickButRoom2Doesnt() {
        Element room = new Element("room");
        Element stick =  new Element("stick");

        room.addElement(stick);

        ArrayList<String> stringListRoom1 = new ArrayList<String>();
        stringListRoom1.add("stick");
        ArrayList<String> stringListRoom2 = new ArrayList<String>();
        stringListRoom2.add("broom");

        Element room2 = new Element("room2");

        TerminalExpression terminalRoom1 = new ContainsElements(room,stringListRoom1);
        TerminalExpression terminalRoom2 = new ContainsElements(room2,stringListRoom2);
        AndExpression andExpression = new AndExpression(terminalRoom1,terminalRoom2);

        assertFalse(andExpression.interpret());
    }

    @Test
    public void testMixedAndOrReturnTrueWhenChestHasBroomOrStickAndRoomHasWindow() {
        Element room = new Element("room");
        Element chest = new Element("chest");
        Element broom = new Element("broom");
        Element window = new Element("window");

        chest.addElement(broom);
        room.addElement(window);

        ArrayList<String> roomElements = new ArrayList<String>();
        ArrayList<String> chestElementBroom = new ArrayList<String>();
        ArrayList<String> chestElementStick = new ArrayList<String>();

        roomElements.add("window");
        chestElementBroom.add("broom");
        chestElementStick.add("stick");

        TerminalExpression terminalRoom = new ContainsElements(room, roomElements);
        TerminalExpression terminalChestBroom = new ContainsElements(chest, chestElementBroom);
        TerminalExpression terminalChestStick = new ContainsElements(chest, chestElementStick);

        OrExpression orExpression = new OrExpression(terminalChestBroom, terminalChestStick);
        AndExpression andExpression = new AndExpression(orExpression,terminalRoom);

        assertTrue(andExpression.interpret());
    }

    @Test
    public void testTwoOrExpressionConectedWithAnAndExpressionReturnsTrueWhenOrTrue() {
        Element room = new Element("room");
        Element chest = new Element("chest");
        Element broom = new Element("broom");
        Element window = new Element("window");

        chest.addElement(broom);
        room.addElement(window);

        ArrayList<String> roomElementsWindow = new ArrayList<String>();
        ArrayList<String> roomElementsPainting = new ArrayList<String>();
        ArrayList<String> chestElementBroom = new ArrayList<String>();

        roomElementsWindow.add("window");
        roomElementsPainting.add("painting");
        chestElementBroom.add("broom");

        ArrayList<String> chestElementStick = new ArrayList<String>();

        chestElementStick.add("stick");

        OrExpression orExpressionChest = new OrExpression(new ContainsElements(chest, chestElementBroom),
                new ContainsElements(chest, chestElementStick));
        OrExpression orExpressionRoom = new OrExpression(new ContainsElements(room, roomElementsPainting),
                new ContainsElements(room, roomElementsWindow));

        AndExpression andExpression = new AndExpression(orExpressionChest,orExpressionRoom);

        assertTrue(andExpression.interpret());
    }

    @Test
    public void testNotExpressionReturnsTrueWhenEmptyRoom() {
        Element room = new Element("room");
        ArrayList<String> roomList = new ArrayList<String>();
        roomList.add("stick");

        TerminalExpression notExpression = new DoesNotContainElements(room, roomList);
        assertTrue(notExpression.interpret());
    }

    @Test
    public void testNotExpressionReturnsTrueWhenRoomHasStickAndDoorButDoesntHaveKey() {
        Element room = new Element("room");
        Element stick = new Element("stick");
        Element door = new Element("door");

        room.addElement(stick);
        room.addElement(door);

        ArrayList<String> roomList = new ArrayList<String>();
        roomList.add("key");

        TerminalExpression notExpression = new DoesNotContainElements(room, roomList);
        assertTrue(notExpression.interpret());
    }

    @Test
    public void testNotExpressionReturnsFalseWhenRoomHasStick() {
        Element room = new Element("room");
        Element stick = new Element("stick");

        room.addElement(stick);

        ArrayList<String> roomList = new ArrayList<String>();
        roomList.add("stick");

        TerminalExpression notExpression = new DoesNotContainElements(room, roomList);
        assertFalse(notExpression.interpret());
    }
}
