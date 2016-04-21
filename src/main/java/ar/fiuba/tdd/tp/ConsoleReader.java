package ar.fiuba.tdd.tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ar.fiuba.tdd.tp.Constants.*;

public class ConsoleReader implements Reader {

    public ConsoleReader() {

    }

    public String read() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, ENCODING));
            return br.readLine();
        } catch (IOException e) {
            System.err.println("Couldn't read from console.");
            return "";
        }
    }
}
