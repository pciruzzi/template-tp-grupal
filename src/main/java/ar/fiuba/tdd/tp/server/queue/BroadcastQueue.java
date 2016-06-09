package ar.fiuba.tdd.tp.server.queue;

public interface BroadcastQueue {
    void pushBroadcast(String broadcast);

    void pushLostCommand(int playerNumber);
}
