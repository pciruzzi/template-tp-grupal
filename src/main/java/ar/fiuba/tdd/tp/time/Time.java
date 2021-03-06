package ar.fiuba.tdd.tp.time;

import ar.fiuba.tdd.tp.engine.Engine;
import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;

import java.util.ArrayList;
import java.util.Timer;

public class Time {

    private Timer timer;
    private ArrayList<TimeCommand> timeCommands;
    private Engine engine;

    public Time(Engine engine) {
        timer = new Timer();
        timeCommands = new ArrayList<>();
        this.engine = engine;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void start() {
        for (TimeCommand command: timeCommands) {
            command.setEngine(this.engine);
            command.setTimer(timer);
            if ( command.getStart() ) {
                command.startTimeAction();
            }
        }
    }

    public void setTimeTasks(ArrayList<TimeCommand> timeTasks) {
        this.timeCommands = timeTasks;
    }
}
