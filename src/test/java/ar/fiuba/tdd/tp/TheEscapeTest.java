package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.driver.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TheEscapeTest {

//    @Test
//    public void itShouldLostIfDownloadUsingStairs() throws GameLoadFailedException {
//        GameDriver driver = getGameDriver();
//        execute5Steps(driver);
//        driver.sendCommand("goto Salon1");
//        driver.sendCommand("move CuadroBarco");
//        driver.sendCommand("open CajaFuerte using Llave");
//        enterToTheLibrary(driver);
//        driver.sendCommand("move LibroViejo");
//        driver.sendCommand("goto Sotano");
//        driver.sendCommand("use Escalera");
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
}
