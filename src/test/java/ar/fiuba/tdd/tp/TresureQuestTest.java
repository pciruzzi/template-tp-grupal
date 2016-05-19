package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.model.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TresureQuestTest {

    private Game initializeGame() {
        TreasureQuestConfiguration gameConfiguration = new TreasureQuestConfiguration();
        Game game = gameConfiguration.build();
        return game;
    }

    @Test
    public void lookArroundInFirstRoom() {
        Game game = this.initializeGame();
        assertEquals("There's a door and a pokemon in the room.",game.play("look around"));
    }

    @Test
    public void canPickPokemonInRoom() {
        Game game = this.initializeGame();
        assertEquals("You picked the pokemon",game.play("pick", "pokemon"));
    }

    @Test
    public void canOpenTheDoor() {
        Game game = this.initializeGame();
        assertEquals("You have crossed",game.play("open", "door"));
    }

    @Test
    public void lookArroundInRoom2() {
        Game game = this.initializeGame();
        game.play("open","door");
        assertEquals("There's a door to three and a door to one in the room.",game.play("look around"));
    }

    @Test
    public void cantEnterRoomTwoWithoutPokeon() {
        Game game = this.initializeGame();
        game.play("open","door");
        assertEquals("The door is locked! You need a pokemon to open.",game.play("open","door to three"));
    }

    @Test
    public void canEnterRoomTwoWithPokeonShowElements() {
        Game game = this.initializeGame();
        game.play("pick","pokemon");
        game.play("open","door");
        game.play("open","door to three");
        assertEquals("There's a door to four, a key and a door to two in the room.",game.play("look around"));
    }

    @Test
    public void canEnterRoomFourAndShowElements() {
        Game game = this.initializeGame();
        game.play("pick","pokemon");
        game.play("open","door");
        game.play("open","door to three");
        game.play("pick","key");
        game.play("open", "door to four");
        assertEquals("There's a door to three, a door to five and a wardrobe in the room.",game.play("look around"));
    }

    @Test
    public void openWardrobeInRoomFourAndShowElements() {
        Game game = this.initializeGame();
        game.play("pick","pokemon");
        game.play("open","door");
        game.play("open","door to three");
        game.play("pick","key");
        game.play("open", "door to four");
        game.play("open","wardrobe");
        assertEquals("There's a door to three, a door to five, a chest and a wardrobe in the room.",game.play("look around"));
    }

    @Test
    public void openChestInsideWardbrobeShowsElements() {
        Game game = this.initializeGame();
        game.play("pick","pokemon");
        game.play("open","door");
        game.play("open","door to three");
        game.play("pick","key");
        game.play("open", "door to four");
        game.play("open","wardrobe");
        game.play("open","chest");
        assertEquals("There's a door to three, a door to five, a chest, a wardrobe and a antidote in the room.",game.play("look around"));
    }

    @Test
    public void openChestInsideWardbrobeAndPickAntidote() {
        Game game = this.initializeGame();
        game.play("pick","pokemon");
        game.play("open","door");
        game.play("open","door to three");
        game.play("drop","pokemon");
        game.play("pick","key");
        game.play("open", "door to four");
        game.play("open","wardrobe");
        game.play("open","chest");
        assertEquals("You picked the antidote",game.play("pick","antidote"));
    }

    @Test
    public void enterRoom5AndLookAround() {
        Game game = this.initializeGame();
        game.play("pick","pokemon");
        game.play("open","door");
        game.play("open","door to three");
        game.play("drop","pokemon");
        game.play("pick","key");
        game.play("open", "door to four");
        game.play("open","wardrobe");
        game.play("open","chest");
        game.play("pick", "antidote");
        game.play("open","door to five");
        assertEquals("There's a box two, a door to one, a box one and a door to four in the room.",game.play("look around"));
    }

    @Test
    public void openBoxOnePoisonsAndAntidoteCures() {
        Game game = this.initializeGame();
        game.play("pick","pokemon");
        game.play("open","door");
        game.play("open","door to three");
        game.play("drop","pokemon");
        game.play("pick","key");
        game.play("open", "door to four");
        game.play("open","wardrobe");
        game.play("open","chest");
        game.play("pick", "antidote");
        game.play("open","door to five");
        assertEquals("The box one is opened.\n"
                + "You have been posionnd! :(\n"
                + "You have an antidote in the inventory, you have been cured! :D",game.play("open", "box one"));
    }

    @Test
    public void enterPoisonedToRoom4LoosesTheGame() {
        Game game = this.initializeGame();
        game.play("pick","pokemon");
        game.play("open","door");
        game.play("open","door to three");
        game.play("drop","pokemon");
        game.play("pick","key");
        game.play("open", "door to four");
        game.play("open","wardrobe");
        game.play("open","chest");
        game.play("open","door to five");
        game.play("open", "box one");
        assertEquals("You lost!!!",game.play("open", "door to four"));
    }

    @Test
    public void enterRoom1WithTreasureWinsGame() {
        Game game = this.initializeGame();
        game.play("pick","pokemon");
        game.play("open","door");
        game.play("open","door to three");
        game.play("drop","pokemon");
        game.play("pick","key");
        game.play("open", "door to four");
        game.play("open","wardrobe");
        game.play("open","chest");
        game.play("open","door to five");
        game.play("open", "box two");
        game.play("pick","treasure");
        assertEquals("You won!!!",game.play("open","door to one"));
    }
}