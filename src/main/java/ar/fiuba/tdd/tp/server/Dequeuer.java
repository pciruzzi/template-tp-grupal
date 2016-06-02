package ar.fiuba.tdd.tp.server;

import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.exceptions.WritingException;

import java.util.List;

import static ar.fiuba.tdd.tp.Constants.GAME_LOST;
import static ar.fiuba.tdd.tp.Constants.GAME_WON;
import static ar.fiuba.tdd.tp.Constants.SOMEONE_WON;

public class Dequeuer implements Runnable {

    volatile boolean terminate = false;
    private List<InteractorStatus> interactors;
    private boolean gameWon;
    private int gameWonBy;
    private EventQueue queue;
    private GameDriver driver;
    private Writer writer;

    public Dequeuer(List<InteractorStatus> interactors, EventQueue queue, GameDriver driver) {
        this.interactors = interactors;
        this.queue = queue;
        this.driver = driver;
        this.writer = new Console();
        this.gameWon = false;
        this.gameWonBy = -1;
    }

    public void run() {
        while (! gameWon && ! terminate) {
            if (gameWonBy != -1) {
                gameWon = true;
            }
            if (! queue.isEmpty()) {
                CommandPlayer command = queue.pop();
                sendCommandToInteractors(command);
            }
        }
    }

    private void sendCommandToInteractors(CommandPlayer command) {
        try {
            for (InteractorStatus interactor : interactors) {
                Interactor actualInteractor = interactor.getInteractor();
                if (actualInteractor.isAlive()) {
                    sendCommandToAliveInteractor(command, interactor, actualInteractor);
                }
            }
        } catch (WritingException e) {
            writer.writeError(e.getMsg());
        }
        pushWinCommand();
    }

    private void pushWinCommand() {
        if (gameWonBy != -1) {
            CommandPlayer winCommand = new CommandPlayer(gameWonBy, SOMEONE_WON);
            queue.push(winCommand);
        }
    }

    private void sendCommandToAliveInteractor(CommandPlayer command, InteractorStatus interactor,
                                              Interactor actualInteractor) throws WritingException {
        if (gameWon) {
            if (actualInteractor.getPlayerNumber() != command.getPlayer()) {
                try {
                    Thread.sleep(300); //Para que pueda enviar la devolucion del exit
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                actualInteractor.write("Player " + command.getPlayer() + command.getCommmand());
            }
        } else {
            if (actualInteractor.getPlayerNumber() == command.getPlayer()) {
                String returnCode = driver.sendCommand(command.getCommmand());
                actualInteractor.write(returnCode);
                checkStatus(interactor, returnCode);
            } else {
                actualInteractor.write("Player " + command.getPlayer() + " execute: " + command.getCommmand());
            }
        }
    }

    private void checkStatus(InteractorStatus interactor, String returnCode) {
        if (returnCode.equals(GAME_WON)) {
            interactor.won();
            this.gameWonBy = interactor.getInteractor().getPlayerNumber();
        }
        if (returnCode.equals(GAME_LOST)) {
            interactor.lost();
        }
    }

    public void terminate() {
        this.terminate = true;
    }

    public boolean getGameWon() {
        return this.gameWon;
    }
}
