package ar.fiuba.tdd.tp.time;

public class SingleTimedAction extends TimeCommand{

    public SingleTimedAction(int timeOfAction, String comandito) {
        super(timeOfAction, comandito);
    }

    public void startTimeAction() {
        timer.schedule(this.start(),this.getTimeOfAction());
    }
}
