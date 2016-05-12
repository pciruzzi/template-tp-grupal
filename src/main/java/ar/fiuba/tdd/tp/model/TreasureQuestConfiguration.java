package ar.fiuba.tdd.tp.model;

public class TreasureQuestConfiguration implements GameBuilder {

    public Game build() {

        Game game = new Game("Treasure Quest");

//        // Creo los elementos
//        Element key = new Element("key", true);
//        Element door = new Element("door", true);
//        Element roomOne = new Element("roomOne", true);
//
//
//        // Combino los elementos
//
//        // Agrego las acciones
//
//        // Creo las formas de ganar
//        ArrayList<String> winArray = new ArrayList<>();
//        winArray.add(key.getName());
//
//        IInterpreter winInterpreter = new ContainsElements(roomOne,winArray);
//
//        // Seteo las formas de ganar
//        game.setWinInterpreter(winInterpreter);
//
//        // Agrego la posicion del player
//        game.setPlayerPosition(roomOne);
        return game;
    }
}
