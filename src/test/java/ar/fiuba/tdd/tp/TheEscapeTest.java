package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TheEscapeTest {

    private GameDriver initializeGameDriver() throws GameLoadFailedException {
        GameDriver driver = new DriverImplementation();
        driver.initGame("build/classes/main/ar/fiuba/tdd/tp/model/TheEscapeConfiguration.jar");
        assertEquals(GameState.Ready, driver.getCurrentState());
        return driver;
    }

    @Test
    public void itShouldLostIfDownloadUsingStairs() throws GameLoadFailedException {
        GameDriver driver = initializeGameDriver();
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte using Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto in Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial in Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Escalera");
        assertEquals(GameState.Lost, driver.getCurrentState());
    }

    @Test
    public void itShouldLostIfGotoBasementWithoutAHammer() throws GameLoadFailedException {
        GameDriver driver = initializeGameDriver();
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerte using Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto in Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial in Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Baranda");
        assertEquals(GameState.Lost, driver.getCurrentState());
    }

    @Test
    public void itShouldWinIfGotoBasementWithAHammer() throws GameLoadFailedException {
        GameDriver driver = initializeGameDriver();
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon3");
        driver.sendCommand("pick Llave");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon2");
        driver.sendCommand("pick Martillo");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto Salon1");
        driver.sendCommand("move CuadroBarco");
        driver.sendCommand("open CajaFuerta using Llave");
        driver.sendCommand("pick Credencial");
        driver.sendCommand("put Foto in Credencial");
        driver.sendCommand("goto Pasillo");
        driver.sendCommand("goto BibliotecaAcceso");
        driver.sendCommand("show Credencial in Bibliotecario");
        driver.sendCommand("goto Biblioteca");
        driver.sendCommand("move LibroViejo");
        driver.sendCommand("goto Sotano");
        driver.sendCommand("use Baranda");
        driver.sendCommand("break Ventana using Martillo");
        driver.sendCommand("goto Afuera");
        assertEquals(GameState.Won, driver.getCurrentState());
    }
}
