package ar.fiuba.tdd.tp.time;


import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;

import java.util.Timer;
import java.util.TimerTask;

public abstract class TimeCommand {

    private Engine engine;
    protected Timer timer;
    private int timeOfAction;
    private String command;
    private BroadcastQueue queue;

    public TimeCommand(int timeOfAction, String command) {
        this.timeOfAction = timeOfAction;
        this.command = command;
    }

    public TimerTask start() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                queue.pushBroadcast(engine.doCommand(-1, command));
//                System.out.println(engine.doCommand(0, command));
            }
        };
        return timerTask;
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

    public abstract void startTimeAction();
}
