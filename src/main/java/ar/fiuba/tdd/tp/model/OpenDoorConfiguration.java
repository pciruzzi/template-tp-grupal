package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

public class OpenDoorConfiguration implements GameBuilder {

    private Game game;
    private ElementTwo roomOne;
    private ElementTwo roomTwo;
    private ElementTwo doorOneTwo;
    private ElementTwo doorTwoOne;
    private ElementTwo player;
    private IInterpreter winCondition;
    private ElementTwo key;

    private void setAllVariablesOfOpenDoor() {
        game = new Game("Open Door");
        roomOne = new ElementTwo("roomOne");
        roomTwo = new ElementTwo("roomTwo");
        doorOneTwo = new ElementTwo("door");
        doorOneTwo.setState(true);
        doorTwoOne = new ElementTwo("door");
        player = new ElementTwo("player");
        key = new ElementTwo("key");
        key.setState(true);
    }

    private void configureLookAround(Game game) {
        ICommand lookAround = new LookAround("look around", game);
        roomOne.addCommand(lookAround);
    }

    private void configureKey(Game game) {
        ICommand pick = new MoveToPlayer("pick", game);
        key.addCommand(pick);
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

    private void setDoorOneTwoRequirements(Game game, ArrayList<String> doorRequirements) {
        IInterpreter doorCondition = new ContainsElements(player, doorRequirements);
        ICommand openDoorOneTwo = new MovePlayerTo(game, doorCondition, "open");
        doorOneTwo.addCommand(openDoorOneTwo);
    }

    public Game build() {

        setAllVariablesOfOpenDoor();
        doorOneTwo.setObjetiveElement(roomTwo);
        doorTwoOne.setObjetiveElement(roomOne);

        game.setPlayer(player);
        configureLookAround(game);

        configureKey(game);

        ArrayList<String> doorRequirements = new ArrayList<>();
        doorRequirements.add("key");

        setDoorOneTwoRequirements(game, doorRequirements);
        setDoorTwoOneRequirements(game);
        setWinCondition();

        roomOne.addElement(key);
        roomOne.addElement(doorOneTwo);
        roomOne.addElement(player);

        roomTwo.addElement(doorTwoOne);

        game.setPlayerPosition(roomOne);
        game.setWinInterpreter(winCondition);

        return game;
    }

}
