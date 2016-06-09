package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.FalseExpression;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

@SuppressWarnings("CPD-START")

public class OpenDoor2Configuration implements GameBuilder {

    private Game game;
    private Element roomOne;
    private Element roomTwo;
    private Element doorOneTwo;
    private Element doorTwoOne;
    private Element player;
    private IInterpreter winCondition;
    private Element key;
    private Element box;
    private ICommand question;

    private void setAllVariablesOfOpenDoor() {
        game = new Game("Open Door 2");
        game.setDescription("Is this real life? Is this just fantasy? Look for items in the room, try to leave this torture.");

        roomOne = new Element("roomOne");
        doorOneTwo = new Element("door");
        doorOneTwo.setState(true);
        question = new Question("ask");
        doorTwoOne = new Element("door");
        doorTwoOne.setState(true);
        player = new Element("player");
        roomTwo = new Element("roomTwo");
        key = new Element("key");
        key.setState(false);
        box = new Element("box");
        box.addElement(key);
        box.setState(true);
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

    private void setWinCondition() {
        ArrayList<String> winConditionsArray = new ArrayList<>();
        winConditionsArray.add("player");
        winCondition = new ContainsElements(roomTwo, winConditionsArray);
    }

    private void setDoorTwoOneRequirements(Game game) {
        ICommand openDoorTwoOne = new MovePlayerTo(game, "open");
        doorTwoOne.addCommand(openDoorTwoOne);
        doorTwoOne.addCommand(question);
    }

    private void setDoorOneTwoRequirements(Game game, ArrayList<String> doorRequirements) {
        IInterpreter doorCondition = new ContainsElements(player, doorRequirements);
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

        ArrayList<String> doorRequirements = new ArrayList<String>();
        doorRequirements.add("key");

        setDoorOneTwoRequirements(game, doorRequirements);
        setDoorTwoOneRequirements(game);
        setWinCondition();

        setElementsInRoomOneAndTwo();
        setHelpAndExitCommand();

        game.setInitialPosition(roomOne);
        game.setWinInterpreter(winCondition);

        IInterpreter loseInterpreter = new FalseExpression();
        game.setLosingInterpreter(loseInterpreter);

        return game;
    }

    private void setElementsInRoomOneAndTwo() {
        configureBox();
        roomOne.addElement(box);
        roomOne.addElement(doorOneTwo);
        roomTwo.addElement(doorTwoOne);
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

    private void configureBox() {
        ICommand open = new ChangeVisibility("open", true, game);
        ICommand close = new ChangeVisibility("close", false, game);
        box.addCommand(open);
        box.addCommand(close);
        box.addCommand(question);
    }
}
