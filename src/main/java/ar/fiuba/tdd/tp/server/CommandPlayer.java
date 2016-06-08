package ar.fiuba.tdd.tp.server;

public class CommandPlayer {

    private String command;
    private int player;
    private boolean newPlayer;
    private boolean broadcast;

    public CommandPlayer(int player, String command) {
        this.player = player;
        this.command = command;
        this.newPlayer = false;
        this.broadcast = false;
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

    public void setBroadcast() {
        broadcast = true;
    }

    public boolean isBroadcast() {
        return broadcast;
    }
}
