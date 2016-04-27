package ar.fiuba.tdd.tp.engine;


import ar.fiuba.tdd.tp.games.*;

import java.util.Scanner;

import static ar.fiuba.tdd.tp.Constants.*;


public class Engine {

    public void generarJuego() {
//        Game juego = new FetchQuest();
//        Game juego = new OpenDoor();
        Game juego = new HanoiTowers();
//        Game juego = new WolfSheepAndCabbage();
        juego.createGame();

        String intro = "";

        Scanner scanner = new Scanner(System.in, ENCODING);

        while ( ! intro.equals("fin") ) {
            intro = scanner.nextLine();
            System.out.println(juego.doAction(intro));
        }
    }

}
