package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;

import java.util.ArrayList;

public class TheScape implements GameBuilder {

    @SuppressWarnings("CPD-START")
    private Game game;
    private Element player;

    // Los cuartos
    private Element roomOne;
    private Element roomHanoi;
    private Element roomHanoiBis;
    private Element roomArchaeologist;
    private Element lastRoom;

    // Las puertas
    private Element doorOneHanoi;
    private Element doorHanoiBisArchaeologist;
    private Element doorArchaeologistHanoi;
    private Element doorArchaeologistOutside;
    private Element riverForward;
    private Element riverBackward;

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
    private Element archaeologist;

    // Los ICommands
    private ICommand drop;
    private ICommand pick;
    private ICommand openDoor;
    private ICommand openContainer;
    private ICommand closeContainer;
    private ICommand question;
    private ICommand lookAround;
    private ICommand cross;

//    private ICommand moveWithComparator;
    private ICommand talk;

    @Override
    public Game build() {

        game = new Game("Temple Quest");
        player = new Element("player");

        createLastRoom();
        createICommands();
        createRoomOne();
        roomHanoiBis = new Element("roomHanoiBis");
        createRoomHanoi();
        createRoomHanoiBis();
        createRoomArchaeologist();

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
        doorOneHanoi.setState(true);

        // Los elementos levantables
        antidote    = new Element("antidote");
        apple       = new Element("apple");
        monkey      = new Element("monkey");

        antidote.setAntidote(true);
        apple.setPoisoned(true);
        monkey.setState(true);

        monkey.setSize(0);

        // Los elementos contenedores
        skeleton    = new Element("skeleton");
        chest       = new Element("chest");

        skeleton.setState(true);
        chest.setState(true);

        combineElementsRoomOne();
    }

    private void combineElementsRoomOne() {

        roomOne.addElement(chest);
        roomOne.addElement(skeleton);
        roomOne.addElement(monkey);
        roomOne.addElement(doorOneHanoi);

        skeleton.addElement(apple);
        chest.addElement(antidote);

        addICoomandsToElementsInRoomOne();
    }

