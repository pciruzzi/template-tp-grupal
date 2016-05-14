package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.Move;
import ar.fiuba.tdd.tp.model.Game;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MoveTest {

    @Test
    public void testLookArroundListElements() {

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
