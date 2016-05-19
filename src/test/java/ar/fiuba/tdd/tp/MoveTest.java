package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.Move;
import org.junit.Test;

import java.util.Map;

public class MoveTest {

    @Test
    public void testLookAroundListElements() {
        ICommand move = new Move("move");
        Element chest = new Element("chest");
        Element hole = new Element("hole");
        Element sword = new Element("sword");

        hole.addElement(sword);
        sword.addCommand(move);
        sword.doCommand("move",hole,chest);

        Map<String,Element> chestElementMap = chest.getElementMap();
        Map<String,Element> holeElementMap  = hole.getElementMap();
        assert ( chestElementMap.containsKey(sword.getName()) );
        assert ( !holeElementMap.containsKey(sword.getName()) );
    }
}
