package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;

import java.util.ArrayList;

public class TreasureQuestConfiguration implements GameBuilder {

    private Game game;

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
    private Element player;

    // Los ICommands genericos
    private ICommand question;
    private ICommand lookAround;
    private ICommand pick;
    private ICommand drop;
    private ICommand openDoor;
    private ICommand openContainer;

    @SuppressWarnings("CPD-START")
    public Game build() {

        player = new Element("player");
        player.setCapacity(2);
        game = new Game("Treasure Quest");

        createICommands();

        createElements();
        insertElements();

        createFinishingConditions();

        // Agrego la posicion del player
        game.setPlayer(player);
        game.setPlayerPosition(roomOne);
        return game;
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
        poisonBox.setState(true);
        treasureBox.setState(true);
        wardrobe.setState(true);

        poisonBox.setPoisoned(true);
    }

    private void createPickableElements() {
        // Los elementos levantables
        key      = new Element("key");
        treasure = new Element("treasure");
        antidote = new Element("antidote");
        pokemon  = new Element("pokemon");

        // Los hago visibles
        key.setState(true);
        pokemon.setState(true);
        antidote.setAntidote(true);
    }

    private void createDoors() {
        doorOneTwo = new Element("door");
        doorTwoOne = new Element("door to one");

        doorOneTwo.setState(true);
        doorTwoOne.setState(true);

        doorTwoThree = new Element("door to three");
        doorThreeTwo = new Element("door to two");

        doorTwoThree.setState(true);
        doorThreeTwo.setState(true);

        doorThreeFour = new Element("door to four");
        doorFourThree = new Element("door to three");

        doorThreeFour.setState(true);
        doorFourThree.setState(true);

        doorFourFive = new Element("door to five");
        doorFiveFour = new Element("door to four");

        doorFourFive.setState(true);
        doorFiveFour.setState(true);

        doorFiveOne = new Element("door to one");

        doorFiveOne.setState(true);

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

        ArrayList<String> doorThreeRequirements = new ArrayList<String>();
        doorThreeRequirements.add("pokemon");

        IInterpreter doorThreeCondition = new ContainsElements(player, doorThreeRequirements);
        ICommand openDoorTwoThree  = new MovePlayerTo(game, doorThreeCondition, "open");
        doorTwoThree.addCommand(openDoorTwoThree);

        doorThreeTwo.addCommand(openDoor);

        ArrayList<String> doorFourRequirements = new ArrayList<String>();
        doorFourRequirements.add("key");

        IInterpreter doorFourCondition = new ContainsElements(player, doorFourRequirements);
        ICommand openDoorThreeFour = new MovePlayerTo(game, doorFourCondition, "open");
        doorThreeFour.addCommand(openDoorThreeFour);

        doorFourThree.addCommand(openDoor);
        doorFourFive.addCommand(openDoor);
        doorFiveFour.addCommand(openDoor);
        doorFiveOne.addCommand(openDoor);

        setObjectiveToDoors();
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

        ArrayList<String> playerWithTreasure = new ArrayList<String>();
        playerWithTreasure.add("treasure");

        IInterpreter playerWithTreasureInterpreter = new ContainsElements(player,playerWithTreasure);

        ArrayList<String> playerInRoomOne = new ArrayList<String>();
        playerInRoomOne.add("player");

        IInterpreter playerInRoomOneInterpreter = new ContainsElements(roomOne,playerInRoomOne);

        IInterpreter winInterpreter = new AndExpression(playerWithTreasureInterpreter, playerInRoomOneInterpreter);

        IInterpreter playerPoisoned = new IsPoisoned(player, true);

        IInterpreter losingOneInterpreter = new AndExpression(playerPoisoned, playerInRoomOneInterpreter);

        ArrayList<String> playerInRoomFour = new ArrayList<String>();
        playerInRoomFour.add("player");

        IInterpreter playerInRoomFourInterpreter = new ContainsElements(roomFour,playerInRoomFour);

        IInterpreter losingTwoInterpreter = new AndExpression(playerPoisoned, playerInRoomFourInterpreter);

        IInterpreter losingInterpreter = new OrExpression(losingOneInterpreter,losingTwoInterpreter);

        game.setWinInterpreter(winInterpreter);
        game.setLosingInterpreter(losingInterpreter);
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
