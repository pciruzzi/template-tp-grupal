package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.FalseExpression;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("CPD-START")

public class FetchQuestConfiguration implements GameBuilder {

    private Game game;
    private List<Player> players;
    private int maxPlayers;

    public Game build() {
        game = new Game("Fetch Quest");
        game.setDescription("You are in a room, look around to see if there is something useful.");
        setPlayers();

        Element room = new Element("room");
        room.addCommand(new Help("help", game));
        room.addCommand(new Exit(game));

        Element stick = new Element("stick");
        stick.changeState("visible", true);

        ICommand lookAround = new LookAround("look around", game);
        room.addCommand(lookAround);

        ICommand pick = new MoveToPlayer("pick", game);
        ICommand question = new Question("ask");
        stick.addCommand(pick);
        stick.addCommand(question);

        room.addElement(stick);
        game.setInitialPosition(room);

        setWinAndLoseInterpreter();

        return game;
    }

    private void setPlayers() {
        maxPlayers = 2;
        game.setMaxPlayers(maxPlayers);
        players = new ArrayList<>();
        for (int i = 0; i < maxPlayers; i++) {
            Player newPlayer = new Player(i);
            players.add(newPlayer);
        }
        game.setPlayers(players);
    }

    @SuppressWarnings("CPD-END")

    private void setWinAndLoseInterpreter() {
        Optional<Element> playerGenerico = Optional.empty();
        for (Player player : players) {
            ArrayList<String> winArray = new ArrayList<>();
            winArray.add("stick");
            IInterpreter winInterpreter = new ContainsElements(playerGenerico,winArray);
            player.setWinInterpreter(winInterpreter);

            IInterpreter loseInterpreter = new FalseExpression();
            player.setLosingInterpreter(loseInterpreter);
        }
    }
}