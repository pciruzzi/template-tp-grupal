package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

public class TreasureQuestConfiguration implements GameBuilder {

    public Game build() {

        Game game = new Game("Treasure Quest");

//        // Creo los elementos
//        ElementTwo key = new ElementTwo("key", true);
//        ElementTwo door = new ElementTwo("door", true);
//        ElementTwo roomOne = new ElementTwo("roomOne", true);
//
//
//        // Combino los elementos
//
//        // Agrego las acciones
//
//        // Creo las formas de ganar
//        ArrayList<String> winArray = new ArrayList<>();
//        winArray.add(key.getName());
//
//        IInterpreter winInterpreter = new ContainsElements(roomOne,winArray);
//
//        // Seteo las formas de ganar
//        game.setWinInterpreter(winInterpreter);
//
//        // Agrego la posicion del player
//        game.setPlayerPosition(roomOne);
        return game;
    }
}
