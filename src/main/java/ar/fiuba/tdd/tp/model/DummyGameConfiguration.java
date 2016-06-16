package ar.fiuba.tdd.tp.model;


import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.State;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.FalseExpression;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CPD-START")

public class DummyGameConfiguration implements GameBuilder{

    private Game game;

    private Element room;
    private Element roomTwo;

    private Player player;
    private List<Player> players;

    private Element chest;
    private Element sword;
    private Element key;

    private Element winElement;
    private Element loseElement;

    private Element doorToRoomTwo;
    private Element doorToRoomOne;


    public Game build() {
        game = new Game("Dummy game");
        game.setDescription("This is a dummy game");

        players = new ArrayList<>();
        player = new Player(0);
        createWinLoseCondition();
        players.add(player);
        game.setMaxPlayers(1);

        createRoomOne();
        createFinalRoom();
        createElementsCommands();

        game.setInitialPosition(room);
        game.setPlayers(players);
        return game;
    }

    private void createWinLoseCondition() {
        ArrayList<String> winArray = new ArrayList<>();
        ArrayList<String> loseArray = new ArrayList<>();

        winArray.add("goldenStick");
        loseArray.add("woodenStick");

        IInterpreter winInterpreter = new ContainsElements(player, winArray);
        IInterpreter loseInterpreter = new ContainsElements(player, loseArray);

        player.setLosingInterpreter(loseInterpreter);
        player.setWinInterpreter(winInterpreter);
    }

    private void createElementsCommands() {
        ICommand pick = new MoveToPlayer("pick", game);
        ICommand open = new ChangeVisibility("open", true, game);
        ICommand lookAround = new LookAround("look around", game);
        ICommand drop = new DropOnPosition("drop", game);

        ArrayList<String> doorConditionArray = new ArrayList<>();
        doorConditionArray.add("key");

        IInterpreter doorCondition = new ContainsElements(player,doorConditionArray);

        doorCondition.setFailMessage("The door is locked.");

        ICommand openDoor = new MovePlayerTo(game, doorCondition, "open");
        ICommand question = new Question("ask");

        assignCommands(pick, open, lookAround, drop, openDoor, question);
    }

    private void assignCommands(ICommand pick, ICommand open, ICommand lookAround, ICommand drop, ICommand openDoor, ICommand question) {
        doorToRoomOne.setObjectiveElement(room);
        doorToRoomTwo.setObjectiveElement(roomTwo);

        doorToRoomTwo.addCommand(openDoor);
        doorToRoomOne.addCommand(openDoor);

        room.addCommand(lookAround);

        chest.addCommand(open);
        chest.addCommand(question);

        sword.addCommand(pick);
        sword.addCommand(drop);
        key.addCommand(pick);
        key.addCommand(drop);
        winElement.addCommand(pick);
        loseElement.addCommand(pick);

        roomTwo.addCommand(lookAround);
    }

    private void createFinalRoom() {
        roomTwo = new Element("finalRoom");
        doorToRoomOne = new Element("door");
        doorToRoomOne.changeState("visible", true);
        roomTwo.addElement(doorToRoomOne);
        winElement = new Element("goldenStick");
        loseElement = new Element("woodenStick");
        winElement.changeState("visible", true);
        loseElement.changeState("visible", true);
        roomTwo.addElement(winElement);
        roomTwo.addElement(loseElement);
    }

    @SuppressWarnings("CPD-END")

    private void createRoomOne() {
        room = new Element("room");
        doorToRoomTwo = new Element("door");
        doorToRoomTwo.changeState("visible", true);
        room.addElement(doorToRoomTwo);
        sword = new Element("sword");
        key = new Element("key");
        chest = new Element("chest");
        sword.changeState("visible", true);
        chest.changeState("visible", true);
        chest.addElement(key);
        room.addElement(sword);
        room.addElement(chest);
    }
}
