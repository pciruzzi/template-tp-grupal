package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.juegos.FetchQuest;
import ar.fiuba.tdd.tp.juegos.Game;
import ar.fiuba.tdd.tp.juegos.HanoiTowers;
import ar.fiuba.tdd.tp.juegos.OpenDoor;

import java.util.Scanner;


public class Engine {

    public void generarJuego() {
//        Game juego = new FetchQuest();
//        Game juego = new OpenDoor();
        Game juego = new HanoiTowers();
        juego.createGame();

        String intro = "";

        Scanner scanner = new Scanner(System.in, "US-ASCII");

        while ( ! intro.equals( "fin") ) {
            intro = scanner.nextLine();
            System.out.println(juego.doAction(intro));
        }
    }

}
