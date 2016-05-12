package ar.fiuba.tdd.tp.model;


public class WSCConfiguration implements GameBuilder{


    public Game build() {

        Game game = new Game("WSC");
//
//
//        Element wolf = new Element("wolf",true);
//        Element sheep = new Element("sheep",true);
//        Element cabbage = new Element("cabbage",true);
//
//        Element northShore = new Element("north-shore", true);
//        Element southShore = new Element("south-shore", true);
//
//        southShore.addElement(wolf);
//        southShore.addElement(sheep);
//        southShore.addElement(cabbage);
//
//        Element farmer = new Element("farmer", true);
//
//        ArrayList<String> wolfConditionElements = new ArrayList<>();
//        wolfConditionElements.add("sheep");
//        wolfConditionElements.add("cabbage");
//
//        IInterpreter pickWolfSouth = new ContainsElements(southShore,wolfConditionElements);
//        IInterpreter pickWolfNorth = new ContainsElements(northShore,wolfConditionElements);
//
//        IInterpreter pickWolfCondition = new OrExpression(pickWolfNorth,pickWolfSouth);
//
//        ArrayList<String> cabbageConditionElements = new ArrayList<>();
//        cabbageConditionElements.add("wolf");
//        cabbageConditionElements.add("sheep");
//
//        IInterpreter pickCabbageSouth = new ContainsElements(southShore,cabbageConditionElements);
//        IInterpreter pickCabbageNorth = new ContainsElements(northShore,cabbageConditionElements);
//
//        IInterpreter pickCabbageCondition = new OrExpression(pickCabbageSouth,pickCabbageNorth);
//
//        ICommand pickWolf = new MoveToPlayer(game,pickWolfCondition);
//        ICommand pickCabbage = new MoveToPlayer(game, pickCabbageCondition);
//        ICommand pick = new MoveToPlayer(game);
//
//
//        ICommand drop = new DropOnPosition(game);
//
//        wolf.addCommand(pickWolf);
//        sheep.addCommand(pick);
//        cabbage.addCommand(pickCabbage);
//
//        wolf.addCommand(drop);
//        sheep.addCommand(drop);
//        cabbage.addCommand(drop);
//
//        IInterpreter condition = new
//        ICommand cross = new MovePlayerTo(game);
//
//        farmer.addCommand(cross);
//
//        ArrayList<String> winElements = new ArrayList<>();
//        winElements.add("wolf");
//        winElements.add("sheep");
//        winElements.add("cabbage");
//
//        IInterpreter winInterpreter = new ContainsElements(northShore,winElements);
//
//        game.setWinInterpreter(winInterpreter);
//        game.setPlayer(farmer);
//        game.setPlayerPosition(southShore);

        return game;
    }

}
