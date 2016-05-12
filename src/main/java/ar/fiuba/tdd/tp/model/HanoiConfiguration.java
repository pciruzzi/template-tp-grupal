package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.OrExpression;

import java.util.ArrayList;

public class HanoiConfiguration implements GameBuilder{

    public Game build() {

        // Creo los elementos
        ElementTwo diskOne = new ElementTwo("diskOne");
        ElementTwo diskTwo = new ElementTwo("diskTwo");
        ElementTwo diskThree = new ElementTwo("diskThree");

        // Le pongo el tamanio a los discos
//        diskOne.setSize(1);
//        diskTwo.setSize(2);
//        diskThree.setSize(3);

        ElementTwo stackOne = new ElementTwo("stackOne");
        ElementTwo stackTwo = new ElementTwo("stackTwo");
        ElementTwo stackThree = new ElementTwo("stackThree");

        // Combino los elementos
        stackOne.addElement(diskOne);
        stackOne.addElement(diskTwo);
        stackOne.addElement(diskThree);

        // Agrego las acciones

        // Creo las formas de ganar
        ArrayList<String> winArray = new ArrayList<>();
//        winArray.add(diskOne.getName());
//        winArray.add(diskTwo.getName());
//        winArray.add(diskThree.getName());

        // Todos los discos estan en el stackOne o stackTwo
        IInterpreter winInterpreterStackTwo = new ContainsElements(stackTwo,winArray);
        IInterpreter winInterpreterStackThree = new ContainsElements(stackThree,winArray);

        // Combino las formas de ganar
        IInterpreter winingWays = new OrExpression(winInterpreterStackTwo, winInterpreterStackThree);


        // Creo el juego
        Game game = new Game("Hanoi Towers");

        // Seteo las formas de ganar
        game.setWinInterpreter(winingWays);

        // Agrego la posicion del player, esto esta mal
        game.setPlayerPosition(stackOne);

        return game;

    }

}
