package ar.fiuba.tdd.tp.engine;


import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.games.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ar.fiuba.tdd.tp.exceptions.GameNameException;

import static ar.fiuba.tdd.tp.Constants.*;


public class Engine {

    private List<Game> gameList;

    public Engine() {

        gameList = new ArrayList<Game>();

        gameList.add(new EvilThing());
        gameList.add(new FetchQuest());
        gameList.add(new HanoiTowers());
        gameList.add(new OpenDoor());
        gameList.add(new OpenDoor2());
        gameList.add(new TreasureQuest());
        gameList.add(new WolfSheepAndCabbage());
    }

    private Game pickGame(String gameName) throws GameNameException{

        for(Game game:gameList){
            if(game.checkGameName(gameName)){
                return game.clone();
            }
        }
        throw new GameNameException("Juego invalido");
    }


    public void generarJuego() {
//        Game juego = new FetchQuest();
//        Game juego = new OpenDoor();
        Console console = new Console();
        String gameName;
        console.write("Write name of the game that you want to play");
        gameName = console.read();

        try{
            Game juego = pickGame(gameName);
            console.write("The name was correct.");
            juego.createGame();

            String intro = "";

            Scanner scanner = new Scanner(System.in, ENCODING);

            while ( ! intro.equals("fin") ) {
                intro = scanner.nextLine();
                System.out.println(juego.doAction(intro));
            }
        }catch (GameNameException exp){
            gameName = gameName.concat(" ;wrong game's name");
            console.write(gameName);
        }

//        Game juego = new HanoiTowers();
//        Game juego = new WolfSheepAndCabbage();
    }

}
