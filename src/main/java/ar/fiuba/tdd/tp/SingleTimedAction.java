package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.model.Game;

public class SingleTimedAction extends TimeCommand{

    public SingleTimedAction(int timeOfAction, String comandito) {
        super(timeOfAction, comandito);
    }

    public void startTimeAction() {
        time.schedule(this.start(),this.getTimeOfAction());
    }
}
