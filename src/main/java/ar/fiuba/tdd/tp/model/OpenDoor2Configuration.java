package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

public class OpenDoor2Configuration implements  GameBuilder {

    private Element box;
    private Element key;

    private Game game;

    private Element doorOneTwo;
    private Element doorTwoOne;

    private Element player;

    private Element roomOne;
    private Element roomTwo;

    private IInterpreter winCondition;

    private void setAllVariablesOfOpenDoor() {
        game = new Game("Open Door 2");
        player = new Element("player");
        key = new Element("key");
        key.setState(false);
        box = new Element("box");
        box.addElement(key);
        box.setState(true);

        roomOne = new Element("roomOne");
        roomTwo = new Element("roomTwo");
        doorOneTwo = new Element("door");
        doorOneTwo.setState(true);
        doorTwoOne = new Element("door");
        doorTwoOne.setState(true);
    }

    private void configureKey(Game game) {
        ICommand pick = new MoveToPlayer("pick", game);
        ICommand drop = new DropOnPosition("drop", game);
        key.addCommand(pick);
        key.addCommand(drop);
    }

    public Game build() {
        setAllVariablesOfOpenDoor();
        doorOneTwo.setObjectiveElement(roomTwo);
        doorTwoOne.setObjectiveElement(roomOne);

        game.setPlayer(player);
        configureLookAround(game);

        configureKey(game);

        configureBox();

        ArrayList<String> doorRequirements = new ArrayList<String>();
        doorRequirements.add("key");

        setDoorOneTwoRequirements(game, doorRequirements);
        setDoorTwoOneRequirements(game);
        setWinCondition();

        roomOne.addElement(box);
        roomOne.addElement(doorOneTwo);
        roomOne.addElement(player);

        roomTwo.addElement(doorTwoOne);

        game.setPlayerPosition(roomOne);
        game.setWinInterpreter(winCondition);

        return game;
    }

    private void setWinCondition() {
        ArrayList<String> winConditionsArray = new ArrayList<>();
        winConditionsArray.add("player");
        winCondition = new ContainsElements(roomTwo, winConditionsArray);
    }

    private void setDoorTwoOneRequirements(Game game) {
        ICommand openDoorTwoOne = new MovePlayerTo(game, "open");
        doorTwoOne.addCommand(openDoorTwoOne);
    }

    private void configureLookAround(Game game) {
        ICommand lookAround = new LookAround("look around", game);
        roomOne.addCommand(lookAround);
        roomTwo.addCommand(lookAround);
    }

    private void configureBox() {
        ICommand open = new ChangeVisibility("open", true);
        ICommand close = new ChangeVisibility("close", false);
        box.addCommand(open);
        box.addCommand(close);
    }

    private void setDoorOneTwoRequirements(Game game, ArrayList<String> doorRequirements) {
        IInterpreter doorCondition = new ContainsElements(player, doorRequirements);
        ICommand openDoorOneTwo = new MovePlayerTo(game, doorCondition, "open");
        doorOneTwo.addCommand(openDoorOneTwo);
    }
}
