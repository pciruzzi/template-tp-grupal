package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.Time;
import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.icommand.ICommand;
import ar.fiuba.tdd.tp.model.FetchQuestConfiguration;
import ar.fiuba.tdd.tp.model.Game;

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
                System.out.println(engine.doCommand(comandito));
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
