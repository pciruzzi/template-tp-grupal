package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.time.TimeCommand;

public class MockedTimedAction extends TimeCommand {

    private BroadcastQueue queue;

    public MockedTimedAction(int timeOfAction, String comandito, BroadcastQueue queue) {
        super(timeOfAction, comandito);
        this.queue = queue;
    }

    @Override
    public void startTimeAction() {
    }

    public String startMockedAction() {
        String returnMessagge = engine.doTimeCommand(command);
        queue.pushBroadcast(returnMessagge);
        return returnMessagge;
    }

}
