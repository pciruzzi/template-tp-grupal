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
    private Game game;

    private IInterpreter winInterpreter;


    public Game build() {

        this.game = new Game("WSC");

        this.createGameElements();
        this.createGameCommands();
        this.createGameWinInterpreter();

        game.setWinInterpreter(winInterpreter);
        game.setPlayer(boat);
        game.setPlayerPosition(southShore);

        return this.game;
    }

    private void createGameElements() {

        wolf = new Element("wolf");
        sheep = new Element("sheep");
        cabbage = new Element("cabbage");

        wolf.setState(true);
        sheep.setState(true);
        cabbage.setState(true);

        northShore = new Element("north-shore");
        southShore = new Element("south-shore");

        northShore.setState(true);
        southShore.setState(true);

        southShore.addElement(wolf);
        southShore.addElement(sheep);
        southShore.addElement(cabbage);

        southShore.addElement(northShore);
        northShore.addElement(southShore);

        northShore.setObjectiveElement(northShore);
        southShore.setObjectiveElement(southShore);

        boat = new Element("boat");
        southShore.addElement(boat);

    }

    private void createSimpleCommands() {
        ICommand drop = new DropOnPosition("leave", game);
        ICommand pick = new MoveToPlayer("take", game);

        wolf.addCommand(pick);
        wolf.addCommand(drop);

        sheep.addCommand(pick);
        sheep.addCommand(drop);

        cabbage.addCommand(pick);
        cabbage.addCommand(drop);
    }

    private void createGameCommands() {

        this.createSimpleCommands();

        ArrayList<String> sheepCabbage = new ArrayList<>();
        sheepCabbage.add("sheep");
        sheepCabbage.add("cabbage");


        ArrayList<String> sheepWolf = new ArrayList<>();
        sheepWolf.add("wolf");
        sheepWolf.add("sheep");

        IInterpreter southSheepCabbage = new ContainsElements(southShore,sheepCabbage);
        IInterpreter southSheepWolf = new ContainsElements(southShore,sheepWolf);

        IInterpreter orSouth = new OrExpression(southSheepCabbage, southSheepWolf);
        IInterpreter southCondition = new NotExpression(orSouth);

        ICommand crossNorth = new MovePlayerTo(game, southCondition, "cross");

        IInterpreter northSheepCabbage = new ContainsElements(northShore,sheepCabbage);
        IInterpreter northSheepWolf = new ContainsElements(northShore,sheepWolf);

        IInterpreter orNorth = new OrExpression(northSheepCabbage, northSheepWolf);
        IInterpreter northCondition = new NotExpression(orNorth);

        ICommand crossSouth = new MovePlayerTo(game, northCondition, "cross");

        northShore.addCommand(crossNorth);
        southShore.addCommand(crossSouth);

    }

    private void createGameWinInterpreter() {

        ArrayList<String> winElements = new ArrayList<>();
        winElements.add("wolf");
        winElements.add("sheep");
        winElements.add("cabbage");

        winInterpreter = new ContainsElements(northShore,winElements);
    }


}
