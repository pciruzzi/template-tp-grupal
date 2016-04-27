package ar.fiuba.tdd.tp.engine;


import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.Writer;
import ar.fiuba.tdd.tp.exceptions.GameNameException;
import ar.fiuba.tdd.tp.games.*;

import java.util.ArrayList;
import java.util.List;


public class Engine {

    private List<Game> gameList;
    private String gameName;
    private Game selectedGame;
    private Writer writer;

    public static boolean canCreate(String gameName) {
        Engine engine = new Engine(gameName);

        return engine.canBeCreated(gameName);
    }

    public Engine(String gameName) {
        gameList = new ArrayList<Game>();
//        gameList.add(new EvilThing());
//        gameList.add(new OpenDoor2());
//        gameList.add(new TreasureQuest());
        gameList.add(new FetchQuest());
        gameList.add(new HanoiTowers());
        gameList.add(new OpenDoor());
        gameList.add(new WolfSheepAndCabbage());

        writer = new Console();
        selectedGame = null;
        this.gameName = gameName;
    }

    private boolean canBeCreated(String gameName) {
        for (Game game : gameList) {
            if (game.checkGameName(gameName)) {
                return true;
            }
        }
        return false;
    }

    private void pickGame(String gameName) throws GameNameException {
        for (Game game : gameList) {
            if (game.checkGameName(gameName)) {
                selectedGame = game.copy();
            }
        }
        if (selectedGame == null) {
            throw new GameNameException("Juego invalido");
        }
    }

    public void generarJuego() {
        try {
            pickGame(gameName);
            selectedGame.createGame();

//            String intro = "";
//
//            Scanner scanner = new Scanner(System.in, ENCODING);

//            while ( ! intro.equals("fin") ) {
//                intro = scanner.nextLine();
//                System.out.println(selectedGame.doAction(intro));
//            }
        } catch (GameNameException exp) {
            gameName = gameName.concat(" ;wrong game's name");
            writer.writeError(gameName);
        }
    }

    public String respondTo(String message) {
        return selectedGame.doAction(message);
    }

    public boolean getGameWon() {
        return selectedGame.getGameWon();
    }

}
