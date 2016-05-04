package ar.fiuba.tdd.tp;

public class Main {
    public static void main(String[] args) throws Exception {
        GameBuilder builder = BuilderLoader.load(args[0]);
        builder.build();
    }
}
