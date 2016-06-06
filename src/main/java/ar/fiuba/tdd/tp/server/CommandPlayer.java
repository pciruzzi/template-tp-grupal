package ar.fiuba.tdd.tp.server;

public class CommandPlayer {

    private String command;
    private int player;
    private boolean newPlayer;

    public CommandPlayer(int player, String command) {
        this.player = player;
        this.command = command;
        this.newPlayer = false;
    }

    public int getPlayer() {
        return player;
    }

    public String getCommmand() {
        return command;
    }

    public void setNewPlayer() {
        newPlayer = true;
    }

    public boolean isNewPlayer() {
        return newPlayer;
    }
}
