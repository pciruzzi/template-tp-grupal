package ar.fiuba.tdd.tp.engine;


import ar.fiuba.tdd.tp.Console;
import ar.fiuba.tdd.tp.Writer;
import ar.fiuba.tdd.tp.exceptions.GameNameException;
import ar.fiuba.tdd.tp.games.*;

import java.util.HashMap;
import java.util.Map;


public class Engine {

    private Map<String, Game> gameMap;
    private String gameName;
    private Game selectedGame;
    private Writer writer;

    public static boolean canCreate(String gameName) {
        Engine engine = new Engine(gameName);
        return engine.existsGame(gameName);
    }


    private void loadGame(Game game) {
        gameMap.put(game.getGameName(), game);
    }

    private void loadGames() {
//        loadGame(new EvilThing());
//        loadGame(new OpenDoor2());
//        loadGame(new TreasureQuest());
        loadGame(new FetchQuest());
        loadGame(new HanoiTowers());
        loadGame(new OpenDoor());
        loadGame(new WolfSheepAndCabbage());
    }

    public Engine(String gameName) {
        gameMap = new HashMap<String, Game>();
        writer = new Console();
        selectedGame = null;
        this.gameName = gameName;
        this.loadGames();
    }

    private boolean existsGame(String gameName) {
        return gameMap.containsKey(gameName);
    }

    private void pickGame(String gameName) throws GameNameException {
        if (existsGame(gameName)) {
            selectedGame = gameMap.get(gameName).copy();
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

    public String helpCommand(String message) {
        String gameName = message.replaceAll("^help ", "");
        if (existsGame(gameName)) {
            return gameMap.get(gameName).getDescription();
        }
        return "It doesn't exist a game with that name. Sorry I can't help you! :(";
    }

    public String respondTo(String message) {
        if (message.matches("^help .*")) {
            return helpCommand(message);
        }
        return selectedGame.doAction(message);
    }

    public boolean getGameWon() {
        return selectedGame.getGameWon();
    }
}