    private void addICoomandsToElementsInRoomOne() {

        // La puerta se abre con el mono
        ArrayList<String> monkeyRequirements = new ArrayList<>();
        monkeyRequirements.add("monkey");

        IInterpreter hasMonkey = new ContainsElements(player, monkeyRequirements);
        hasMonkey.setFailMessage("Ey! Where do you go, you have to pull both lever at the same time");

        ICommand openDoorWithMonkey = new MovePlayerTo(game, hasMonkey, "open");

        doorOneHanoi.addCommand(openDoorWithMonkey);


        // Los elementos levantables
        apple.addCommand(pick);
        apple.addCommand(drop);

        antidote.addCommand(pick);
        antidote.addCommand(drop);

        ICommand wakeUp = new MoveToPlayer("wake up", game);
        wakeUp.correctMovementMessage("You woke up the ");
        monkey.addCommand(wakeUp);

        // Los elementos contenedores
        chest.addCommand(openContainer);

        ICommand search = new ChangeVisibility("search", true, game);
        search.correctMovementMessage(" has elements inside.");
        skeleton.addCommand(search);

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

    private void createRoomHanoiBis() {
        doorHanoiBisArchaeologist = new Element("door");
//        roomHanoiBis = new Element("roomHanoiBis");
        riverBackward = new Element("river");
        riverBackward.setState(true);
        riverBackward.setObjectiveElement(roomHanoi);
        riverBackward.addCommand(cross);
        riverBackward.addCommand(question);
        roomHanoiBis.addCommand(lookAround);
        roomHanoiBis.addElement(riverBackward);


        ArrayList<String> containsBigDisk = new ArrayList<>();
        containsBigDisk.add("disk nine");
        IInterpreter containsDisk = new ContainsElements(player, containsBigDisk);
        containsDisk.setFailMessage("You need the magic stone to open the door!");

        doorHanoiBisArchaeologist.setState(true);
        doorHanoiBisArchaeologist.addCommand(new MovePlayerTo(game,containsDisk,"open"));

        roomHanoiBis.addElement(doorHanoiBisArchaeologist);
    }

    private void createRoomHanoi() {
        roomHanoi = new Element("roomHanoi");
        doorOneHanoi.setObjectiveElement(roomHanoi);



        riverForward = new Element("river");
        riverForward.setState(true);
        riverForward.setObjectiveElement(roomHanoiBis);
        riverForward.addCommand(cross);
        riverForward.addCommand(question);

        createPillarsAndDisks();
        combineElementsRoomHanoi();
    }

    private void combineElementsRoomHanoi() {

        roomHanoi.addElement(pillarOne);
        roomHanoi.addElement(pillarTwo);
        roomHanoi.addElement(pillarThree);
        roomHanoi.addElement(riverForward);

        pillarOne.addCommand(question);
        pillarTwo.addCommand(question);
        pillarThree.addCommand(question);

        addICoomandsToElementsInRoomHanoi();
    }

    private void addICoomandsToElementsInRoomHanoi() {

        ICommand moveSmallest = new MoveWithComparator("move top", (Element e1, Element e2) -> e1.getSize() - e2.getSize());
        moveSmallest.correctMovementMessage("You moved the disk!");
        moveSmallest.incorrectMovementMessage("You can't move a bigger disk over a smaller one!");
        moveSmallest.auxiliarMessage("The stack from where you are trying to move is empty");

        pillarOne.addCommand(moveSmallest);
        pillarTwo.addCommand(moveSmallest);
        pillarThree.addCommand(moveSmallest);

        ICommand checkTop = new Check("check top",(Element e1, Element e2) -> e1.getSize() - e2.getSize());
        checkTop.correctMovementMessage("The size of the top is ");
        checkTop.incorrectMovementMessage("The stack you are trying to check is empty.");

        pillarOne.addCommand(checkTop);
        pillarTwo.addCommand(checkTop);
        pillarThree.addCommand(checkTop);

        ICommand lookAround = new LookAround("look around", game);
        roomHanoi.addCommand(lookAround);

        diskNine.addCommand(pick);
        diskNine.setState(true);
    }

    private void createPillarsAndDisks() {
        pillarOne   = new Element("pillar one");
        pillarTwo   = new Element("pillar two");
        pillarThree = new Element("pillar three");

        pillarOne.setState(true);
        pillarTwo.setState(true);
        pillarThree.setState(true);

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

        diskOne.setSize(1);
        diskTwo.setSize(2);
        diskThree.setSize(3);
        diskFour.setSize(4);
        diskFive.setSize(5);
        diskSix.setSize(6);
        diskSeven.setSize(7);
        diskEight.setSize(8);
        diskNine.setSize(9);

    }

    private void createRoomArchaeologist() {

        roomArchaeologist = new Element("roomArchaeologist");
        doorHanoiBisArchaeologist.setObjectiveElement(roomArchaeologist);
        archaeologist = new Element("archaeologist");
        archaeologist.setState(true);
        doorArchaeologistOutside = new Element("door outside");
        doorArchaeologistHanoi = new Element("door back");
        doorArchaeologistHanoi.setState(true);
        doorArchaeologistOutside.setState(true);

        combineElementsArchaeologist();
    }

    private void combineElementsArchaeologist() {
        roomArchaeologist.addElement(archaeologist);
        roomArchaeologist.addElement(doorArchaeologistOutside);
        roomArchaeologist.addElement(doorArchaeologistHanoi);

        doorArchaeologistHanoi.setObjectiveElement(roomHanoi);
        doorArchaeologistOutside.setObjectiveElement(lastRoom);

        addICoomandsToElementsInRoomArchaeologist();
    }

    private void addICoomandsToElementsInRoomArchaeologist() {
        archaeologist.addCommand(talk);
        archaeologist.addCommand(question);
        doorArchaeologistOutside.addCommand(question);
        doorArchaeologistHanoi.addCommand(question);

        doorArchaeologistHanoi.addCommand(openDoor);


        ArrayList<String> doorArchaeologistOutsideRequirements = new ArrayList<String>();
        doorArchaeologistOutsideRequirements.add("diskNine");
        IInterpreter doorArchaeologistOutsideCondition = new DoesNotContainElements(player, doorArchaeologistOutsideRequirements);
        ICommand openDoorArchaeologistOutside = new MovePlayerTo(game, doorArchaeologistOutsideCondition, "open");
        doorArchaeologistOutside.addCommand(openDoorArchaeologistOutside);
        roomArchaeologist.addCommand(lookAround);

    }

    private void createFinishingConditions() {

        ArrayList<String> roomContainsPlayer = new ArrayList<String>();
        roomContainsPlayer.add("player");

        IInterpreter playerInLastRoom = new ContainsElements(lastRoom, roomContainsPlayer);

        ArrayList<String> playerWithDisk = new ArrayList<String>();
        playerWithDisk.add("disk nine");

        IInterpreter playerWithDiskInterpreter = new ContainsElements(player, playerWithDisk);
        IInterpreter playerIsPoisoned = new IsPoisoned(player, true);

        IInterpreter deadPlayer = new OrExpression(playerWithDiskInterpreter, playerIsPoisoned);
        IInterpreter losingInterpreter = new AndExpression(deadPlayer, playerInLastRoom);


        game.setWinInterpreter(playerInLastRoom);
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
        talk            = new MoveFromPlayer("talk", game, "disk nine");
        createCross();
    }

    private void createCross() {
//        ArrayList<String> requirementsToCross = new ArrayList<>();
//        requirementsToCross.add("disk nine");
//
//        IInterpreter containsDisk9 = new ContainsElements(player, requirementsToCross);

        IInterpreter hasOneDisk = new HasLessElementsThan(player, 3);
        hasOneDisk.setFailMessage("The rope won't support your weight.");
        cross = new MovePlayerTo(game, hasOneDisk, "cross");
    }
}


