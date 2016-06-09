package ar.fiuba.tdd.tp.server.queue;

import ar.fiuba.tdd.tp.console.Console;
import ar.fiuba.tdd.tp.console.Writer;
import ar.fiuba.tdd.tp.driver.GameDriver;
import ar.fiuba.tdd.tp.driver.UnknownPlayerException;
import ar.fiuba.tdd.tp.exceptions.WritingException;
import ar.fiuba.tdd.tp.server.Interactor;
import ar.fiuba.tdd.tp.server.InteractorStatus;

import java.util.List;

import static ar.fiuba.tdd.tp.Constants.*;

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
        this.gameWonBy = NONE;
    }

    public void run() {
        while (! gameWon && ! terminate) {
            if (gameWonBy != NONE) {
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
            synchronized (interactors) {
                for (InteractorStatus interactor : interactors) {
                    Interactor actualInteractor = interactor.getInteractor();
                    if (actualInteractor.isAlive()) {
                        sendCommandToAliveInteractor(command, interactor, actualInteractor);
                    }
                }
            }
        } catch (WritingException e) {
            writer.writeError(e.getMsg());
        }
        pushWinCommand();
    }

    private void pushWinCommand() {
        if (gameWonBy != NONE) {
            CommandPlayer winCommand = new CommandPlayer(gameWonBy, SOMEONE_WON);
            queue.push(winCommand);
        }
    }

    private void sendCommandToAliveInteractor(CommandPlayer command, InteractorStatus interactor,
                                              Interactor actualInteractor) throws WritingException {
        try {
            Thread.sleep(300); //Para que no se pisen 2 mensajes a enviar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (gameWon) {
            sendGameWon(command, actualInteractor);
        } else {
            sendNoWonMessage(command, interactor, actualInteractor);
        }
    }

    private void sendNoWonMessage(CommandPlayer command, InteractorStatus interactor, Interactor actualInteractor) throws WritingException {
        if (actualInteractor.getPlayerNumber() == command.getPlayer()) {
            try {
                if (! command.isNewPlayer()) {
                    String returnCode = driver.sendCommand(command.getCommmand(), command.getPlayer());
                    actualInteractor.write(returnCode);
                    checkStatus(interactor, returnCode);
                }
            } catch (UnknownPlayerException e) {
                writer.writeError(e.getMsg());
            }
        } else {
            if (command.isNewPlayer()) {
                actualInteractor.write("The player " + command.getPlayer() + " has entered the game!");
            } else if (command.isBroadcast()) { //Como los mensajes de broadcast entran con player = -1, se enviaran a todos
                actualInteractor.write(command.getCommmand());
                writer.write("Enviando mensaje broadcast: " + command.getCommmand()); //TODO: Borrar!
            } else {
                actualInteractor.write("Player " + command.getPlayer() + " execute: " + command.getCommmand());
            }
        }
    }

    private void sendGameWon(CommandPlayer command, Interactor actualInteractor) throws WritingException {
        if (actualInteractor.getPlayerNumber() != command.getPlayer() && command.getPlayer() != BROADCAST) {
            try {
                Thread.sleep(300); //Para que pueda enviar la devolucion del exit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actualInteractor.write("Player " + command.getPlayer() + command.getCommmand());
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
