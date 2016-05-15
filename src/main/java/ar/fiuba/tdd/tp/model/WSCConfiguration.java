package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.DropOnPosition;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.MovePlayerTo;
import ar.fiuba.tdd.tp.icommand.MoveToPlayer;
import ar.fiuba.tdd.tp.interpreter.*;

import java.util.ArrayList;


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

    private IInterpreter winInterpreter;


    public Game build() {

        this.game = new Game("WSC");

        this.createElements();
        this.assignElementStates();
        this.assignSimpleCommand();
        this.assignComplexCommand();

        this.createGameWinInterpreter();

        game.setWinInterpreter(winInterpreter);
        game.setPlayer(boat);
        game.setPlayerPosition(southShore);

        return this.game;
    }

    private ArrayList<String> buildCondition(String elementOne, String elementTwo) {
        ArrayList<String> returnArray = new ArrayList<>();
        returnArray.add(elementOne);
        returnArray.add(elementTwo);
        return returnArray;
    }

    private void assignComplexCommand() {

        ArrayList<String> sheepCabbage = buildCondition("sheep", "cabbage");
        ArrayList<String> sheepWolf = buildCondition("wolf", "sheep");

        IInterpreter southSheepCabbage = new ContainsElements(southShore,sheepCabbage);
        IInterpreter southSheepWolf = new ContainsElements(southShore,sheepWolf);

        IInterpreter orSouth = new OrExpression(southSheepCabbage, southSheepWolf);
        IInterpreter southCondition = new NotExpression(orSouth);

        ICommand crossNorth = new MovePlayerTo(game, southCondition, "cross");
        //crossNorth.correctMovementMessage("You have crossed!");
        crossNorth.incorrectMovementMessage("You cant do that!");
        crossNorth.auxiliarMessage("They'll eat each other.");

        IInterpreter northSheepCabbage = new ContainsElements(northShore,sheepCabbage);
        IInterpreter northSheepWolf = new ContainsElements(northShore,sheepWolf);

        IInterpreter orNorth = new OrExpression(northSheepCabbage, northSheepWolf);
        IInterpreter northCondition = new NotExpression(orNorth);

        ICommand crossSouth = new MovePlayerTo(game, northCondition, "cross");
        //crossSouth.correctMovementMessage("You have crossed!");
        crossSouth.incorrectMovementMessage("You cant do that!");
        crossSouth.auxiliarMessage("They'll eat each other.");

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

        wolf.setState(true);
        sheep.setState(true);
        cabbage.setState(true);

        boat.setCapacity(1);

        southShore.addElement(boat);

        riverSouthToNorth.setState(true);
        riverNorthToSouth.setState(true);

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
    }

    private void createGameWinInterpreter() {

        ArrayList<String> winElements = new ArrayList<>();
        winElements.add("wolf");
        winElements.add("sheep");
        winElements.add("cabbage");

        winInterpreter = new ContainsElements(northShore,winElements);
    }


}
