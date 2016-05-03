package ar.fiuba.tdd.tp;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        GameBuilder builder = BuilderLoader.load(file, Thread.currentThread().getContextClassLoader());
        builder.build();
    }
}
