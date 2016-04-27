package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;
import org.junit.Test;

public class MainTests {

    @Test
    public void equalsStatesWithOneElement() {
        State stateOne = new State();
        stateOne.addElement(new Element("element", "state"));

        State stateTwo = new State();
        stateTwo.addElement(new Element("element", "state"));

        assert stateOne.isEqual(stateTwo);
    }

    @Test
    public void equalsStatesWithThreeElements() {
        State stateOne = new State();
        stateOne.addElement(new Element("element", "state"));
        stateOne.addElement(new Element("fire", "on"));
        stateOne.addElement(new Element("water", "off"));

        State stateTwo = new State();
        stateTwo.addElement(new Element("element", "state"));
        stateTwo.addElement(new Element("fire", "on"));
        stateTwo.addElement(new Element("water", "off"));

        assert stateOne.isEqual(stateTwo);
    }

    @Test
    public void notEqualsStatesWithOneElement() {
        State stateOne = new State();
        stateOne.addElement(new Element("element", "state"));

        State stateTwo = new State();
        stateTwo.addElement(new Element("element", "notState"));

        assert !stateOne.isEqual(stateTwo);
    }

    @Test
    public void notEqualsStatesWithThreeElements() {
        State stateOne = new State();
        stateOne.addElement(new Element("element", "state"));
        stateOne.addElement(new Element("fire", "on"));
        stateOne.addElement(new Element("water", "on"));

        State stateTwo = new State();
        stateTwo.addElement(new Element("element", "state"));
        stateTwo.addElement(new Element("fire", "on"));
        stateTwo.addElement(new Element("water", "off"));

        assert !stateOne.isEqual(stateTwo);
    }

    @Test
    public void notEqualsStatesWithDifferentElements() {
        State stateOne = new State();
        stateOne.addElement(new Element("element", "state"));
        stateOne.addElement(new Element("fire", "on"));
        stateOne.addElement(new Element("gas", "on"));

        State stateTwo = new State();
        stateTwo.addElement(new Element("element", "state"));
        stateTwo.addElement(new Element("fire", "on"));
        stateTwo.addElement(new Element("water", "on"));

        assert !stateOne.isEqual(stateTwo);
    }

    @Test
    public void notEqualsStatesWithDifferentAmountOfElements() {
        State stateOne = new State();
        stateOne.addElement(new Element("element", "state"));
        stateOne.addElement(new Element("fire", "on"));
        stateOne.addElement(new Element("water", "on"));

        State stateTwo = new State();
        stateTwo.addElement(new Element("element", "state"));
        stateTwo.addElement(new Element("fire", "on"));
        stateTwo.addElement(new Element("water", "on"));
        stateTwo.addElement(new Element("gas", "on"));

        assert !stateOne.isEqual(stateTwo);
    }
}
