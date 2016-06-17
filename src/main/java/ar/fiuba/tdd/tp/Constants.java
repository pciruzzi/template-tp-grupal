package ar.fiuba.tdd.tp;

public final class Constants {
    public static final char EO_MSG = (char) 13;
    public static final String ENCODING = "US-ASCII";
    public static final int PORT = 8080;

    public static final String GAME_WON = "You won!!!";
    public static final String GAME_LOST = "You lost!!!";
    public static final String REJECTED = "Sorry, but the game has reached the maximum players connected. Try again later.";
    public static final String SOMEONE_WON = " has win! The game is over.";
    public static final String EXIT = "exit";

    public static final int BROADCAST = -1;
    public static final int NONE = -1;
    public static final int NON_PLAYER = -1;

    // Para The Escape 2
    public static final int TIME_AWAKE = 30000; //en milisegundos
    public static final int TIME_MOVE = 60000; //en milisegundos
}
