package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;

import java.util.ArrayList;

public class TempleQuest implements GameBuilder {

    @SuppressWarnings("CPD-START")
    private Game game;
    private Element player;

    // Los cuartos
    private Element roomOne;
    private Element roomHanoi;
    private Element roomArchaeologist;
    private Element lastRoom;

    // Las puertas
    private Element doorOneHanoi;
//    private Element doorHanoiArchaeologist;
    private Element doorArchaeologistHanoi;
    private Element doorArchaeologistOutside;

    // Los elementos levantables
    private Element monkey;
    private Element antidote;
    private Element apple;

    // Los elementos contenedores
    private Element skeleton;
    private Element chest;

    // Los pilares de Hanoi
    private Element pillarOne;
    private Element pillarTwo;
    private Element pillarThree;

    // Los discos de Hanoi
    private Element diskOne;
    private Element diskTwo;
    private Element diskThree;
    private Element diskFour;
    private Element diskFive;
    private Element diskSix;
    private Element diskSeven;
    private Element diskEight;
    private Element diskNine;
    //El ladron
    private Element thief;

    // Los ICommands
    private ICommand drop;
    private ICommand pick;
    private ICommand openDoor;
    private ICommand openContainer;
    private ICommand closeContainer;
    private ICommand question;
    private ICommand lookAround;

//    private ICommand moveWithComparator;
    private ICommand talk;

    @Override
    public Game build() {

        game = new Game("Temple Quest");
        player = new Element("player");

        createRoomOne();
        createRoomHanoi();
        createRoomArchaeologist();
        createLastRoom();

        createFinishingConditions();

        // Agrego la posicion del player
        game.setPlayer(player);
        game.setPlayerPosition(roomOne);
        return game;
    }

    private void createLastRoom() {
        lastRoom = new Element("lastRoom");
    }

    private void createRoomOne() {
        roomOne     = new Element("roomOne");

        doorOneHanoi = new Element("door");

        // Los elementos levantables
        antidote    = new Element("antidote");
        apple       = new Element("apple");
        monkey      = new Element("monkey");

        antidote.setAntidote(true);
        apple.setPoisoned(true);
        monkey.setState(true);

        // Los elementos contenedores
        skeleton    = new Element("skeleton");
        chest       = new Element("chest");

        skeleton.setState(true);
        chest.setState(true);

        combineElementsRoomOne();
    }

    private void combineElementsRoomOne() {

        skeleton.addElement(apple);
        chest.addElement(antidote);
        addICoomandsToElementsInRoomOne();
    }

    private void addICoomandsToElementsInRoomOne() {

        // La puerta se abre con el mono
        ArrayList<String> monkeyRequirements = new ArrayList<>();
        monkeyRequirements.add("monkey");

        IInterpreter hasMonkey = new ContainsElements(doorOneHanoi, monkeyRequirements);

        ICommand openDoorWithMonkey = new MovePlayerTo(game, hasMonkey, "open");

        doorOneHanoi.addCommand(openDoorWithMonkey);

        // Los elementos levantables
        apple.addCommand(pick);
        apple.addCommand(drop);

        skeleton.addCommand(pick);
        skeleton.addCommand(drop);

        // Los elementos contenedores
        skeleton.addCommand(openContainer);
        chest.addCommand(openContainer);

        chest.addCommand(closeContainer);

        roomOne.addCommand(lookAround);

        addQuestionsToRoomOne();

    }

    private void addQuestionsToRoomOne() {

        doorOneHanoi.addCommand(question);

        apple.addCommand(question);
        antidote.addCommand(question);
        monkey.addCommand(question);

        chest.addCommand(question);
        skeleton.addCommand(question);
    }

    private void createRoomHanoi() {
        roomHanoi = new Element("roomHanoi");

        createPillarsAndDisks();
        combineElementsRoomHanoi();
    }

    private void combineElementsRoomHanoi() {

        addICoomandsToElementsInRoomHanoi();
    }

    private void addICoomandsToElementsInRoomHanoi() {


    }

    private void createPillarsAndDisks() {
        pillarOne   = new Element("pillar one");
        pillarTwo   = new Element("pillar two");
        pillarThree = new Element("pillar three");

        diskOne    = new Element("disk one");
        diskTwo    = new Element("disk two");
        diskThree  = new Element("disk three");
        diskFour   = new Element("disk four");
        diskFive   = new Element("disk five");
        diskSix    = new Element("disk six");
        diskSeven  = new Element("disk seven");
        diskEight  = new Element("disk eight");
        diskNine   = new Element("disk nine");

        addDisksToPillars();
    }

    private void addDisksToPillars() {
        pillarOne.addElement(diskOne);
        pillarOne.addElement(diskTwo);
        pillarOne.addElement(diskThree);

        pillarTwo.addElement(diskFour);
        pillarTwo.addElement(diskFive);
        pillarTwo.addElement(diskSix);

        pillarThree.addElement(diskSeven);
        pillarThree.addElement(diskEight);
        pillarThree.addElement(diskNine);
    }

    private void createRoomArchaeologist() {

        roomArchaeologist = new Element("roomArchaeologist");
        thief = new Element("thief");
        thief.setState(true);
        doorArchaeologistOutside = new Element("door forward");
        doorArchaeologistHanoi = new Element("door back");

        combineElementsArchaeologist();
    }

    private void combineElementsArchaeologist() {
        roomArchaeologist.addElement(thief);
        roomArchaeologist.addElement(doorArchaeologistOutside);
        roomArchaeologist.addElement(doorArchaeologistHanoi);

        doorArchaeologistHanoi.setObjectiveElement(roomHanoi);
        doorArchaeologistOutside.setObjectiveElement(lastRoom);

        addICoomandsToElementsInRoomArchaeologist();
    }

    private void addICoomandsToElementsInRoomArchaeologist() {
        thief.addCommand(talk);

        doorArchaeologistHanoi.addCommand(openDoor);

        ArrayList<String> doorArchaeologistOutsideRequirements = new ArrayList<String>();
        doorArchaeologistOutsideRequirements.add("diskNine");
        IInterpreter doorArchaeologistOutsideCondition = new DoesNotContainElements(player, doorArchaeologistOutsideRequirements);
        ICommand openDoorArchaeologistOutside = new MovePlayerTo(game, doorArchaeologistOutsideCondition, "open");
        doorArchaeologistOutside.addCommand(openDoorArchaeologistOutside);

    }

    private void createFinishingConditions() {

        ArrayList<String> roomContainsPlayer = new ArrayList<String>();
        roomContainsPlayer.add("player");

        IInterpreter winInterpreter = new ContainsElements(lastRoom, roomContainsPlayer);

        ArrayList<String> playerWithDisk = new ArrayList<String>();
        playerWithDisk.add("diskNine");

        IInterpreter playerWithDiskInterpreter = new ContainsElements(player, playerWithDisk);
        IInterpreter playerIsPoisoned = new IsPoisoned(player, true);

        IInterpreter losingInterpreter = new OrExpression(playerWithDiskInterpreter, playerIsPoisoned);

        game.setWinInterpreter(winInterpreter);
        game.setLosingInterpreter(losingInterpreter);
    }

    @SuppressWarnings("CPD-END")

    private void createICommands() {
        drop            = new DropOnPosition("drop", game);
        pick            = new MoveToPlayer("pick", game);
        openDoor        = new MovePlayerTo(game, "open");
        openContainer   = new ChangeVisibility("open", true, game);
        closeContainer  = new ChangeVisibility("close", false, game);
        question        = new Question("ask");
        lookAround      = new LookAround("look around", game);
        talk            = new MoveFromPlayer("talk", game);
    }
}


