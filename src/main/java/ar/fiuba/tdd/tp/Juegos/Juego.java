package ar.fiuba.tdd.tp.Juegos;

import ar.fiuba.tdd.tp.Triplet;

import java.util.ArrayList;

/**
 * Created by panchoubuntu on 21/04/16.
 */
public class Juego {

    private String playerLocation;
    private String[] inventory;
    private String[] playerState;
    private ArrayList<Triplet<String,String,String>> triplet;
    private ArrayList<String> actions;
    private String actualState;
    private String finalState;


    public Juego(String playerLocation, String[] inventory, String[] playerState){
        this.playerLocation = playerLocation;
        this.inventory = inventory;
        this.playerState = playerState;
        actions = new ArrayList<>();
    }

    public void loadRules(ArrayList<Triplet<String,String,String>> triplet){
        this.triplet = triplet;
        int lenght = triplet.size();
        for ( int i = 0 ; i < lenght ; i++ ) {
            actions.add(triplet.get(i).getB());
        }
        actualState = triplet.get(0).getA();
        finalState  = triplet.get(triplet.size() - 1).getC();
    }

    public String doAction(String actionToDo){
        boolean actionFound = false;
        String action = "";
        for( int i = 0 ; i < actions.size() && !actionFound ; i++){
            action = actions.get(i);

            if(actionToDo.equals(action)){
                if(checkState(i)){
                    actionFound = true;
                }

            }

        }
        if(!actionFound) return "You can't do that";


        return update();
    }

    public boolean checkState(int actionIndex){
        String state = triplet.get(actionIndex).getA();
        if(state.equals(actualState)){
            String nextState = triplet.get(actionIndex).getC();
            actualState = nextState;

            return true;
        }
        return false;
    }

    public String update(){

        if(actualState.equals(finalState)){
            return "You won";
        } else {
            return "Actual state: " + actualState;
        }
    }
}
