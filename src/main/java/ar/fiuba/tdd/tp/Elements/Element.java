package ar.fiuba.tdd.tp.Elements;

import ar.fiuba.tdd.tp.Actions.Pick;
import ar.fiuba.tdd.tp.Actions.Open;

/**
 * Created by panchoubuntu on 21/04/16.
 */
public class Element {

    public void doIt(Pick pick){
        System.out.println("No me puedo levantar");
    }

    public void doIt(Open open){
        System.out.println("No me puedo abrir");
    }
}
