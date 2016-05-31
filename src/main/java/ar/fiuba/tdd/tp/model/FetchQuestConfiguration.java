package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.SchedualedTimedAction;
import ar.fiuba.tdd.tp.SingleTimedAction;
import ar.fiuba.tdd.tp.Time;
import ar.fiuba.tdd.tp.TimeCommand;
import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.FalseExpression;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

@SuppressWarnings("CPD-START")

public class FetchQuestConfiguration implements GameBuilder {

    private Game game;

    public Game build() {
        game = new Game("Fetch Quest");
        game.setDescription("You are in a room, look around to see if there is something useful.");

        Element room = new Element("room");
        room.addCommand(new Help("help", game));
        room.addCommand(new Exit());

        Element stick = new Element("stick");
        stick.setState(true);

        Element player = new Element("player");
        game.setPlayer(player);

        ICommand lookAround = new LookAround("look around", game);
        room.addCommand(lookAround);

        ICommand pick = new MoveToPlayer("pick", game);
        ICommand question = new Question("ask");
        stick.addCommand(pick);
        stick.addCommand(question);

        room.addElement(stick);

        game.setPlayerPosition(room);

        setWinAndLoseInterpreter(player);


//        TimeCommand pickStick = new SingleTimedAction(game,10000,"pick stick");
//        TimeCommand dropStick = new SingleTimedAction(game,7000,"drop stick");
//        TimeCommand lookAround2 = new SchedualedTimedAction(game,3000,"look around");
//
//
//        game.addTimeCommand(pickStick);
//        game.addTimeCommand(dropStick);
//        game.addTimeCommand(lookAround2);

        return game;
    }

    @SuppressWarnings("CPD-END")

    private void setWinAndLoseInterpreter(Element player) {
        ArrayList<String> winArray = new ArrayList<String>();
        winArray.add("stick");

        IInterpreter winInterpreter = new ContainsElements(player,winArray);

        game.setWinInterpreter(winInterpreter);

        IInterpreter loseInterpreter = new FalseExpression();
        game.setLosingInterpreter(loseInterpreter);
    }
}