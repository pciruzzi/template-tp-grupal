package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.games.FetchQuest;
import ar.fiuba.tdd.tp.games.Game;

import java.util.Scanner;


public class Engine {

    public void generarJuego() {
        Game juego = new FetchQuest();
//        Game juego = new OpenDoor();
        juego.createGame();

        String intro = "";

        Scanner scanner = new Scanner(System.in, "US-ASCII");

        while ( ! intro.equals( "fin") ) {
            intro = scanner.nextLine();
            System.out.println(juego.doAction(intro));
        }
    }

}
