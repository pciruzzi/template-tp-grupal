package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.ContainsPlayer;
import ar.fiuba.tdd.tp.interpreter.FalseExpression;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CPD-START")

public class OpenDoorConfiguration implements GameBuilder {

    private Game game;
    private Element roomOne;
    private Element roomTwo;
    private Element doorOneTwo;
    private Element doorTwoOne;
    private Player playerGenerico;
    private Element key;
    private ICommand question;

    private List<Player> players;
    private int maxPlayers;

    private void setAllVariablesOfOpenDoor() {
        game = new Game("Open Door");
        game.setDescription("Oh dear... you are trapped inside a room, don't worry, there's always a way out.");
        maxPlayers = 4;
        game.setMaxPlayers(maxPlayers);
        players = new ArrayList<>();

        roomOne = new Element("roomOne");
        doorOneTwo = new Element("door");
        doorOneTwo.changeState("visible", true);
        question = new Question("ask");
        doorTwoOne = new Element("door");
        doorTwoOne.changeState("visible", true);
        playerGenerico = new Player(-1);
        for (int i = 0; i < maxPlayers; i++) {
            Player newPlayer = new Player(i);
            players.add(newPlayer);
        }
        game.setPlayers(players);
        roomTwo = new Element("roomTwo");
        key = new Element("key");
        key.changeState("visible", true);
    }

    private void configureLookAround(Game game) {
        ICommand lookAround = new LookAround("look around", game);
        roomOne.addCommand(lookAround);
        roomTwo.addCommand(lookAround);
    }

    private void configureKey(Game game) {
        ICommand pick = new MoveToPlayer("pick", game);
        ICommand drop = new DropOnPosition("drop", game);
        key.addCommand(pick);
        key.addCommand(drop);
        key.addCommand(question);
    }

    private void setDoorTwoOneRequirements(Game game) {
        ICommand openDoorTwoOne = new MovePlayerTo(game, "open");
        doorTwoOne.addCommand(openDoorTwoOne);
        doorTwoOne.addCommand(question);
    }

    private void setDoorOneTwoRequirements(Game game, ArrayList<String> doorRequirements) {
        IInterpreter doorCondition = new ContainsElements(playerGenerico, doorRequirements);
        doorCondition.setFailMessage("Ey! You can't do that! The door is locked");
        ICommand openDoorOneTwo = new MovePlayerTo(game, doorCondition, "open");
        doorOneTwo.addCommand(openDoorOneTwo);
        doorOneTwo.addCommand(question);
    }

    public Game build() {
        setAllVariablesOfOpenDoor();
        doorOneTwo.setObjectiveElement(roomTwo);
        doorTwoOne.setObjectiveElement(roomOne);

        configureLookAround(game);
        configureKey(game);

        ArrayList<String> doorRequirements = new ArrayList<>();
        doorRequirements.add("key");

        setDoorOneTwoRequirements(game, doorRequirements);
        setDoorTwoOneRequirements(game);

        setElementsInRoomOneAndTwo();
        setHelpAndExitCommand();

        game.setInitialPosition(roomOne);
        setWinAndLoseInterpreter();

        return game;
    }

    private void setWinAndLoseInterpreter() {
        ArrayList<String> winConditionsArray = new ArrayList<>();
        winConditionsArray.add("player");
        IInterpreter winCondition = new ContainsPlayer(roomTwo, winConditionsArray);

        IInterpreter loseInterpreter = new FalseExpression();

        for (Player player : players) {
            player.setWinInterpreter(winCondition);
            player.setLosingInterpreter(loseInterpreter);
        }
    }

    private void setHelpAndExitCommand() {
        ICommand exit = new Exit(game);
        ICommand help = new Help("help", game);

        roomOne.addCommand(help);
        roomOne.addCommand(exit);
        roomTwo.addCommand(help);
        roomTwo.addCommand(exit);
    }

    @SuppressWarnings("CPD-END")

    private void setElementsInRoomOneAndTwo() {
        roomOne.addElement(key);
        roomOne.addElement(doorOneTwo);
        roomTwo.addElement(doorTwoOne);
    }
}
