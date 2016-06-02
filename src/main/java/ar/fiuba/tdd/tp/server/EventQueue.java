package ar.fiuba.tdd.tp.server;

import java.util.LinkedList;
import java.util.Queue;

public class EventQueue {

    Queue<CommandPlayer> queue;

    public EventQueue() {
        this.queue = new LinkedList<>();
    }

    public boolean isEmpty() {
        synchronized (queue) {
            return this.queue.isEmpty();
        }
    }

    public void push(CommandPlayer command) {
        synchronized (queue) {
            this.queue.add(command);
        }
    }

    public CommandPlayer pop() {
        synchronized (queue) {
            return this.queue.poll();
        }
    }
}
