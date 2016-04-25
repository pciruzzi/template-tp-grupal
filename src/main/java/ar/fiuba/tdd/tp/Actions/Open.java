package ar.fiuba.tdd.tp.Actions;

import ar.fiuba.tdd.tp.Elements.Element;

/**
 * Created by panchoubuntu on 21/04/16.
 */
public class Open extends Action {

    @Override
    public void doAction(Element element){
        element.doIt(this);
    }
}
