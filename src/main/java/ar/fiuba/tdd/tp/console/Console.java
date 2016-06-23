package ar.fiuba.tdd.tp.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ar.fiuba.tdd.tp.Constants.*;

public class Console implements Reader, Writer {

    public Console() {

    }

    public String read() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, ENCODING));
            return br.readLine();
        } catch (IOException e) {
            this.writeError("Couldn't read from console.");
            return "";
        }
    }

    public void write(String writable) {
        System.out.println(writable);
    }

    public void writeError(String writable) {
        System.err.println(writable);
    }
}
