package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

public class EvilThingConfiguration implements GameBuilder{

    public Game build() {

        Game game = new Game("Evil Thing");

        // Creo los elementos
        Element key = new Element("key");
//        Element door = new Element("door", true);
        Element roomOne = new Element("roomOne");


        // Combino los elementos

        // Agrego las acciones

        // Creo las formas de ganar
        ArrayList<String> winArray = new ArrayList<String>();
        winArray.add(key.getName());

        IInterpreter winInterpreter = new ContainsElements(roomOne,winArray);

        // Seteo las formas de ganar
        game.setWinInterpreter(winInterpreter);

        // Agrego la posicion del player
        game.setPlayerPosition(roomOne);

        return game;
    }
}
