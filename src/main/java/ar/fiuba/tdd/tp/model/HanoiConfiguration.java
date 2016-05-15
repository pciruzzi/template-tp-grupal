package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.Check;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.LookAround;
import ar.fiuba.tdd.tp.icommand.MoveWithComparator;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.OrExpression;

import java.util.ArrayList;

public class HanoiConfiguration implements GameBuilder{

    private Element diskOne;
    private Element diskTwo;
    private Element diskThree;

    private Element stackOne;
    private Element stackTwo;
    private Element stackThree;

    private Element room;

    private Game game;

    public Game build() {

        // Creo el juego
        game = new Game("Hanoi Towers");

        createElements();
        addActions();

        // Creo las formas de ganar
        ArrayList<String> winArray = new ArrayList<String>();
        winArray.add(diskOne.getName());
        winArray.add(diskTwo.getName());
        winArray.add(diskThree.getName());

        // Todos los discos estan en el stackOne o stackTwo
        IInterpreter winInterpreterStackTwo = new ContainsElements(stackTwo,winArray);
        IInterpreter winInterpreterStackThree = new ContainsElements(stackThree,winArray);

        // Combino las formas de ganar
        IInterpreter winingWays = new OrExpression(winInterpreterStackTwo, winInterpreterStackThree);

        Element player = new Element("player");
        game.setPlayer(player);

        // Seteo las formas de ganar
        game.setWinInterpreter(winingWays);

        // Agrego la posicion del player, esto esta mal
        game.setPlayerPosition(room);

        return game;
    }

    private void createElements() {

        // Creo los elementos
        diskOne = new Element("diskOne");
        diskTwo = new Element("diskTwo");
        diskThree = new Element("diskThree");

        // Le pongo el tamanio a los discos
        diskOne.setSize(1);
        diskTwo.setSize(2);
        diskThree.setSize(3);

        stackOne = new Element("stackOne");
        stackTwo = new Element("stackTwo");
        stackThree = new Element("stackThree");

        stackOne.setState(true);
        stackTwo.setState(true);
        stackThree.setState(true);

        // Combino los elementos
        stackOne.addElement(diskOne);
        stackOne.addElement(diskTwo);
        stackOne.addElement(diskThree);

        room = new Element("room");
        room.addElement(stackOne);
        room.addElement(stackTwo);
        room.addElement(stackThree);
    }

    private void addActions() {

        // Agrego las acciones
        ICommand moveSmallest = new MoveWithComparator("move top", (Element e1, Element e2) -> e1.getSize() - e2.getSize());

        stackOne.addCommand(moveSmallest);
        stackTwo.addCommand(moveSmallest);
        stackThree.addCommand(moveSmallest);

        ICommand checkTop = new Check("check top",(Element e1, Element e2) -> e1.getSize() - e2.getSize());

        stackOne.addCommand(checkTop);
        stackTwo.addCommand(checkTop);
        stackThree.addCommand(checkTop);

        ICommand lookAround = new LookAround("look around", game);
        room.addCommand(lookAround);

    }
}
