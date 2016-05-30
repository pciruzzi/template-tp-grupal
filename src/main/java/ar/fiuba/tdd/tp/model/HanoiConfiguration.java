package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.FalseExpression;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.OrExpression;

import java.util.ArrayList;

@SuppressWarnings("CPD-START")

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
        game.setDescription("3 stacks with n disks, try moving the disks around and see what happens");

        createElements();
        addActions();

        // Creo las formas de ganar
        ArrayList<String> winArray = new ArrayList<>();
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

        IInterpreter loseInterpreter = new FalseExpression();
        game.setLosingInterpreter(loseInterpreter);

        setHelpAndExitCommand();

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
        moveSmallest.correctMovementMessage("You moved the disk!");
        moveSmallest.incorrectMovementMessage("You can't move a bigger disk over a smaller one!");
        moveSmallest.auxiliarMessage("The stack from where you are trying to move is empty");

        stackOne.addCommand(moveSmallest);
        stackTwo.addCommand(moveSmallest);
        stackThree.addCommand(moveSmallest);

        ICommand checkTop = new Check("check top",(Element e1, Element e2) -> e1.getSize() - e2.getSize());
        checkTop.correctMovementMessage("The size of the top is ");
        checkTop.incorrectMovementMessage("The stack you are trying to check is empty.");

        stackOne.addCommand(checkTop);
        stackTwo.addCommand(checkTop);
        stackThree.addCommand(checkTop);

        ICommand lookAround = new LookAround("look around", game);
        room.addCommand(lookAround);

        addQuestions();
    }

    private void setHelpAndExitCommand() {
        ICommand exit = new Exit();
        ICommand help = new Help("help", game);

        room.addCommand(help);
        room.addCommand(exit);
    }

    @SuppressWarnings("CPD-END")

    private void addQuestions() {
        ICommand question = new Question("ask");
        stackOne.addCommand(question);
        stackTwo.addCommand(question);
        stackThree.addCommand(question);
    }
}
