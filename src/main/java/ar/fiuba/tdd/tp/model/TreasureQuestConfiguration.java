package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.State;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CPD-START")

public class TreasureQuestConfiguration implements GameBuilder {

    private Game game;
    private List<Player> players;
    private int maxPlayers;

    // Los cuartos
    private Element roomOne;
    private Element roomTwo;
    private Element roomThree;
    private Element roomFour;
    private Element roomFive;

    // Las puertas en ambos sentidos
    private Element doorOneTwo;
    private Element doorTwoOne;
    private Element doorTwoThree;
    private Element doorThreeTwo;
    private Element doorThreeFour;
    private Element doorFourThree;
    private Element doorFourFive;
    private Element doorFiveFour;
    private Element doorFiveOne;

    // Los contenedores
    private Element poisonBox;
    private Element treasureBox;
    private Element wardrobe;
    private Element chest;

    // Los objetos levantables
    private Element treasure;
    private Element key;
    private Element antidote;
    private Element pokemon;

    // Los elementos extra
    private Player playerGenerico;

    // Los ICommands genericos
    private ICommand question;
    private ICommand lookAround;
    private ICommand pick;
    private ICommand drop;
    private ICommand openDoor;
    private ICommand openContainer;

    public Game build() {
        playerGenerico = new Player(-1);
        playerGenerico.setCapacity(2);
        playerGenerico.addState(new State("poison", false, false));
        game = new Game("Treasure Quest");
        game.setDescription("Try to find the Irish treasure");
        setPlayers();

        createICommands();
        createElements();
        insertElements();
        createFinishingConditions();
        setHelpAndExitCommand();

        game.setInitialPosition(roomOne);
        return game;
    }

    private void setPlayers() {
        maxPlayers = 4;
        game.setMaxPlayers(maxPlayers);
        players = new ArrayList<>();
        for (int i = 0; i < maxPlayers; i++) {
            Player newPlayer = new Player(i);
            newPlayer.addState(new State("poison", false, false));
            newPlayer.setCapacity(2);
            players.add(newPlayer);
        }
        game.setPlayers(players);
    }

    private void createElements() {
        // Los cuartos
        roomOne   = new Element("roomOne");
        roomTwo   = new Element("roomTwo");
        roomThree = new Element("roomThree");
        roomFour  = new Element("roomFour");
        roomFive  = new Element("roomFive");

        createDoors();
        createPickableElements();

        // Los contenedores
        poisonBox   = new Element("box one");
        treasureBox = new Element("box two");
        wardrobe    = new Element("wardrobe");
        chest       = new Element("chest");

        // Los hago visibles
        poisonBox.changeState("visible", true);
        treasureBox.changeState("visible", true);
        wardrobe.changeState("visible", true);

        State poisonBoxState = new State("poison", true, "antidote", true);
        poisonBoxState.setEffectMessage("You have been poison :( by box");
        poisonBoxState.setAntiEffectMessage("You have been healed :)");
        poisonBox.setStateToAffect(poisonBoxState);
    }

    private void createPickableElements() {
        // Los elementos levantables
        key      = new Element("key");
        treasure = new Element("treasure");
        antidote = new Element("antidote");
        pokemon  = new Element("pokemon");

        // Los hago visibles
        key.changeState("visible", true);
        pokemon.changeState("visible", true);

    }

    private void createDoors() {
        doorOneTwo = new Element("door");
        doorTwoOne = new Element("door to one");

        doorOneTwo.changeState("visible", true);
        doorTwoOne.changeState("visible", true);

        doorTwoThree = new Element("door to three");
        doorThreeTwo = new Element("door to two");

        doorTwoThree.changeState("visible", true);
        doorThreeTwo.changeState("visible", true);

        doorThreeFour = new Element("door to four");
        doorFourThree = new Element("door to three");

        doorThreeFour.changeState("visible", true);
        doorFourThree.changeState("visible", true);

        doorFourFive = new Element("door to five");
        doorFiveFour = new Element("door to four");

        doorFourFive.changeState("visible", true);
        doorFiveFour.changeState("visible", true);

        doorFiveOne = new Element("door to one");

        doorFiveOne.changeState("visible", true);

        setDoorsIntoRooms();
    }

    private void setDoorsIntoRooms() {
        roomOne.addElement(doorOneTwo);

        roomTwo.addElement(doorTwoOne);
        roomTwo.addElement(doorTwoThree);

        roomThree.addElement(doorThreeTwo);
        roomThree.addElement(doorThreeFour);

        roomFour.addElement(doorFourThree);
        roomFour.addElement(doorFourFive);

        roomFive.addElement(doorFiveFour);
        roomFive.addElement(doorFiveOne);

        roomOne.addCommand(lookAround);
        roomTwo.addCommand(lookAround);
        roomThree.addCommand(lookAround);
        roomFour.addCommand(lookAround);
        roomFive.addCommand(lookAround);

        addOpensToDoors();
    }

