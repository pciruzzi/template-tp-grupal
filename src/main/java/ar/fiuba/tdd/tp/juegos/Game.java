package ar.fiuba.tdd.tp.juegos;

import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.State;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

    protected State actualState;

    public abstract String doAction(String action);

    public abstract void createGame();

    public String showItems() {
        return "There's a " + actualState.showStateItems() + " in the room";
    }


//    private String playerLocation;
//    private String[] inventory;
//    private String[] playerState;
//    private ArrayList<String> actions;
//    private String actualState;
//    private String finalState;
//
//
//    public Game(String playerLocation, String[] inventory, String[] playerState) {
//        this.playerLocation = playerLocation;
//        this.inventory = inventory;
//        this.playerState = playerState;
//        actions = new ArrayList<>();
//    }
//
//
//
//    public String doAction(String actionToDo) {
//        boolean actionFound = false;
//        String action = "";
//        for ( int i = 0 ; i < actions.size() && !actionFound ; i++) {
//            action = actions.get(i);
//
////            if (actionToDo.equals(action)) {
////                if (checkState(i)) {
////                    actionFound = true;
////                }
////
////            }
//
//        }
//        if (!actionFound) {
//            return "You can't do that";
//        }
//
//
//        return update();
//    }
//
//
//
//    public String update() {
//
//        if (actualState.equals(finalState)) {
//            return "You won";
//        } else {
//            return "Actual state: " + actualState;
//        }
//    }
}
