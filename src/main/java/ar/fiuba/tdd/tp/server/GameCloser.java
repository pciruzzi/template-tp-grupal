package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.server.queue.Dequeuer;

public class GameCloser implements Runnable {

    private GameSocket socket;
    private Dequeuer dequeuer;

    public GameCloser(GameSocket socket, Dequeuer dequeuer) {
        this.socket = socket;
        this.dequeuer = dequeuer;
    }

    public void run() {
        while (! dequeuer.getGameWon()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socket.terminate();
    }
}
