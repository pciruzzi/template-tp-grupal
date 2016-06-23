package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.driver.*;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.icommand.MoveRandom;
import ar.fiuba.tdd.tp.mocks.MockedTimedCommand;
import ar.fiuba.tdd.tp.mocks.MockedTimer;
import ar.fiuba.tdd.tp.model.GameBuilder;
import ar.fiuba.tdd.tp.model.TheEscape2Configuration;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.server.queue.EventQueue;
import org.junit.Test;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class TheEscape2Test {

    private Engine engine;
    private MockedTimedCommand enojar;
    private MockedTimedCommand despertar;
    private MockedTimedCommand mover;
    private MockedTimer mockedTimer;

    @Test
    public void shouldLostWhenMeetsAngryBibliotecario() throws GameLoadFailedException {
        setUpTestOne();

        engine.doCommand(0, "goto Salon1");
        engine.doCommand(0, "pick Botella");
        engine.doCommand(0, "goto Pasillo");
        engine.doCommand(0, "goto BibliotecaAcceso");
        engine.doCommand(0, "look around");
        engine.doCommand(0, "ask Bibliotecario");
        engine.doCommand(0, "give Licor Bibliotecario");
        engine.doCommand(0, "goto doorBiblioteca");

        mockedTimer.avanzarTiempo(120000);

        assertEquals(GAME_LOST, engine.doCommand(0, "goto BibliotecaAcceso"));
    }

    @Test
    public void shouldLostWhenBibliotecarioFoundsPlayer1InBiblioteca() throws GameLoadFailedException {
        setUpTestTwo();

        engine.createPlayer();
        engine.doCommand(1, "goto BibliotecaAcceso");
        engine.doCommand(1, "goto doorBiblioteca");

        engine.doCommand(0, "goto Salon1");
        engine.doCommand(0, "pick Botella");
        engine.doCommand(0, "goto Pasillo");
        engine.doCommand(0, "goto BibliotecaAcceso");
        engine.doCommand(0, "give Licor Bibliotecario");
        engine.doCommand(0, "goto doorBiblioteca");

        engine.doCommand(1, "goto BibliotecaAcceso");
        engine.doCommand(1, "goto Pasillo");

        assertEquals(GAME_LOST, mockedTimer.avanzarTiempo(120000));
    }

    //TESTS QUE SON TAMBIEN DE "THEESCAPE"
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

        BroadcastQueue queue = new EventQueue();

        GameBuilder gameBuilder = new TheEscape2Configuration();

        engine = new Engine(queue);
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer();

    }

    private void setUpTestOne() {
        BroadcastQueue queue = new EventQueue();
        mockedTimer = new MockedTimer(0);

        enojar      = new MockedTimedCommand("enojar Bibliotecario", queue);
        despertar   = new MockedTimedCommand("despertar Bibliotecario", queue);
        mockedTimer.addAction(120000, enojar);
        mockedTimer.addAction(120000, despertar);

        MoveRandom moveRandom = new MoveRandom("move");

        GameBuilder gameBuilder = new TheEscape2Configuration(despertar, enojar, mover, moveRandom, "Biblioteca");

        engine = new Engine(queue);
        engine.setTimer(mockedTimer);
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer();
    }

    private void setUpTestTwo() {

        BroadcastQueue queue = new EventQueue();
        mockedTimer = new MockedTimer(0);

        enojar      = new MockedTimedCommand("enojar Bibliotecario", queue);
        despertar   = new MockedTimedCommand("despertar Bibliotecario", queue);
        mover       = new MockedTimedCommand("move Bibliotecario", queue);

        mockedTimer.addAction(120000, enojar);
        mockedTimer.addAction(120000, despertar);
        mockedTimer.addAction(120000, mover);
        MoveRandom moveRandom = new MoveRandom("move");

        GameBuilder gameBuilder = new TheEscape2Configuration(despertar, enojar, mover, moveRandom, "Biblioteca");

        engine = new Engine(queue);
        engine.setTimer(mockedTimer);
        engine.createGame(gameBuilder);
        engine.getGame().createPlayer();
    }
}
