package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.logic.FalseExpression;
import ar.fiuba.tdd.tp.interpreter.logic.OrExpression;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.ContainsElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("CPD-START")

public class HanoiConfiguration implements GameBuilder{

    private Element diskOne;
    private Element diskTwo;
    private Element diskThree;

    private Element stackOne;
    private Element stackTwo;
    private Element stackThree;

    private Element room;

    private List<Player> players;
    private int maxPlayers;

    private Game game;

    public Game build() {
        game = new Game("Hanoi Towers");
        game.setDescription("3 stacks with n disks, try moving the disks around and see what happens");
        setPlayers();

        createElements();
        addPlayers();
        addActions();
        setWinAndLoseInterpreter();

        game.setInitialPosition(room);
        setHelpAndExitCommand();
        return game;
    }

    private void setWinAndLoseInterpreter() {
        // Creo las formas de ganar
        ArrayList<String> winArray = new ArrayList<>();
        winArray.add(diskOne.getName());
        winArray.add(diskTwo.getName());
        winArray.add(diskThree.getName());

        // Todos los discos estan en el stackThree o stackTwo
        IInterpreter winInterpreterStackTwo = new ContainsElements(Optional.of(stackTwo),winArray);
        IInterpreter winInterpreterStackThree = new ContainsElements(Optional.of(stackThree),winArray);

        // Combino las formas de ganar
        IInterpreter winingWays = new OrExpression(winInterpreterStackTwo, winInterpreterStackThree);
        IInterpreter loseInterpreter = new FalseExpression();

        for (Player player : players) {
            player.setWinInterpreter(winingWays);
            player.setLosingInterpreter(loseInterpreter);
        }
    }

    private void setPlayers() {
        maxPlayers = 1;
        game.setMaxPlayers(maxPlayers);
        players = new ArrayList<>();
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

        stackOne.changeState("visible", true);
        stackTwo.changeState("visible", true);
        stackThree.changeState("visible", true);

        // Combino los elementos
        stackOne.addElement(diskOne);
        stackOne.addElement(diskTwo);
        stackOne.addElement(diskThree);

        room = new Element("room");
        room.addElement(stackOne);
        room.addElement(stackTwo);
        room.addElement(stackThree);
    }

    private void addPlayers() {
        for (int i = 0; i < maxPlayers; i++) {
            Player newPlayer = new Player(i);
            players.add(newPlayer);
        }
        game.setPlayers(players);
    }

    private void addActions() {
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
        ICommand exit = new Exit(game);
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
