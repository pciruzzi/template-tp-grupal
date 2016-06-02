package ar.fiuba.tdd.tp.server;

public class CommandPlayer {

    private String command;
    private int player;

    public CommandPlayer(int player, String command) {
        this.player = player;
        this.command = command;
    }

    public int getPlayer() {
        return player;
    }

    public String getCommmand() {
        return command;
    }
}
