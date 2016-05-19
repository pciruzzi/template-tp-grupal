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

    // Las puertas
    private Element doorOneHanoi;
    private Element doorHanoiArchaeologist;
    private Element doorArchaeologistOutside;

    // Los elementos levantables
    private Element key;
    private Element antidote;
    private Element apple;

    // Los elementos contenedores
    private Element skeleton;
    private Element chest;

    // Los ICommands
    private ICommand drop;
    private ICommand pick;
    private ICommand openDoor;
    private ICommand openContainer;
    private ICommand closeContainer;
    private ICommand question;
    private ICommand lookAround;
    private ICommand moveWithComparator;

    @Override
    public Game build() {

        game = new Game("Temple Quest");
        player = new Element("player");

        createRoomOne();
        createRoomHanoi();
        createRoomArchaeologist();

        createFinishingConditions();

        // Agrego la posicion del player
        game.setPlayer(player);
        game.setPlayerPosition(roomOne);
        return game;
    }

    private void createRoomOne() {
        roomOne     = new Element("roomOne");

        // Los elementos levantables
        antidote    = new Element("antidote");
        apple       = new Element("apple");

        antidote.setAntidote(true);
        apple.setPoisoned(true);

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

    }

    private void createRoomHanoi() {
        roomHanoi = new Element("roomHanoi");

        combineElementsRoomHanoi();
    }

    private void combineElementsRoomHanoi() {

        addICoomandsToElementsInRoomHanoi();
    }

    private void addICoomandsToElementsInRoomHanoi() {

    }

    private void createRoomArchaeologist() {
        roomArchaeologist = new Element("roomArchaeologist");

        combineElementsArchaeologist();
    }

    private void combineElementsArchaeologist() {

        addICoomandsToElementsInRoomArchaeologist();
    }

    private void addICoomandsToElementsInRoomArchaeologist() {

    }

    private void createFinishingConditions() {

        ArrayList<String> playerContains = new ArrayList<String>();
        playerContains.add("algo");


        IInterpreter playerAlgo = new ContainsElements(player,playerContains);

        game.setWinInterpreter(playerAlgo);
        game.setLosingInterpreter(playerAlgo);
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
    }
}


