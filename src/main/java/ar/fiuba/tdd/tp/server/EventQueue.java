package ar.fiuba.tdd.tp.server;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EventQueue {

    Queue<CommandPlayer> queue;

    public EventQueue() {
        // Otra opcion: this.queue = Collections.synchronizedList(new LinkedList<>());
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void push(CommandPlayer command) {
        this.queue.add(command);
    }

    public CommandPlayer pop() {
        return this.queue.poll();
    }
}
