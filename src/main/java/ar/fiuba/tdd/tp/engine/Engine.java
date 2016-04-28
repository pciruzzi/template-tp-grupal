package ar.fiuba.tdd.tp.engine;


import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.exceptions.GameNameException;
import ar.fiuba.tdd.tp.games.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ar.fiuba.tdd.tp.Constants.*;


public class Engine {

    private List<Game> gameList;
    private Game juego;

    public static boolean canCreate(String gameName) {

        Engine engine = new Engine();

        return engine.canBeCreated(gameName);
    }

    public Engine() {

        gameList = new ArrayList<Game>();

//        gameList.add(new EvilThing());
//        gameList.add(new OpenDoor2());
//        gameList.add(new TreasureQuest());
        gameList.add(new FetchQuest());
        gameList.add(new HanoiTowers());
        gameList.add(new OpenDoor());
        gameList.add(new WolfSheepAndCabbage());
    }

    private boolean canBeCreated(String gameName) {

        for (Game game : gameList) {
            if (game.checkGameName(gameName)) {
                return true;
            }
        }
        return false;
    }

    private Game pickGame(String gameName) throws GameNameException {

        for (Game game : gameList) {
            if (game.checkGameName(gameName)) {
                return game.copy();
            }
        }
        throw new GameNameException("Juego invalido");
    }

    public void generarJuego() {
        Console console = new Console();
        String gameName;
        console.write("Write name of the game that you want to play");
        gameName = console.read();

        try {
            juego = pickGame(gameName);
            console.write("The name was correct.");
            juego.createGame();

        } catch (GameNameException exp) {
            gameName = gameName.concat(" ;wrong game's name");
            console.write(gameName);
        }
    }

    public String respondTo(String message) {
        return juego.doAction(message);
    }

}
