package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.ElementTwo;
import ar.fiuba.tdd.tp.icommand.DropOnPosition;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.icommand.MovePlayerTo;
import ar.fiuba.tdd.tp.icommand.MoveToPlayer;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.interpreter.OrExpression;

import java.util.ArrayList;


public class WSCConfiguration implements GameBuilder{


    public Game build() {

        Game game = new Game("WSC");

        /*
        ElementTwo wolf = new ElementTwo("wolf",true);
        ElementTwo sheep = new ElementTwo("sheep",true);
        ElementTwo cabbage = new ElementTwo("cabbage",true);

        ElementTwo northShore = new ElementTwo("north-shore", true);
        ElementTwo southShore = new ElementTwo("south-shore", true);

        southShore.addElement(wolf);
        southShore.addElement(sheep);
        southShore.addElement(cabbage);

        ElementTwo farmer = new ElementTwo("farmer", true);

        ArrayList<String> wolfConditionElements = new ArrayList<>();
        wolfConditionElements.add("sheep");
        wolfConditionElements.add("cabbage");

        IInterpreter pickWolfSouth = new ContainsElements(southShore,wolfConditionElements);
        IInterpreter pickWolfNorth = new ContainsElements(northShore,wolfConditionElements);

        IInterpreter pickWolfCondition = new OrExpression(pickWolfNorth,pickWolfSouth);

        ArrayList<String> cabbageConditionElements = new ArrayList<>();
        cabbageConditionElements.add("wolf");
        cabbageConditionElements.add("sheep");

        IInterpreter pickCabbageSouth = new ContainsElements(southShore,cabbageConditionElements);
        IInterpreter pickCabbageNorth = new ContainsElements(northShore,cabbageConditionElements);

        IInterpreter pickCabbageCondition = new OrExpression(pickCabbageSouth,pickCabbageNorth);

        ICommand pickWolf = new MoveToPlayer(game,pickWolfCondition);
        ICommand pickCabbage = new MoveToPlayer(game, pickCabbageCondition);
        ICommand pick = new MoveToPlayer(game);


        ICommand drop = new DropOnPosition(game);

        wolf.addCommand(pickWolf);
        sheep.addCommand(pick);
        cabbage.addCommand(pickCabbage);

        wolf.addCommand(drop);
        sheep.addCommand(drop);
        cabbage.addCommand(drop);

        IInterpreter condition = new
        ICommand cross = new MovePlayerTo(game);

        farmer.addCommand(cross);

        ArrayList<String> winElements = new ArrayList<>();
        winElements.add("wolf");
        winElements.add("sheep");
        winElements.add("cabbage");

        IInterpreter winInterpreter = new ContainsElements(northShore,winElements);

        game.setWinInterpreter(winInterpreter);
        game.setPlayer(farmer);
        game.setPlayerPosition(southShore);
*/
        return game;
    }

}
