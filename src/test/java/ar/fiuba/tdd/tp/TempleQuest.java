package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class TempleQuest extends InitializationsForTests {

    private static final int id = 0;

    @Test
    public void testLookAroundShowsSkeletonDoorChestAndMonkey() {
        Engine engine = initializeEngineTempleQuest();
        assertEquals(engine.doCommand(id,"look around"), "There's a skeleton, a door, a chest and a monkey in the room.");
    }

    @Test
    public void testLookAroundSearchSkeletonShowsApple() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"search skeleton");
        assertEquals(engine.doCommand(id,"look around"), "There's a apple, a skeleton, a door, a chest and a monkey in the room.");
    }

    @Test
    public void testYouCantOpenTheDoorWithoutWakingUpTheMonkey() {
        Engine engine = initializeEngineTempleQuest();
        assertEquals(engine.doCommand(id,"open door"), "Ey! Where do you go, you have to pull both lever at the same time");
    }

    @Test
    public void testYouCanCrossAfterWakingUpTheMonkey() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"wake up monkey");
        assertEquals(engine.doCommand(id,"open door"), "You have crossed");
    }

    @Test
    public void testYouCanCrossTheRiverWithDiskNine() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"wake up monkey");
        engine.doCommand(id,"open door");
        engine.doCommand(id,"pick disk nine");
        assertEquals(engine.doCommand(id,"cross river"), "You have crossed");
    }

    @Test
    public void testAfterCrossingTheRiverAndDoorTheresTheExitDoor() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"wake up monkey");
        engine.doCommand(id,"open door");
        engine.doCommand(id,"pick disk nine");
        engine.doCommand(id,"cross river");
        assertEquals(engine.doCommand(id,"look around"),"There's a door and a river in the room.");
    }

    @Test
    public void testYouCantOpenTheDoorWithoutDiskNine() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"wake up monkey");
        engine.doCommand(id,"open door");
        engine.doCommand(id,"cross river");
        assertEquals(engine.doCommand(id,"open door"),"You need the magic stone to open the door!");

    }

    @Test
    public void testTheGameIsWonWhenPlayerOpensDoorToOutside() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"wake up monkey");
        engine.doCommand(id,"open door");
        engine.doCommand(id,"pick disk nine");
        engine.doCommand(id,"cross river");
        engine.doCommand(id,"open door");
        assertEquals(engine.doCommand(id,"open door outside"),"You won!!!");
    }

    @Test
    public void testCheckTopPillarOneIsNine() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"wake up monkey");
        engine.doCommand(id,"open door");
        assertEquals(engine.doCommand(id,"check top pillar one"),"The size of the top is 1.");
    }

    @Test
    public void testMoveTopPillarOneToPillarTwo() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"wake up monkey");
        engine.doCommand(id,"open door");
        assertEquals(engine.doCommand(id,"move top pillar one pillar two"),"You moved the disk!");
    }


    @Test
    public void testMoveTopPillarThreeToPillarTwoIsInvalid() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"wake up monkey");
        engine.doCommand(id,"open door");
        assertEquals(engine.doCommand(id,"move top pillar three pillar two"),"You can't move a bigger disk over a smaller one!");
    }

    @Test
    public void testMoveTopPillarOneToPillarThreeChangesCheckTopSize() {
        Engine engine = initializeEngineTempleQuest();
        engine.doCommand(id,"wake up monkey");
        engine.doCommand(id,"open door");
        assertEquals(engine.doCommand(id,"check top pillar three"),"The size of the top is 7.");
        engine.doCommand(id,"move top pillar one pillar three");
        assertEquals(engine.doCommand(id,"check top pillar three"),"The size of the top is 1.");
    }

}
