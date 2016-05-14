package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;

import java.util.ArrayList;

public class EvilThingConfiguration implements GameBuilder {

    private Element cursedObject;
    private Element roomOne;
    private Element roomTwo;
    private Element roomThree;
    private Element doorOneTwo;
    private Element doorTwoThree;
    private Element doorTwoOne;
    private Element player;
    private Element thief;

    private Game game;

    public Game build() {
        game = new Game("Evil Thing");

        createGameElements();

        createGameActions();

        createGameContainsElements();

        // Creo y seteo las formas de ganar.
        ArrayList<String> winConditionsArray = new ArrayList<>();
        winConditionsArray.add("player");
        IInterpreter winCondition = new ContainsElements(roomThree, winConditionsArray);
        game.setWinInterpreter(winCondition);

        // Agrego el player y su posicion.
        game.setPlayer(player);
        game.setPlayerPosition(roomOne);

        doorOneTwo.setObjectiveElement(roomTwo);
        doorTwoOne.setObjectiveElement(roomOne);
        doorTwoThree.setObjectiveElement(roomThree);

        return game;
    }

    private void createGameContainsElements() {
        //Agrego los elementos en sus contenedores
        //Habitacion 1
        roomOne.addElement(cursedObject);
        roomOne.addElement(doorOneTwo);
        roomOne.addElement(player);
        //Habitacion 2
        roomTwo.addElement(doorTwoOne);
        roomTwo.addElement(thief);
        roomTwo.addElement(doorTwoThree);
    }

    private void createGameActions() {
        // Agrego las acciones de cada elemento
        ICommand question = new Question("ask");
        //Ladron
        ICommand talkTo = new MoveFromPlayer("talk to", game);
        thief.addCommand(talkTo);
        thief.addCommand(question);
        //Objeto maldito
        ICommand pick = new MoveToPlayer("pick", game);
        cursedObject.addCommand(pick);
        cursedObject.addCommand(question);
        //Habitaciones
        ICommand lookAround = new LookAround("look around", game);
        roomOne.addCommand(lookAround);
        roomTwo.addCommand(lookAround);
        roomThree.addCommand(lookAround);

        createActionsForDoors(question);

    }

    private void createActionsForDoors(ICommand question) {
        //Puertas
        ArrayList<String> doorRequirements = new ArrayList<String>();
        doorRequirements.add("key");
        IInterpreter doorCondition = new ContainsElements(player, doorRequirements);
        ICommand openDoorOneTwo = new MovePlayerTo(game, doorCondition, "open");
        doorOneTwo.addCommand(openDoorOneTwo);
        ICommand openDoorTwoOne = new MovePlayerTo(game, "open");
        doorTwoOne.addCommand(openDoorTwoOne);
        IInterpreter door2Condition = new DoesNotContainElements(player, doorRequirements);
        ICommand openDoorTwoThree = new MovePlayerTo(game, door2Condition, "open");
        doorTwoThree.addCommand(openDoorTwoThree);
        doorOneTwo.addCommand(question);
        doorTwoOne.addCommand(question);
        doorTwoThree.addCommand(question);
    }

    private void createGameElements() {
        // Creo los elementos
        cursedObject = new Element("key");
        cursedObject.setState(true);
        roomOne = new Element("roomOne");
        roomTwo = new Element("roomTwo");
        roomThree = new Element("roomThree");
        doorOneTwo = new Element("door");
        doorOneTwo.setState(true);
        doorTwoOne = new Element("door");
        doorTwoOne.setState(true);
        doorTwoThree = new Element("otherDoor");
        doorTwoThree.setState(true);
        player = new Element("player");
        thief = new Element("thief");
        thief.setState(true);
    }
}
