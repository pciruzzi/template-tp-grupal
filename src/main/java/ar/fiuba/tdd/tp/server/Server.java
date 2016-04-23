package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.ConsoleReader;
import ar.fiuba.tdd.tp.Reader;

public class Server {

    private int port;
    private int portOffset;
    private Reader reader;
    //TODO: map<String, Game>

    public Server(int port) {
        this.port = port;
        this.portOffset = 0;
        this.reader = new ConsoleReader();
    }

    public String setUp() {
        boolean loadOk = false;
        String game = "";
        while (! loadOk) {
            String command = reader.read();
            String[] commandSplitted = command.split(" ");
            if (commandSplitted[0].equals("load")) { //TODO: Condición más compleja...
                //Buscar si el resto es un juego válido (Está en el map)
                loadOk = true;
//                if (commandSplitted.length > 1) {
//                    game = commandSplitted[1];
//                }
            } else {
                System.out.println("Command unknown... Try again!");
            }
        }
        portOffset++;
        return game;
    }

    public void initializeGame(String game) {
        Runnable runnable = new GameSocket(port + portOffset, game);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
