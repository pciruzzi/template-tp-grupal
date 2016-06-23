package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.interpreter.logic.FalseExpression;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.ContainsPlayer;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.DoesNotContainElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("CPD-START")

public class EvilThingConfiguration implements GameBuilder {

    private Element cursedObject;
    private Element roomOne;
    private Element roomTwo;
    private Element roomThree;
    private Element doorOneTwo;
    private Element doorTwoThree;
    private Element doorTwoOne;
    private Element thief;
    private Optional<Element> playerGenerico;
    private List<Player> players;
    private int maxPlayers;

    private Game game;

    public Game build() {
        game = new Game("Evil Thing");
        game.setDescription("Never feel sad if you are removed of a valuable item, sometimes is the only way out.");
        setPlayers();

        createGameElements();
        createGameActions();
        createGameContainsElements();
        setWinAndLoseInterpreter();

        // Agrego la posicion inicial del player.
        game.setInitialPosition(roomOne);
        setHelpAndExitCommand();

        doorOneTwo.setObjectiveElement(roomTwo);
        doorTwoOne.setObjectiveElement(roomOne);
        doorTwoThree.setObjectiveElement(roomThree);

        return game;
    }

    private void setWinAndLoseInterpreter() {
        for (Player player : players) {
            ArrayList<String> winConditionsArray = new ArrayList<>();
            winConditionsArray.add("player");
            IInterpreter winCondition = new ContainsPlayer(Optional.of(roomThree), winConditionsArray);
            player.setWinInterpreter(winCondition);

            IInterpreter loseInterpreter = new FalseExpression();
            player.setLosingInterpreter(loseInterpreter);
        }
    }

    private void setPlayers() {
        maxPlayers = 4;
        game.setMaxPlayers(maxPlayers);
        players = new ArrayList<>();
    }

    private void createGameContainsElements() {
        //Agrego los elementos en sus contenedores
        roomOne.addElement(cursedObject);
        roomOne.addElement(doorOneTwo);

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
        IInterpreter doorCondition = new ContainsElements(playerGenerico, doorRequirements);
        doorCondition.setFailMessage("Ey! You can't do that! The door is locked");
        ICommand openDoorOneTwo = new MovePlayerTo(game, doorCondition, "open");
        doorOneTwo.addCommand(openDoorOneTwo);
        ICommand openDoorTwoOne = new MovePlayerTo(game, "open");
        doorTwoOne.addCommand(openDoorTwoOne);
        //playerGenerico.addElement(new Element("key"));
        IInterpreter door2Condition = new DoesNotContainElements(playerGenerico, doorRequirements);
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
        cursedObject.changeState("visible", true);
        doorOneTwo = new Element("door");
        doorOneTwo.changeState("visible",true);
        roomThree = new Element("roomThree");
        doorTwoOne = new Element("door");
        doorTwoOne.changeState("visible",true);
        doorTwoThree = new Element("otherDoor");
        doorTwoThree.changeState("visible",true);
        roomOne = new Element("roomOne");
        playerGenerico = Optional.empty();
        for (int i = 0; i < maxPlayers; i++) {
            Player newPlayer = new Player(i);
            players.add(newPlayer);
        }
        game.setPlayers(players);
        thief = new Element("thief");
        thief.changeState("visible", true);
    }
}
