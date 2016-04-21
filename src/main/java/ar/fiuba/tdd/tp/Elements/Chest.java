package ar.fiuba.tdd.tp.Elements;

import ar.fiuba.tdd.tp.Actions.Open;
import ar.fiuba.tdd.tp.Actions.Pick;

/**
 * Created by panchoubuntu on 21/04/16.
 */
public class Chest extends Element {

    public void doIt(Open open){
        System.out.println("Hice la accion OPEN en Chest");
    }

    public void doIt(Pick pick){
        System.out.println("Hice la accion PICK en Chest");
    }

}
