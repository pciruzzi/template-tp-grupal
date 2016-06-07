package ar.fiuba.tdd.tp.engine;


import java.util.Timer;
import java.util.TimerTask;

public abstract class TimeCommand {

    private Engine engine;
    protected Timer time;
    private int timeOfAction;

    private String comandito;

    public TimeCommand(int timeOfAction, String comandito) {
        this.timeOfAction = timeOfAction;
        this.comandito = comandito;
    }

    public TimerTask start() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                System.out.println(engine.doCommand(0, comandito));
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
        this.time = time;
    }

    public abstract void startTimeAction();
}
