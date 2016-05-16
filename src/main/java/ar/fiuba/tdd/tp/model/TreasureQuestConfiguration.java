package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.AndExpression;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.OrExpression;

import java.util.ArrayList;

public class TreasureQuestConfiguration implements GameBuilder {

    @Override
    public Game build() {
        return new Game("Treasure Quest");
    }

//    private Game game;
//
//    // Los cuartos
//    private Element roomOne;
//    private Element roomTwo;
//    private Element roomThree;
//    private Element roomFour;
//    private Element roomFive;
//
//    // Las puertas en ambos sentidos
//    private Element doorOneTwo;
//    private Element doorTwoOne;
//    private Element doorTwoThree;
//    private Element doorThreeTwo;
//    private Element doorThreeFour;
//    private Element doorFourThree;
//    private Element doorFourFive;
//    private Element doorFiveFour;
//    private Element doorFiveOne;
//
//    // Las cajas
//    private Element poisonBox;
//    private Element treasureBox;
//
//    // Los objetos levantables
//    private Element stick;
//    private Element treasure;
//    private Element key;
//
//    // Los elementos extra
//    private Element player;
//
//    // Los ICommands genericos
//    private ICommand question;
//    private ICommand lookAround;
//    private ICommand pick;
//    private ICommand drop;
//    private ICommand openDoor;
//    private ICommand openBox;
//
//    // TODO: CAMBIAR EL METODO DE OPEN DOOR / OPEN DOOR 2
//    public Game build() {
//
//        player = new Element("player");
//        game = new Game("Treasure Quest");
//
//        createElements();
//
//        createFinishingConditions();
//
//        // Agrego la posicion del player
//        game.setPlayer(player);
//        game.setPlayerPosition(roomOne);
//        return game;
//    }
//
//    private void createElements() {
//
//
//        // Los cuartos
//        roomOne   = new Element("roomOne");
//        roomTwo   = new Element("roomTwo");
//        roomThree = new Element("roomThree");
//        roomFour  = new Element("roomFour");
//        roomFive  = new Element("roomFive");
//
//        createICommands();
//        createDoors();
//
//        // Los elementos levantables
//        key = new Element("key");
//        key.setState(true);
//        stick    = new Element("stick");
//        treasure = new Element("treasure");
////        stick.setState(true);
//        stick.setPoisoned(true);
////        treasure.setState(true);
//
//        // Las cajas
//        poisonBox   = new Element("box one");
//        treasureBox = new Element("box two");
//
//        // Las hago visibles
//        poisonBox.setState(true);
//        treasureBox.setState(true);
//
//        insertElements();
//
//
//    }
//
//    private void createDoors() {
//        doorOneTwo = new Element("door");
//        doorTwoOne = new Element("door to one");
//
//        doorOneTwo.setState(true);
//        doorTwoOne.setState(true);
//
//        doorTwoThree = new Element("door to three");
//        doorThreeTwo = new Element("door to two");
//
//        doorTwoThree.setState(true);
//        doorThreeTwo.setState(true);
//
//        doorThreeFour = new Element("door to four");
//        doorFourThree = new Element("door to three");
//
//        doorThreeFour.setState(true);
//        doorFourThree.setState(true);
//
//        doorFourFive = new Element("door to five");
//        doorFiveFour = new Element("door to four");
//
//        doorFourFive.setState(true);
//        doorFiveFour.setState(true);
//
//        doorFiveOne = new Element("door to one");
//
//        doorFiveOne.setState(true);
//
//        setDoorsIntoRooms();
//    }
//
//    private void setDoorsIntoRooms() {
//        roomOne.addElement(doorOneTwo);
//
//        roomTwo.addElement(doorTwoOne);
//        roomTwo.addElement(doorTwoThree);
//
//        roomThree.addElement(doorThreeTwo);
//        roomThree.addElement(doorThreeFour);
//
//        roomFour.addElement(doorFourThree);
//        roomFour.addElement(doorFourFive);
//
//        roomFive.addElement(doorFiveFour);
//        roomFive.addElement(doorFiveOne);
//
//        roomOne.addCommand(lookAround);
//        roomTwo.addCommand(lookAround);
//        roomThree.addCommand(lookAround);
//        roomFour.addCommand(lookAround);
//        roomFive.addCommand(lookAround);
//
//        addOpensToDoors();
//    }
//
//    private void addOpensToDoors() {
//
//        doorOneTwo.addCommand(openDoor);
//        doorTwoOne.addCommand(openDoor);
//        doorTwoThree.addCommand(openDoor);
//        doorThreeTwo.addCommand(openDoor);
//
//        ArrayList<String> doorRequirements = new ArrayList<String>();
//        doorRequirements.add("key");
//
//        IInterpreter doorCondition = new ContainsElements(player, doorRequirements);
//        ICommand openDoorThreeFour = new MovePlayerTo(game, doorCondition, "open");
//        doorThreeFour.addCommand(openDoorThreeFour);
//
//        doorFourThree.addCommand(openDoor);
//        doorFourFive.addCommand(openDoor);
//        doorFiveFour.addCommand(openDoor);
//        doorFiveOne.addCommand(openDoor);
//
//        setObjectiveToDoors();
//    }
//
//    private void setObjectiveToDoors() {
//        doorOneTwo.setObjectiveElement(roomTwo);
//        doorTwoOne.setObjectiveElement(roomOne);
//        doorTwoThree.setObjectiveElement(roomThree);
//        doorThreeTwo.setObjectiveElement(roomTwo);
//        doorThreeFour.setObjectiveElement(roomFour);
//
//        doorFourThree.setObjectiveElement(roomThree);
//        doorFourFive.setObjectiveElement(roomFive);
//        doorFiveFour.setObjectiveElement(roomFour);
//        doorFiveOne.setObjectiveElement(roomOne);
//    }
//
//    private void insertElements() {
//        poisonBox.addElement(stick);
//        treasureBox.addElement(treasure);
//
//        poisonBox.addCommand(openBox);
//        treasureBox.addCommand(openBox);
//
//        roomThree.addElement(key);
//        roomFive.addElement(poisonBox);
//        roomFive.addElement(treasureBox);
////        roomFive.addElement(treasure);
////        roomFive.addElement(stick);
//
//        addICommandsToElements();
//    }
//
//    private void addICommandsToElements() {
//        key.addCommand(pick);
//        key.addCommand(drop);
//        key.addCommand(question);
//
//        stick.addCommand(pick);
//        treasure.addCommand(pick);
//    }
//
//
//    private void createFinishingConditions() {
//
//        ArrayList<String> playerWithTreasure = new ArrayList<String>();
//        playerWithTreasure.add("treasure");
//
//        IInterpreter playerWithTreasureInterpreter = new ContainsElements(player,playerWithTreasure);
//
//        ArrayList<String> playerInRoomOne = new ArrayList<String>();
//        playerInRoomOne.add("player");
//
//        IInterpreter playerInRoomOneInterpreter = new ContainsElements(roomOne,playerInRoomOne);
//
//        IInterpreter winInterpreter = new AndExpression(playerWithTreasureInterpreter, playerInRoomOneInterpreter);
//
//        ArrayList<String> playerWithPoison = new ArrayList<String>();
//        playerWithPoison.add("stick");
//
//        IInterpreter playerWithPoisonInterpreter = new ContainsElements(player, playerWithPoison);
//
//        IInterpreter losingOneInterpreter = new AndExpression(playerWithPoisonInterpreter, playerInRoomOneInterpreter);
//
//        ArrayList<String> playerInRoomFour = new ArrayList<String>();
//        playerInRoomFour.add("player");
//
//        IInterpreter playerInRoomFourInterpreter = new ContainsElements(roomFour,playerInRoomFour);
//
//        IInterpreter losingTwoInterpreter = new AndExpression(playerWithPoisonInterpreter, playerInRoomFourInterpreter);
//
//        IInterpreter losingInterpreter = new OrExpression(losingOneInterpreter,losingTwoInterpreter);
//
//        game.setWinInterpreter(winInterpreter);
//        game.setLosingInterpreter(losingInterpreter);
//    }
//
//    private void createICommands() {
//        pick = new MoveToPlayer("pick", game);
//        drop = new MoveFromPlayer("drop", game);
//        openDoor = new MovePlayerTo(game, "open");
//        openBox = new ChangeVisibility("open",true);
//        question = new Question("ask");
//        lookAround = new LookAround("look around", game);
//    }


}
