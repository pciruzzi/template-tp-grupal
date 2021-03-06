package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.State;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;
import ar.fiuba.tdd.tp.interpreter.logic.AndExpression;
import ar.fiuba.tdd.tp.interpreter.logic.OrExpression;
import ar.fiuba.tdd.tp.interpreter.terminalexpressions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("CPD-START")
public class TempleQuestConfiguration implements GameBuilder {

    private Game game;
    private Optional<Element> playerGenerico;

    private List<Player> players;
    private int maxPlayers;

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
    private ICommand talk;

    @Override
    public Game build() {
        game = new Game("Temple Quest");
        game.setDescription("Think that you're Indiana Jones and then act like him...");
        setPlayers();

        createLastRoom();
        createICommands();
        createRoomOne();
        roomHanoiBis = new Element("roomHanoiBis");
        createRoomHanoi();
        createRoomHanoiBis();
        createRoomArchaeologist();

        createFinishingConditions();
        setHelpAndExitCommand();

        // Agrego la posicion del player
        game.setInitialPosition(roomOne);
        return game;
    }

    private void setPlayers() {
        maxPlayers = 4;
        game.setMaxPlayers(maxPlayers);
        players = new ArrayList<>();

        playerGenerico = Optional.empty();
        for (int i = 0; i < maxPlayers; i++) {
            Player newPlayer = new Player(i);
            players.add(newPlayer);
        }
        game.setPlayers(players);
    }

    private void setHelpAndExitCommand() {
        ICommand exit = new Exit(game);
        ICommand help = new Help("help", game);

        roomOne.addCommand(exit);
        roomOne.addCommand(help);
        roomHanoi.addCommand(exit);
        roomHanoi.addCommand(help);
        roomHanoiBis.addCommand(exit);
        roomHanoiBis.addCommand(help);
        roomArchaeologist.addCommand(exit);
        roomArchaeologist.addCommand(help);
        lastRoom.addCommand(exit);
        lastRoom.addCommand(help);
    }

    private void createLastRoom() {
        lastRoom = new Element("lastRoom");
    }

    private void createRoomOne() {
        roomOne      = new Element("roomOne");
        doorOneHanoi = new Element("door");
        doorOneHanoi.changeState("visible", true);

        // Los elementos levantables
        antidote    = new Element("antidote");
        apple       = new Element("apple");
        monkey      = new Element("monkey");

        //antidote.addState("antidote", true);
        apple.addState(new State("poison", true, false));
        monkey.changeState("visible", true);
        monkey.setSize(0);

        // Los elementos contenedores
        skeleton    = new Element("skeleton");
        chest       = new Element("chest");
        skeleton.changeState("visible", true);
        chest.changeState("visible", true);

        combineElementsRoomOne();
    }

    private void combineElementsRoomOne() {
        roomOne.addElement(chest);
        roomOne.addElement(skeleton);
        roomOne.addElement(monkey);
        roomOne.addElement(doorOneHanoi);
        roomOne.addCommand(lookAround);

        skeleton.addElement(apple);
        chest.addElement(antidote);

        addICoomandsToElementsInRoomOne();
    }

    private void addICoomandsToElementsInRoomOne() {
        // La puerta se abre con el mono
        ArrayList<String> monkeyRequirements = new ArrayList<>();
        monkeyRequirements.add("monkey");

        IInterpreter hasMonkey = new ContainsElements(playerGenerico, monkeyRequirements);
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
        riverBackward = new Element("river");
        riverBackward.changeState("visible", true);
        riverBackward.setObjectiveElement(roomHanoi);
        riverBackward.addCommand(cross);
        riverBackward.addCommand(question);
        roomHanoiBis.addCommand(lookAround);
        roomHanoiBis.addElement(riverBackward);

        ArrayList<String> containsBigDisk = new ArrayList<>();
        containsBigDisk.add("disk nine");
        IInterpreter containsDisk = new ContainsElements(playerGenerico, containsBigDisk);
        containsDisk.setFailMessage("You need the magic stone to open the door!");

        doorHanoiBisArchaeologist.changeState("visible", true);
        doorHanoiBisArchaeologist.addCommand(new MovePlayerTo(game,containsDisk,"open"));

        roomHanoiBis.addElement(doorHanoiBisArchaeologist);
    }

    private void createRoomHanoi() {
        roomHanoi = new Element("roomHanoi");
        doorOneHanoi.setObjectiveElement(roomHanoi);

        riverForward = new Element("river");
        riverForward.changeState("visible", true);
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
        diskNine.changeState("visible", true);
    }

    private void createPillarsAndDisks() {
        pillarOne   = new Element("pillar one");
        pillarTwo   = new Element("pillar two");
        pillarThree = new Element("pillar three");

        pillarOne.changeState("visible", true);
        pillarTwo.changeState("visible", true);
        pillarThree.changeState("visible", true);

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
        archaeologist.changeState("visible", true);
        doorArchaeologistOutside = new Element("door outside");
        doorArchaeologistHanoi = new Element("door back");
        doorArchaeologistHanoi.changeState("visible", true);
        doorArchaeologistOutside.changeState("visible", true);

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

        ArrayList<String> doorArchaeologistOutsideRequirements = new ArrayList<>();
        doorArchaeologistOutsideRequirements.add("diskNine");
        IInterpreter doorArchaeologistOutsideCondition = new DoesNotContainElements(playerGenerico, doorArchaeologistOutsideRequirements);
        ICommand openDoorArchaeologistOutside = new MovePlayerTo(game, doorArchaeologistOutsideCondition, "open");
        doorArchaeologistOutside.addCommand(openDoorArchaeologistOutside);
        roomArchaeologist.addCommand(lookAround);
    }

    private void createFinishingConditions() {
        ArrayList<String> roomContainsPlayer = new ArrayList<>();
        roomContainsPlayer.add("player");
        IInterpreter playerInLastRoom = new ContainsPlayer(Optional.of(lastRoom), roomContainsPlayer);

        ArrayList<String> playerWithDisk = new ArrayList<>();
        playerWithDisk.add("disk nine");

        IInterpreter playerWithDiskInterpreter = new ContainsElements(playerGenerico, playerWithDisk);
        IInterpreter playerIsPoisoned = new HasValueState(playerGenerico, "poison", true);

        IInterpreter deadPlayer = new OrExpression(playerWithDiskInterpreter, playerIsPoisoned);
        IInterpreter losingInterpreter = new AndExpression(deadPlayer, playerInLastRoom);

        for (Player player : players) {
            player.setWinInterpreter(playerInLastRoom);
            player.setLosingInterpreter(losingInterpreter);
        }
    }

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

    @SuppressWarnings("CPD-END")

    private void createCross() {
        IInterpreter hasOneDisk = new HasLessElementsThan(playerGenerico, 3);
        hasOneDisk.setFailMessage("The rope won't support your weight.");
        cross = new MovePlayerTo(game, hasOneDisk, "cross");
    }
}