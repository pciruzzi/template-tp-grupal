package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.interpreter.*;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by gg on 5/9/2016.
 */
public class IInterpreterTest {

    @Test
    public void testInterpreterReturnsTrueWhenConteinsOneElement() {
        ElementTwo room = new ElementTwo("room");
        ElementTwo stick =  new ElementTwo("stick");
        room.addElement(stick);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");

        TerminalExpression terminal = new ContainsElements(room,stringList);

        assertTrue(terminal.interpret());
    }

    @Test
    public void testInterpreterReturnsFalseWhenRoomDoesntContainStick() {
        ElementTwo room = new ElementTwo("room");

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");

        TerminalExpression terminal = new ContainsElements(room,stringList);

        assertFalse(terminal.interpret());
    }

    @Test
    public void testInterpreterReturnsFalseWhenConteinsStickAndBroomButDoesNotContainWindow() {
        ElementTwo room = new ElementTwo("room");
        ElementTwo stick =  new ElementTwo("stick");
        ElementTwo broom =  new ElementTwo("broom");
        ElementTwo window =  new ElementTwo("window");

        room.addElement(stick);
        room.addElement(broom);
        room.addElement(window);

        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("stick");
        stringList.add("broom");

        TerminalExpression terminal = new ContainsElements(room,stringList);

        assertFalse(terminal.interpret());
    }

    @Test
    public void testOrInterpreterReturnsTrueWhenConteinsOneElementInRoomOneOrTwo() {
        ElementTwo room = new ElementTwo("room");
        ElementTwo room2 = new ElementTwo("room2");
        ElementTwo stick =  new ElementTwo("stick");
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
        ElementTwo room = new ElementTwo("room");
        ElementTwo room2 = new ElementTwo("room2");
        ElementTwo stick =  new ElementTwo("stick");
        ElementTwo broom = new ElementTwo("broom");

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
        ElementTwo room = new ElementTwo("room");
        ElementTwo room2 = new ElementTwo("room2");
        ElementTwo stick =  new ElementTwo("stick");

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
        ElementTwo room = new ElementTwo("room");
        ElementTwo stick =  new ElementTwo("stick");

        room.addElement(stick);

        ArrayList<String> stringListRoom1 = new ArrayList<String>();
        stringListRoom1.add("stick");
        ArrayList<String> stringListRoom2 = new ArrayList<String>();
        stringListRoom2.add("broom");

        ElementTwo room2 = new ElementTwo("room2");

        TerminalExpression terminalRoom1 = new ContainsElements(room,stringListRoom1);
        TerminalExpression terminalRoom2 = new ContainsElements(room2,stringListRoom2);
        AndExpression andExpression = new AndExpression(terminalRoom1,terminalRoom2);

        assertFalse(andExpression.interpret());
    }

    @Test
    public void testMixedAndOrReturnTrueWhenChestHasBroomOrStickAndRoomHasWindow() {
        ElementTwo room = new ElementTwo("room");
        ElementTwo chest = new ElementTwo("chest");
        ElementTwo broom = new ElementTwo("broom");
        ElementTwo window = new ElementTwo("window");

        chest.addElement(broom);
        room.addElement(window);

        ArrayList<String> roomElements = new ArrayList<>();
        ArrayList<String> chestElementBroom = new ArrayList<>();
        ArrayList<String> chestElementStick = new ArrayList<>();

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
        ElementTwo room = new ElementTwo("room");
        ElementTwo chest = new ElementTwo("chest");
        ElementTwo broom = new ElementTwo("broom");
        ElementTwo window = new ElementTwo("window");

        chest.addElement(broom);
        room.addElement(window);

        ArrayList<String> roomElementsWindow = new ArrayList<>();
        ArrayList<String> roomElementsPainting = new ArrayList<>();
        ArrayList<String> chestElementBroom = new ArrayList<>();

        roomElementsWindow.add("window");
        roomElementsPainting.add("painting");
        chestElementBroom.add("broom");

        ArrayList<String> chestElementStick = new ArrayList<>();

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
        ElementTwo room = new ElementTwo("room");
        ArrayList<String> roomList = new ArrayList<>();
        roomList.add("stick");

        TerminalExpression notExpression = new DoesNotContainElements(room, roomList);
        assertTrue(notExpression.interpret());
    }

    @Test
    public void testNotExpressionReturnsTrueWhenRoomHasStickAndDoorButDoesntHaveKey() {
        ElementTwo room = new ElementTwo("room");
        ElementTwo stick = new ElementTwo("stick");
        ElementTwo door = new ElementTwo("door");

        room.addElement(stick);
        room.addElement(door);

        ArrayList<String> roomList = new ArrayList<>();
        roomList.add("key");

        TerminalExpression notExpression = new DoesNotContainElements(room, roomList);
        assertTrue(notExpression.interpret());
    }

    @Test
    public void testNotExpressionReturnsFalseWhenRoomHasStick() {
        ElementTwo room = new ElementTwo("room");
        ElementTwo stick = new ElementTwo("stick");

        room.addElement(stick);

        ArrayList<String> roomList = new ArrayList<>();
        roomList.add("stick");

        TerminalExpression notExpression = new DoesNotContainElements(room, roomList);
        assertFalse(notExpression.interpret());
    }





}
