package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;

import java.util.ArrayList;

@SuppressWarnings("CPD-START")

public class EvilThingConfiguration implements GameBuilder {

    private Element cursedObject;
    private Element roomOne;
    private Element roomTwo;
    private Element roomThree;
    private Element doorOneTwo;
    private Element doorTwoThree;
    private Element doorTwoOne;
    private Player player;
    private Element thief;

    private Game game;

    public Game build() {
        game = new Game("Evil Thing");
        game.setDescription("Never feel sad if you are removed of a valuable item, sometiemes is the only way out.");
        game.setMaxPlayers(4);

        createGameElements();
        createGameActions();
        createGameContainsElements();

        // Creo y seteo las formas de ganar.
        ArrayList<String> winConditionsArray = new ArrayList<>();
        winConditionsArray.add("player");
        IInterpreter winCondition = new ContainsElements(roomThree, winConditionsArray);
        game.setWinInterpreter(winCondition);
        IInterpreter loseInterpreter = new FalseExpression();
        game.setLosingInterpreter(loseInterpreter);

        // Agrego la posicion inicial del player.
        game.setInitialPosition(roomOne);

        setHelpAndExitCommand();

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
        //Habitacion 2
        roomTwo.addElement(doorTwoOne);
        roomTwo.addElement(thief);
        roomTwo.addElement(doorTwoThree);
    }

    private void createGameActions() {
        // Agrego las acciones de cada elemento
        ICommand question = new Question("ask");
        //Ladron
        ICommand talkTo = new MoveFromPlayer("talk to", game, "key");
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
        ArrayList<String> doorRequirements = new ArrayList<>();
        doorRequirements.add("key");
        IInterpreter doorCondition = new ContainsElements(player, doorRequirements);
        doorCondition.setFailMessage("Ey! You can't do that! The door is locked");
        ICommand openDoorOneTwo = new MovePlayerTo(game, doorCondition, "open");
        doorOneTwo.addCommand(openDoorOneTwo);
        ICommand openDoorTwoOne = new MovePlayerTo(game, "open");
        doorTwoOne.addCommand(openDoorTwoOne);
        player.addElement(new Element("key"));
        IInterpreter door2Condition = new DoesNotContainElements(player, doorRequirements);
        door2Condition.setFailMessage("Ey! You can't do that! The otherDoor is locked");
        ICommand openDoorTwoThree = new MovePlayerTo(game, door2Condition, "open");
        doorTwoThree.addCommand(openDoorTwoThree);
        doorOneTwo.addCommand(question);
        doorTwoOne.addCommand(question);
        doorTwoThree.addCommand(question);
    }

    private void setHelpAndExitCommand() {
        ICommand exit = new Exit(game);
        ICommand help = new Help("help", game);

        roomOne.addCommand(help);
        roomOne.addCommand(exit);
        roomTwo.addCommand(help);
        roomTwo.addCommand(exit);
        roomThree.addCommand(help);
        roomThree.addCommand(exit);
    }

    @SuppressWarnings("CPD-END")

    private void createGameElements() {
        // Creo los elementos
        roomTwo = new Element("roomTwo");
        cursedObject = new Element("key");
        cursedObject.setState(true);
        doorOneTwo = new Element("door");
        doorOneTwo.setState(true);
        roomThree = new Element("roomThree");
        doorTwoOne = new Element("door");
        doorTwoOne.setState(true);
        doorTwoThree = new Element("otherDoor");
        doorTwoThree.setState(true);
        roomOne = new Element("roomOne");
        player = new Player(0);
        thief = new Element("thief");
        thief.setState(true);
    }
}
