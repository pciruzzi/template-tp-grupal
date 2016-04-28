package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.games.*;
import org.junit.Test;

import static ar.fiuba.tdd.tp.ConstantVariables.*;
import static org.junit.Assert.assertEquals;

public class FetchQuestTest {


    private Game initializeGame() {
        Game game = new FetchQuest();
        game.createGame();
        return game;
    }

    @Test
    public void testFetchQuestListElementsInRoom() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(lookArround),"There's a stick in the room.");
    }

    @Test
    public void testPickStickWinsGame() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(pickStick),wonGame);
    }

    @Test
    public void testOneWordCommandReturnError() {
        Game game = this.initializeGame();
        assertEquals(game.doAction("stick"),invalidInput);
    }

    @Test
    public void testPickElementNotOnListShowsError() {
        Game game = this.initializeGame();
        assertEquals(game.doAction(pickBroom),"It doesn't exist the item: broom or the action: pick.");
    }

}
