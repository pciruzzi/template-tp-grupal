package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gg on 5/10/2016.
 */
public class LookArround extends ICommand {


    public LookArround(Game game) {
       // name = "look arround";
        //this.game = game;
    }

    public String doAction(ElementTwo element) {

        List<ElementTwo> elementList = element.getElementList();
        String returnMessage = "";

        for (ElementTwo elem: elementList) {
            //returnMessage = returnMessage + elem.getName();
        }
        return returnMessage;
    }
}
