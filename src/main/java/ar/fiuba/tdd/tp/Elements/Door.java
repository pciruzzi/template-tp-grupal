package ar.fiuba.tdd.tp.Elements;

import ar.fiuba.tdd.tp.Actions.Open;

/**
 * Created by panchoubuntu on 21/04/16.
 */
public class Door extends Element {

    public void doIt(Open open){
        System.out.println("Hice la accion OPEN en DOOR");
    }

}
