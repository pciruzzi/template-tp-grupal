package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;

import java.util.Scanner;

import static ar.fiuba.tdd.tp.Constants.ENCODING;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to hell");

        Engine engine = new Engine("fetch quest");
        engine.generarJuego();

        String intro = "";

        Scanner scanner = new Scanner(System.in, ENCODING);

        while ( ! intro.equals("fin") ) {
            intro = scanner.nextLine();
            System.out.println(engine.respondTo(intro));
        }
    }
}
