package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.*;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.TheEscapeConfiguration;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.server.queue.EventQueue;
import org.junit.Test;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;
import static org.junit.Assert.assertEquals;

public class TheEscapeTest {

    private Engine engine;

//    @Test
//    public void itShouldLostIfDownloadUsingStairs() throws GameLoadFailedException {
//        GameDriver driver = getGameDriver();
//        try {
//            driver.sendCommand("goto Salon1", 0);
//            driver.sendCommand("pick Botella", 0);
//            driver.sendCommand("goto Pasillo", 0);
//            driver.sendCommand("goto BibliotecaAcceso", 0);
//            driver.sendCommand("goto Sotano", 0);
//            driver.sendCommand("use Escalera", 0 );
//        } catch (UnknownPlayerException e) {
//            e.printStackTrace();
//        }
//
//        assertEquals(GameState.Lost, driver.getCurrentState());
//    }
//
//    @Test
//    public void itShouldLostIfGotoBasementWithoutAHammer() throws GameLoadFailedException {
//        GameDriver driver = getGameDriver();
//        execute5Steps(driver);
//        driver.sendCommand("goto Salon1");
//        driver.sendCommand("move CuadroBarco");
//        driver.sendCommand("open CajaFuerte using Llave");
//        enterToTheLibrary(driver);
//        driver.sendCommand("move LibroViejo");
//        driver.sendCommand("goto Sotano");
//        driver.sendCommand("use Baranda");
//        assertEquals(GameState.Lost, driver.getCurrentState());
//    }
//
//    @Test
//    public void itShouldWinIfGotoBasementWithAHammer() throws GameLoadFailedException {
//        GameDriver driver = getGameDriver();
//        execute5Steps(driver);
//        driver.sendCommand("goto Salon2");
//        driver.sendCommand("pick Martillo");
//        driver.sendCommand("goto Pasillo");
//        driver.sendCommand("drop Lapicera");
//        driver.sendCommand("goto Salon1");
//        driver.sendCommand("move CuadroBarco");
//        driver.sendCommand("open CajaFuerte using Llave");
//        enterToTheLibrary(driver);
//        driver.sendCommand("move LibroViejo");
//        driver.sendCommand("goto Sotano");
//        driver.sendCommand("use Baranda");
//        driver.sendCommand("break Ventana using Martillo");
//        driver.sendCommand("goto Afuera");
//        assertEquals(GameState.Won, driver.getCurrentState());
//    }
//
//    private void enterToTheLibrary(GameDriver driver) {
//        driver.sendCommand("pick Credencial");
//        driver.sendCommand("put Foto in Credencial");
//        driver.sendCommand("goto Pasillo");
//        driver.sendCommand("goto BibliotecaAcceso");
//        driver.sendCommand("show credencial Bibliotecario");
//        driver.sendCommand("goto Biblioteca");
//    }
//
//    private GameDriver getGameDriver() throws GameLoadFailedException {
//        GameDriver driver = new DriverImplementation();
//        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/TheEscapeConfiguration.jar");
//        assertEquals(GameState.Ready, driver.getCurrentState());
//        return driver;
//    }
//
//    private void execute5Steps(GameDriver driver) {
//        driver.sendCommand("goto BibliotecaAcceso");
//        driver.sendCommand("goto Pasillo");
//        driver.sendCommand("goto Salon3");
//        driver.sendCommand("pick Llave");
//        driver.sendCommand("goto Pasillo");
//    }


    @Test
    public void itShouldLostIfDownloadUsingStairs() throws GameLoadFailedException {
        setUp();
        int player0 = 0;
        execute5Steps(player0);
        engine.doCommand(player0, "goto Salon1");
        engine.doCommand(player0, "move CuadroBarco");
        engine.doCommand(player0, "open CajaFuerte using Llave");
        enterToTheLibrary(player0);
        engine.doCommand(player0, "move LibroViejo");
        engine.doCommand(player0, "goto Sotano");
        assertEquals(GAME_LOST, engine.doCommand(player0, "use Escalera"));
    }

    @Test
    public void itShouldLostIfGotoBasementWithoutAHammer() throws GameLoadFailedException {
        setUp();
        int player0 = 0;
        execute5Steps(player0);
        engine.doCommand(player0, "goto Salon1");
        engine.doCommand(player0, "move CuadroBarco");
        engine.doCommand(player0, "open CajaFuerte using Llave");
        enterToTheLibrary(player0);
        engine.doCommand(player0, "move LibroViejo");
        engine.doCommand(player0, "goto Sotano");
        assertEquals(GAME_LOST, engine.doCommand(player0, "use Baranda"));
    }

    @Test
    public void itShouldWinIfGoToBasementWithAHammer() throws GameLoadFailedException {
        setUp();
        int player0 = 0;
        execute5Steps(player0);
        engine.doCommand(player0, "goto Salon2");
        engine.doCommand(player0, "pick Martillo");
        engine.doCommand(player0, "goto Pasillo");
        engine.doCommand(player0, "drop Lapicera");
        engine.doCommand(player0, "goto Salon1");
        engine.doCommand(player0, "move CuadroBarco");
        engine.doCommand(player0, "open CajaFuerte using Llave");
        enterToTheLibrary(player0);
        engine.doCommand(player0, "move LibroViejo");
        engine.doCommand(player0, "goto Sotano");
        engine.doCommand(player0, "use Baranda");
        engine.doCommand(player0, "break Ventana using Martillo");
        assertEquals(GAME_WON, engine.doCommand(player0, "goto Afuera"));
    }

    private void enterToTheLibrary(int id) {
        engine.doCommand(id, "pick Credencial");
        engine.doCommand(id, "put Foto in Credencial");
        engine.doCommand(id, "goto Pasillo");
        engine.doCommand(id, "goto BibliotecaAcceso");
        engine.doCommand(id, "show credencial Bibliotecario");
        engine.doCommand(id, "goto Biblioteca");
    }

    private void execute5Steps(int id) {
        engine.doCommand(id, "goto BibliotecaAcceso");
        engine.doCommand(id, "goto Pasillo");
        engine.doCommand(id, "goto Salon3");
        engine.doCommand(id, "pick Llave");
        engine.doCommand(id, "goto Pasillo");
    }

    private void setUp() {
        GameBuilder gameBuilder = new TheEscapeConfiguration();
        BroadcastQueue queue = new EventQueue();

        engine = new Engine(queue);
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer();
    }
}

