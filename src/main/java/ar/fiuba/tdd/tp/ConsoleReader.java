package ar.fiuba.tdd.tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader implements Reader {

    public ConsoleReader() {

    }

    public String read() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "US-ASCII"));
            return br.readLine();
        } catch (IOException e) {
            System.err.println("Couldn't read from console.");
            return "";
        }
    }
}
