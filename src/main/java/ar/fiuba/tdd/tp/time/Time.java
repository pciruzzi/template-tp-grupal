package ar.fiuba.tdd.tp.time;


import ar.fiuba.tdd.tp.engine.Engine;

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

    public void start() {
        for (TimeCommand command: timeCommands) {
            command.setEngine(this.engine);
            command.setTimer(timer);
            command.startTimeAction();
        }
    }

    public void addTimeCommand(TimeCommand timeCommand) {
        timeCommands.add(timeCommand);
    }

    public void setTimeTasks(ArrayList<TimeCommand> timeTasks) {
        this.timeCommands = timeTasks;
    }
}
