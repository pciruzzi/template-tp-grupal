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

