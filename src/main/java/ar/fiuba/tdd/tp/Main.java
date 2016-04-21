package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.Actions.Action;
import ar.fiuba.tdd.tp.Actions.Open;
import ar.fiuba.tdd.tp.Actions.Pick;
import ar.fiuba.tdd.tp.Elements.Stick;
import ar.fiuba.tdd.tp.Elements.Chest;
import ar.fiuba.tdd.tp.Elements.Door;
import ar.fiuba.tdd.tp.Elements.Element;

public class Main {
    public static void main(String[] args) {

        System.out.println("This is just a tp project");
        Element door = new Door();
        Element chest = new Chest();
        Element stick = new Stick();

        Action open = new Open();
        Action pick = new Pick();

        pick.doAction(stick);
        open.doAction(stick);

        pick.doAction(door);
        open.doAction(door);

        pick.doAction(chest);
        open.doAction(chest);

    }
}