    private void addOpensToDoors() {

        doorOneTwo.addCommand(openDoor);
        doorTwoOne.addCommand(openDoor);
        doorTwoThree.addCommand(openDoor);

        ArrayList<String> doorThreeRequirements = new ArrayList<>();
        doorThreeRequirements.add("pokemon");

        IInterpreter doorThreeCondition = new ContainsElements(playerGenerico, doorThreeRequirements);
        doorThreeCondition.setFailMessage("The door is locked! You need a pokemon to open.");
        ICommand openDoorTwoThree  = new MovePlayerTo(game, doorThreeCondition, "open");
        doorTwoThree.addCommand(openDoorTwoThree);

        doorThreeTwo.addCommand(openDoor);

        ArrayList<String> doorFourRequirements = new ArrayList<>();
        doorFourRequirements.add("key");

        IInterpreter doorFourCondition = new ContainsElements(playerGenerico, doorFourRequirements);
        ICommand openDoorThreeFour = new MovePlayerTo(game, doorFourCondition, "open");
        doorThreeFour.addCommand(openDoorThreeFour);

        addDoorCommands();
        setObjectiveToDoors();
    }

    private void addDoorCommands() {
        doorFourThree.addCommand(openDoor);
        doorFourFive.addCommand(openDoor);
        doorFiveFour.addCommand(openDoor);
        doorFiveOne.addCommand(openDoor);
    }

    private void setObjectiveToDoors() {
        doorOneTwo.setObjectiveElement(roomTwo);
        doorTwoOne.setObjectiveElement(roomOne);
        doorTwoThree.setObjectiveElement(roomThree);
        doorThreeTwo.setObjectiveElement(roomTwo);
        doorThreeFour.setObjectiveElement(roomFour);

        doorFourThree.setObjectiveElement(roomThree);
        doorFourFive.setObjectiveElement(roomFive);
        doorFiveFour.setObjectiveElement(roomFour);
        doorFiveOne.setObjectiveElement(roomOne);
    }

    private void insertElements() {
        // Elementos de roomOne
        roomOne.addElement(pokemon);

        // Elementos de roomThree
        roomThree.addElement(key);

        // Elementos de roomFour
        chest.addElement(antidote);
        wardrobe.addElement(chest);
        roomFour.addElement(wardrobe);

        // Elementos de roomFive
        treasureBox.addElement(treasure);

        roomFive.addElement(poisonBox);
        roomFive.addElement(treasureBox);

        addICommandsToElements();
    }

    private void addICommandsToElements() {
        key.addCommand(pick);
        key.addCommand(drop);

        antidote.addCommand(pick);
        wardrobe.addCommand(openContainer);
        chest.addCommand(openContainer);

        poisonBox.addCommand(openContainer);
        treasureBox.addCommand(openContainer);

        treasure.addCommand(pick);
        pokemon.addCommand(pick);

        treasure.addCommand(drop);
        pokemon.addCommand(drop);

        addQuestions();
    }

    private void addQuestions() {
        pokemon.addCommand(question);
        key.addCommand(question);
        antidote.addCommand(question);
        wardrobe.addCommand(question);
        chest.addCommand(question);
        poisonBox.addCommand(question);
        treasureBox.addCommand(question);
        treasure.addCommand(question);
    }

    private void createFinishingConditions() {
        // Creo la condicion de ganar
        ArrayList<String> playerWithTreasure = new ArrayList<>();
        playerWithTreasure.add("treasure");

        IInterpreter playerWithTreasureInterpreter = new ContainsElements(playerGenerico,playerWithTreasure);

        ArrayList<String> playerInRoomOne = new ArrayList<>();
        playerInRoomOne.add("player");

        IInterpreter playerInRoomOneInterpreter = new ContainsPlayer(roomOne,playerInRoomOne);
        IInterpreter winInterpreter = new AndExpression(playerWithTreasureInterpreter, playerInRoomOneInterpreter);

        // Creo la condicion de perder
        IInterpreter playerPoisoned = new HasValueState(playerGenerico, "poison", true);
        IInterpreter losingOneInterpreter = new AndExpression(playerPoisoned, playerInRoomOneInterpreter);

        ArrayList<String> playerInRoomFour = new ArrayList<>();
        playerInRoomFour.add("player");

        IInterpreter playerInRoomFourInterpreter = new ContainsPlayer(roomFour,playerInRoomFour);
        IInterpreter losingTwoInterpreter = new AndExpression(playerPoisoned, playerInRoomFourInterpreter);
        IInterpreter losingInterpreter = new OrExpression(losingOneInterpreter,losingTwoInterpreter);

        for (Player player : players) {
            player.setWinInterpreter(winInterpreter);
            player.setLosingInterpreter(losingInterpreter);
        }
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
        roomFour.addCommand(help);
        roomFour.addCommand(exit);
        roomFive.addCommand(help);
        roomFive.addCommand(exit);
    }


    @SuppressWarnings("CPD-END")

    private void createICommands() {

        lookAround = new LookAround("look around", game);
        pick = new MoveToPlayer("pick", game);
        drop = new DropOnPosition("drop", game);
        openDoor = new MovePlayerTo(game, "open");
        openContainer = new ChangeVisibility("open",true, game);
        question = new Question("ask");
    }


}
