package ar.fiuba.tdd.tp.time;


import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;

import java.util.Timer;
import java.util.TimerTask;

public abstract class TimeCommand {

    protected Engine engine;
    protected Timer timer;
    private int timeOfAction;
    protected String command;
    protected BroadcastQueue queue;
    private boolean startAtBegining;

    public TimeCommand(int timeOfAction, String command) {
        this.timeOfAction = timeOfAction;
        this.command = command;
        startAtBegining = true;
    }

    public TimerTask start() {
        return new TimerTask() {
            public void run() {
                engine.doTimeCommand(command);
            }
        };
    }

    public int getTimeOfAction() {
        return timeOfAction;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setTimer(Timer time) {
        this.timer = time;
    }

    public void setBroadcastQueue(BroadcastQueue queue) {
        this.queue = queue;
    }

    public void setStart(boolean start) {
        startAtBegining = start;
    }

    public boolean getStart() {
        return startAtBegining;
    }

    public abstract void startTimeAction();
}
