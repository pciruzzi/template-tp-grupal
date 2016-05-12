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
        cabbage.addCommand(pick);


        sheep.addCommand(drop);
        cabbage.addCommand(drop);
    }

    private void createGameCommands() {

        ArrayList<String> wolfConditionElements = new ArrayList<>();
        wolfConditionElements.add("sheep");
        wolfConditionElements.add("cabbage");


        ArrayList<String> cabbageConditionElements = new ArrayList<>();
        cabbageConditionElements.add("wolf");
        cabbageConditionElements.add("sheep");


        IInterpreter wolfCrossNorthCondition = new DoesNotContainElements(southShore, wolfConditionElements);
        IInterpreter cabbageCrossNorthCondition = new DoesNotContainElements(southShore, cabbageConditionElements);


        IInterpreter crossNorthCondition = new AndExpression(wolfCrossNorthCondition, cabbageCrossNorthCondition);

        IInterpreter wolfCrossSouthCondition = new DoesNotContainElements(northShore, wolfConditionElements);
        IInterpreter cabbageCrossSouthCondition = new DoesNotContainElements(northShore, cabbageConditionElements);

        IInterpreter crossSouthCondition = new AndExpression(wolfCrossSouthCondition, cabbageCrossSouthCondition);

        ICommand crossNorth = new MovePlayerTo(game, crossNorthCondition, "cross");
        ICommand crossSouth = new MovePlayerTo(game, crossSouthCondition, "cross");

        northShore.addCommand(crossSouth);
        southShore.addCommand(crossNorth);

    }

    private void createGameWinInterpreter() {

        ArrayList<String> winElements = new ArrayList<>();
        winElements.add("wolf");
        winElements.add("sheep");
        winElements.add("cabbage");

        winInterpreter = new ContainsElements(northShore,winElements);
    }


}
