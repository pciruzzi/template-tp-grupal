package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.interpreter.logic.AndExpression;
import ar.fiuba.tdd.tp.interpreter.logic.OrExpression;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.DoesNotContainElements;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.TerminalExpression;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static ar.fiuba.tdd.tp.Constants.NON_PLAYER;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class IInterpreterTest {

    //Este es para pasar en los tests, aunque no se va a usar porque se pasa un element
    static Player player = new Player(NON_PLAYER);

    @Test
    public void testInterpreterReturnsTrueWhenConteinsOneElement() {
        Element room = new Element("room");
        Element stick =  new Element("stick");
        room.addElement(stick);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("stick");

        TerminalExpression terminal = new ContainsElements(Optional.of(room),stringList);
        assertTrue(terminal.interpret(player));
    }

    @Test
    public void testInterpreterReturnsFalseWhenRoomDoesntContainStick() {
        Element room = new Element("room");
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("stick");

        TerminalExpression terminal = new ContainsElements(Optional.of(room),stringList);
        assertFalse(terminal.interpret(player));
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

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("stick");
        stringList.add("broom");

        TerminalExpression terminal = new ContainsElements(Optional.of(room),stringList);
        assertTrue(terminal.interpret(player));
    }

    @Test
    public void testOrInterpreterReturnsTrueWhenConteinsOneElementInRoomOneOrTwo() {
        Element room = new Element("room");
        Element room2 = new Element("room2");
        Element stick =  new Element("stick");
        room.addElement(stick);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("stick");

        TerminalExpression terminalRoom1 = new ContainsElements(Optional.of(room),stringList);
        TerminalExpression terminalRoom2 = new ContainsElements(Optional.of(room2),stringList);
        OrExpression orExpression = new OrExpression(terminalRoom1,terminalRoom2);

        assertTrue(orExpression.interpret(player));
    }

    @Test
    public void testOrInterpreterReturnsFalseWhenRoom1HasBroomAndRoom2HasStick() {
        Element room = new Element("room");
        Element room2 = new Element("room2");
        Element stick =  new Element("stick");
        Element broom = new Element("broom");

        room.addElement(broom);
        room2.addElement(stick);

        ArrayList<String> stringListRoom1 = new ArrayList<>();
        stringListRoom1.add("stick");
        ArrayList<String> stringListRoom2 = new ArrayList<>();
        stringListRoom2.add("broom");

        TerminalExpression terminalRoom1 = new ContainsElements(Optional.of(room),stringListRoom1);
        TerminalExpression terminalRoom2 = new ContainsElements(Optional.of(room2),stringListRoom2);
        OrExpression orExpression = new OrExpression(terminalRoom1,terminalRoom2);

        assertFalse(orExpression.interpret(player));
    }

    @Test
    public void testAndInterpretorReturnsTrueWhenRoomOneAndTwoHasStick() {
        Element room = new Element("room");
        Element room2 = new Element("room2");
        Element stick =  new Element("stick");

        room.addElement(stick);
        room2.addElement(stick);

        ArrayList<String> stringListRoom1 = new ArrayList<>();
        stringListRoom1.add("stick");
        ArrayList<String> stringListRoom2 = new ArrayList<>();
        stringListRoom2.add("stick");

        TerminalExpression terminalRoom1 = new ContainsElements(Optional.of(room),stringListRoom1);
        TerminalExpression terminalRoom2 = new ContainsElements(Optional.of(room2),stringListRoom2);
        AndExpression andExpression = new AndExpression(terminalRoom1,terminalRoom2);

        assertTrue(andExpression.interpret(player));
    }

    @Test
    public void testAndInterpretorReturnsFalseWhenRoomOneHasStickButRoom2Doesnt() {
        Element room = new Element("room");
        Element stick =  new Element("stick");
        room.addElement(stick);

        ArrayList<String> stringListRoom1 = new ArrayList<>();
        stringListRoom1.add("stick");
        ArrayList<String> stringListRoom2 = new ArrayList<>();
        stringListRoom2.add("broom");

        Element room2 = new Element("room2");

        TerminalExpression terminalRoom1 = new ContainsElements(Optional.of(room),stringListRoom1);
        TerminalExpression terminalRoom2 = new ContainsElements(Optional.of(room2),stringListRoom2);
        AndExpression andExpression = new AndExpression(terminalRoom1,terminalRoom2);

        assertFalse(andExpression.interpret(player));
    }

    @Test
    public void testMixedAndOrReturnTrueWhenChestHasBroomOrStickAndRoomHasWindow() {
        Element room = new Element("room");
        Element chest = new Element("chest");
        Element broom = new Element("broom");
        Element window = new Element("window");

        chest.addElement(broom);
        room.addElement(window);

        ArrayList<String> roomElements = new ArrayList<>();
        ArrayList<String> chestElementBroom = new ArrayList<>();
        ArrayList<String> chestElementStick = new ArrayList<>();

        roomElements.add("window");
        chestElementBroom.add("broom");
        chestElementStick.add("stick");

        TerminalExpression terminalRoom = new ContainsElements(Optional.of(room), roomElements);
        TerminalExpression terminalChestBroom = new ContainsElements(Optional.of(chest), chestElementBroom);
        TerminalExpression terminalChestStick = new ContainsElements(Optional.of(chest), chestElementStick);

        OrExpression orExpression = new OrExpression(terminalChestBroom, terminalChestStick);
        AndExpression andExpression = new AndExpression(orExpression,terminalRoom);

        assertTrue(andExpression.interpret(player));
    }

    @Test
    public void testTwoOrExpressionConectedWithAnAndExpressionReturnsTrueWhenOrTrue() {
        Element room = new Element("room");
        Element chest = new Element("chest");
        Element broom = new Element("broom");
        Element window = new Element("window");

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

        OrExpression orExpressionChest = new OrExpression(new ContainsElements(Optional.of(chest), chestElementBroom),
                new ContainsElements(Optional.of(chest), chestElementStick));
        OrExpression orExpressionRoom = new OrExpression(new ContainsElements(Optional.of(room), roomElementsPainting),
                new ContainsElements(Optional.of(room), roomElementsWindow));

        AndExpression andExpression = new AndExpression(orExpressionChest,orExpressionRoom);
        assertTrue(andExpression.interpret(player));
    }

    @Test
    public void testNotExpressionReturnsTrueWhenEmptyRoom() {
        Element room = new Element("room");
        ArrayList<String> roomList = new ArrayList<>();
        roomList.add("stick");

        TerminalExpression notExpression = new DoesNotContainElements(Optional.of(room), roomList);
        assertTrue(notExpression.interpret(player));
    }

    @Test
    public void testNotExpressionReturnsTrueWhenRoomHasStickAndDoorButDoesntHaveKey() {
        Element room = new Element("room");
        Element stick = new Element("stick");
        Element door = new Element("door");

        room.addElement(stick);
        room.addElement(door);

        ArrayList<String> roomList = new ArrayList<>();
        roomList.add("key");

        TerminalExpression notExpression = new DoesNotContainElements(Optional.of(room), roomList);
        assertTrue(notExpression.interpret(player));
    }

    @Test
    public void testNotExpressionReturnsFalseWhenRoomHasStick() {
        Element room = new Element("room");
        Element stick = new Element("stick");
        room.addElement(stick);

        ArrayList<String> roomList = new ArrayList<>();
        roomList.add("stick");

        TerminalExpression notExpression = new DoesNotContainElements(Optional.of(room), roomList);
        assertFalse(notExpression.interpret(player));
    }

    @Test
    public void testShouthShoreConteinsWolfAndSheep() {
        Element south = new Element("south");
        Element sheep = new Element("sheep");
        Element cabbage = new Element("cabbage");

        south.addElement(sheep);
        south.addElement(cabbage);

        ArrayList<String> winCondition = new ArrayList<>();
        winCondition.add("sheep");
        winCondition.add("cabbage");

        IInterpreter wolfCondition = new DoesNotContainElements(Optional.of(south),winCondition);
        assertFalse(wolfCondition.interpret(player));
    }
}
