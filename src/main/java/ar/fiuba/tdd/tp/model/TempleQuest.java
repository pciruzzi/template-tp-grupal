package ar.fiuba.tdd.tp.model;

public class TempleQuest implements GameBuilder {

    private Game game;
    @Override
    public Game build() {
        
        game = new Game("Temple Quest");

        return game;
    }
}
