package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.model.*;
import org.junit.Test;

import static ar.fiuba.tdd.tp.Constants.*;
import static org.junit.Assert.assertEquals;

public class TresureQuestTest {

    private static final int id = 0;

    private Game initializeGame() {
        TreasureQuestConfiguration gameConfiguration = new TreasureQuestConfiguration();
        return gameConfiguration.build();
    }

//    @Test
//    public void lookArroundInFirstRoom() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        assertEquals("There's a door and a pokemon in the room.",game.play(id, "look around"));
//    }
//
//    @Test
//    public void canPickPokemonInRoom() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        assertEquals("You picked the pokemon",game.play(id, "pick", "pokemon"));
//    }
//
//    @Test
//    public void canOpenTheDoor() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        assertEquals("You have crossed",game.play(id, "open", "door"));
//    }
//
//    @Test
//    public void lookArroundInRoom2() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "open","door");
//        assertEquals("There's a door to three and a door to one in the room.",game.play(id, "look around"));
//    }
//
//    @Test
//    public void cantEnterRoomTwoWithoutPokeon() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "open","door");
//        assertEquals("The door is locked! You need a pokemon to open.",game.play(id, "open","door to three"));
//    }
//
//    @Test
//    public void canEnterRoomTwoWithPokeonShowElements() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "pick","pokemon");
//        game.play(id, "open","door");
//        game.play(id, "open","door to three");
//        assertEquals("There's a door to four, a key and a door to two in the room.",game.play(id, "look around"));
//    }
//
//    @Test
//    public void canEnterRoomFourAndShowElements() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "pick","pokemon");
//        game.play(id, "open","door");
//        game.play(id, "open","door to three");
//        game.play(id, "pick","key");
//        game.play(id, "open", "door to four");
//        assertEquals("There's a door to three, a door to five and a wardrobe in the room.",game.play(id, "look around"));
//    }
//
//    @Test
//    public void openWardrobeInRoomFourAndShowElements() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "pick","pokemon");
//        game.play(id, "open","door");
//        game.play(id, "open","door to three");
//        game.play(id, "pick","key");
//        game.play(id, "open", "door to four");
//        game.play(id, "open","wardrobe");
//        assertEquals("There's a door to three, a door to five, a chest and a wardrobe in the room.",game.play(id, "look around"));
//    }
//
//    @Test
//    public void openChestInsideWardbrobeShowsElements() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "pick","pokemon");
//        game.play(id, "open","door");
//        game.play(id, "open","door to three");
//        game.play(id, "pick","key");
//        game.play(id, "open", "door to four");
//        game.play(id, "open","wardrobe");
//        game.play(id, "open","chest");
//        assertEquals("There's a door to three, a door to five, a chest, "
//                + "a wardrobe and a antidote in the room.",game.play(id, "look around"));
//    }
//
//    @Test
//    public void openChestInsideWardbrobeAndPickAntidote() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "pick","pokemon");
//        game.play(id, "open","door");
//        game.play(id, "open","door to three");
//        game.play(id, "drop","pokemon");
//        game.play(id, "pick","key");
//        game.play(id, "open", "door to four");
//        game.play(id, "open","wardrobe");
//        game.play(id, "open","chest");
//        assertEquals("You picked the antidote",game.play(id, "pick","antidote"));
//    }
//
//    @Test
//    public void enterRoom5AndLookAround() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "pick","pokemon");
//        game.play(id, "open","door");
//        game.play(id, "open","door to three");
//        game.play(id, "drop","pokemon");
//        game.play(id, "pick","key");
//        game.play(id, "open", "door to four");
//        game.play(id, "open","wardrobe");
//        game.play(id, "open","chest");
//        game.play(id, "pick", "antidote");
//        game.play(id, "open","door to five");
//        assertEquals("There's a box two, a door to one, a box one and a door to four in the room.",game.play(id, "look around"));
//    }
//
//    @Test
//    public void openBoxOnePoisonsAndAntidoteCures() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "pick","pokemon");
//        game.play(id, "open","door");
//        game.play(id, "open","door to three");
//        game.play(id, "drop","pokemon");
//        game.play(id, "pick","key");
//        game.play(id, "open", "door to four");
//        game.play(id, "open","wardrobe");
//        game.play(id, "open","chest");
//        game.play(id, "pick", "antidote");
//        game.play(id, "open","door to five");
//        assertEquals("The box one is opened.\n"
//                + "You have been posioned! :(\n"
//                + "You have an antidote in the inventory, you have been cured! :D",game.play(id, "open", "box one"));
//    }
//
//    @Test
//    public void enterPoisonedToRoom4LoosesTheGame() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "pick","pokemon");
//        game.play(id, "open","door");
//        game.play(id, "open","door to three");
//        game.play(id, "drop","pokemon");
//        game.play(id, "pick","key");
//        game.play(id, "open", "door to four");
//        game.play(id, "open","wardrobe");
//        game.play(id, "open","chest");
//        game.play(id, "open","door to five");
//        game.play(id, "open", "box one");
//        assertEquals(GAME_LOST,game.play(id, "open", "door to four"));
//    }
//
//    @Test
//    public void enterRoom1WithTreasureWinsGame() {
//        Game game = this.initializeGame();
//        game.createPlayer(0);
//        game.play(id, "pick","pokemon");
//        game.play(id, "open","door");
//        game.play(id, "open","door to three");
//        game.play(id, "drop","pokemon");
//        game.play(id, "pick","key");
//        game.play(id, "open", "door to four");
//        game.play(id, "open","wardrobe");
//        game.play(id, "open","chest");
//        game.play(id, "open","door to five");
//        game.play(id, "open", "box two");
//        game.play(id, "pick","treasure");
//        assertEquals(GAME_WON,game.play(id, "open","door to one"));
//    }
}