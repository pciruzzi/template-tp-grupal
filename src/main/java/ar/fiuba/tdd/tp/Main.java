package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.Engine;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Console();
        Writer writer = new Console();

        writer.write("Welcome to hell");
        writer.write("Type the game you'd like to play:");
        String gameName = reader.read();

        if (Engine.canCreate(gameName)) {
            writer.write("You can start playing now...");
            Engine engine = new Engine(gameName);
            engine.generateGame();
            String input = "";
            while (! input.equals("exit") && ! engine.getGameWon()) {
                input = reader.read();
                String returnCode = engine.respondTo(input);
                writer.write(returnCode);
            }

        }
    }
}
