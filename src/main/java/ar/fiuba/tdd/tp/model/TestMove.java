package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.icommand.*;
import ar.fiuba.tdd.tp.interpreter.AndExpression;
import ar.fiuba.tdd.tp.interpreter.ContainsElements;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;

import java.util.ArrayList;

/**
 * Created by panchoubuntu on 12/05/16.
 */
public class TestMove implements GameBuilder {

    public Game build() {
        Game game = new Game("probando move");

//        Element chest = new Element("chest");
//        Element room = new Element("room");
//        Element key = new Element("key");
//        Element player = new Element("player");
////        Element armario = new Element("armario");
//
//        key.setState(true);
//        chest.setState(true);
////        armario.setState(true);
//
//
//        room.addElement(key);
//
//        game.setPlayer(player);
//
//        ICommand lookAround = new LookAround("look around", game);
//        room.addCommand(lookAround);
//
//        ICommand move = new Move(game, "move");
//        key.addCommand(move);
//
//        ICommand pick = new MoveToPlayer("pick", game);
//        ICommand question = new Question("what can i do");
//        key.addCommand(pick);
//        key.addCommand(question);
//
//        room.addElement(chest);
//        room.addElement(key);
////        room.addElement(armario);
//
//        ArrayList<String> winArrayRoom = new ArrayList<String>();
//        winArrayRoom.add("chest");
//
//
//        ArrayList<String> winArrayChest = new ArrayList<String>();
//        winArrayChest.add("key");
//
//        IInterpreter winInterpreterRoom = new ContainsElements(room,winArrayRoom);
//        IInterpreter winInterpreterChest = new ContainsElements(chest,winArrayChest);
//
//        IInterpreter winInterpreter = new AndExpression(winInterpreterRoom, winInterpreterChest);
//
//        game.setPlayerPosition(room);
//        game.setWinInterpreter(winInterpreter);


        return game;
    }
}
