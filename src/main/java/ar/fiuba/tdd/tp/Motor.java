package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.Juegos.Juego;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by panchoubuntu on 21/04/16.
 */
public class Motor {

    Triplet<String,String,String> triplet1 = new Triplet("estado1", "regla1", "estado2");
    Triplet<String,String,String> triplet2 = new Triplet("estado2", "regla2", "estado3");
    Triplet<String,String,String> triplet3 = new Triplet("estado3", "regla3", "estado4");
    Triplet<String,String,String> triplet4 = new Triplet("estado4", "regla4", "estado5");

    ArrayList<Triplet<String,String,String >> listaTriplas = new ArrayList<>();

    public void generarJuego(){
        listaTriplas.add(triplet1);
        listaTriplas.add(triplet2);
        listaTriplas.add(triplet3);
        listaTriplas.add(triplet4);

        Juego juego = new Juego(null, null, null);

        juego.loadRules(listaTriplas);

        String intro = "";

        Scanner scanner = new Scanner(System.in);

        while ( ! intro.equals( "fin") ){
            intro = scanner.nextLine();
            System.out.println(juego.doAction(intro));
        }
    }

}
