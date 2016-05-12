package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.OrExpression;

import java.util.ArrayList;

public class EvilThingConfiguration implements GameBuilder{

    public Game build() {

        Game game = new Game("Evil Thing");

        // Creo los elementos
        ElementTwo key = new ElementTwo("key");
//        ElementTwo door = new ElementTwo("door", true);
        ElementTwo roomOne = new ElementTwo("roomOne");


        // Combino los elementos

        // Agrego las acciones

        // Creo las formas de ganar
        ArrayList<String> winArray = new ArrayList<>();
        winArray.add(key.getName());

        IInterpreter winInterpreter = new ContainsElements(roomOne,winArray);

        // Seteo las formas de ganar
        game.setWinInterpreter(winInterpreter);

        // Agrego la posicion del player
        game.setPlayerPosition(roomOne);

        return game;
    }
}
