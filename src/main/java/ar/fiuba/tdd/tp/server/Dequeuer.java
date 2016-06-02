package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.exceptions.WritingException;

import java.util.List;

public class Dequeuer implements Runnable {

    private List<Interactor> interactors;
    private EventQueue queue;
    private GameDriver driver;
    private Writer writer;

    public Dequeuer(List<Interactor> interactors, EventQueue queue, GameDriver driver) {
        this.interactors = interactors;
        this.queue = queue;
        this.driver = driver;
        this.writer = new Console();
    }

    public void run() {
        while (true) {
            if (! queue.isEmpty()) {
                CommandPlayer command = queue.pop();
                for (Interactor interactor : interactors) {
                    try {
                        if (interactor.getPlayerNumber() == command.getPlayer()) {
                            interactor.write(driver.sendCommand(command.getCommmand()));
                        } else {
                            interactor.write("Player " + command.getPlayer() + " execute: " + command.getCommmand());
                        }
                    } catch (WritingException e) {
                        writer.writeError(e.getMsg());
                    }
                }
            }
        }
    }
}
