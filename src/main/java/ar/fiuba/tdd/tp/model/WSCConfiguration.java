package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CPD-START")

public class WSCConfiguration implements GameBuilder {

    private Element wolf;
    private Element sheep;
    private Element cabbage;
    private Element northShore;
    private Element southShore;
    private Element boat;
    private Element riverSouthToNorth;
    private Element riverNorthToSouth;

    private Game game;
    private int maxPlayers;
    private List<Player> players;

    private IInterpreter winInterpreter;


    public Game build() {
        this.game = new Game("WSC");
        game.setDescription("You are a small farmer, with a small boat, you need to cross the river with a sheep a wolf and a cabagge.");
        maxPlayers = 1;
        game.setMaxPlayers(maxPlayers);
        players = new ArrayList<>();

        this.createElements();
        this.assignElementStates();
        this.assignSimpleCommand();
        this.assignComplexCommand();

        this.createGameWinInterpreter();

        game.setWinInterpreter(winInterpreter);
        game.setInitialPosition(southShore);

        IInterpreter loseInterpreter = new FalseExpression();
        game.setLosingInterpreter(loseInterpreter);
        setHelpAndExitCommand();

        return this.game;
    }

    private void assignComplexCommand() {
        ArrayList<String> sheepCabbage = buildCondition("sheep", "cabbage");
        ArrayList<String> sheepWolf = buildCondition("wolf", "sheep");

        IInterpreter southSheepCabbage = new ContainsElements(southShore,sheepCabbage);
        southSheepCabbage.setFailMessage("The wolf will eat the sheep!");
        IInterpreter southSheepWolf = new ContainsElements(southShore,sheepWolf);
        southSheepWolf.setFailMessage("The sheep will eat the cabbage!");

        IInterpreter orSouth = new OrExpression(southSheepCabbage, southSheepWolf);
        orSouth.setFailMessage("You can't do that! They'll eat other!");

        IInterpreter northSheepCabbage = new ContainsElements(northShore,sheepCabbage);
        northSheepCabbage.setFailMessage("The wolf will eat the sheep!");
        IInterpreter northSheepWolf = new ContainsElements(northShore,sheepWolf);
        northSheepWolf.setFailMessage("The sheep will eat the cabbage!");

        IInterpreter orNorth = new OrExpression(northSheepCabbage, northSheepWolf);
        orNorth.setFailMessage("You can't do that! They'll eat other!");
        IInterpreter northCondition = new NotExpression(orNorth);

        IInterpreter southCondition = new NotExpression(orSouth);

        ICommand crossNorth = new MovePlayerTo(game, southCondition, "cross");
        ICommand crossSouth = new MovePlayerTo(game, northCondition, "cross");

        this.setRiverCommands(crossNorth,crossSouth);
    }

    private void setRiverCommands(ICommand crossNorth, ICommand crossSouth) {
        riverSouthToNorth.addCommand(crossNorth);
        riverNorthToSouth.addCommand(crossSouth);
    }

    private void assignSimpleCommand() {
        ICommand drop = new DropOnPosition("leave", game);
        ICommand pick = new MoveToPlayer("take", game);

        wolf.addCommand(pick);
        wolf.addCommand(drop);

        sheep.addCommand(pick);
        sheep.addCommand(drop);

        cabbage.addCommand(pick);
        cabbage.addCommand(drop);
    }

    private void assignElementStates() {
        wolf.changeState("visible", true);
        sheep.changeState("visible", true);
        cabbage.changeState("visible", true);

        boat.setCapacity(1);

        riverSouthToNorth.changeState("visible", true);
        riverNorthToSouth.changeState("visible", true);

        riverSouthToNorth.setObjectiveElement(northShore);
        riverNorthToSouth.setObjectiveElement(southShore);

        southShore.addElement(riverSouthToNorth);
        northShore.addElement(riverNorthToSouth);
    }

    private void createElements() {
        wolf = new Element("wolf");
        sheep = new Element("sheep");
        cabbage = new Element("cabbage");

        northShore = new Element("north");
        southShore = new Element("south");

        southShore.addElement(wolf);
        southShore.addElement(sheep);
        southShore.addElement(cabbage);

        boat = new Element("boat");

        riverNorthToSouth = new Element("south-shore");
        riverSouthToNorth = new Element("north-shore");

        for (int i = 0; i < maxPlayers; i++) {
            Player newPlayer = new Player(i);
            players.add(newPlayer);
        }
        game.setPlayers(players);
    }

    private void createGameWinInterpreter() {
        ArrayList<String> winElements = new ArrayList<>();
        winElements.add("wolf");
        winElements.add("sheep");
        winElements.add("cabbage");

        winInterpreter = new ContainsElements(northShore,winElements);
    }

    private void setHelpAndExitCommand() {
        ICommand exit = new Exit(game);
        ICommand help = new Help("help", game);

        northShore.addCommand(help);
        northShore.addCommand(exit);
        southShore.addCommand(help);
        southShore.addCommand(exit);
    }

    @SuppressWarnings("CPD-END")

    private ArrayList<String> buildCondition(String elementOne, String elementTwo) {
        ArrayList<String> returnArray = new ArrayList<>();
        returnArray.add(elementOne);
        returnArray.add(elementTwo);
        return returnArray;
    }
}

