package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.games.*;

/**
 * Created by gg on 4/28/2016.
 */
public class TresureQuest {

    private Game initializeGame() {
        Game game = new TreasureQuest();
        game.createGame();
        return game;
    }


}
