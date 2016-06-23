package ar.fiuba.tdd.tp.mocks;

import ar.fiuba.tdd.tp.server.queue.BroadcastQueue;
import ar.fiuba.tdd.tp.time.TimeCommand;

public class MockedTimedCommand extends TimeCommand {

    private BroadcastQueue queue;

    public MockedTimedCommand(String comandito, BroadcastQueue queue) {
        super(1, comandito);
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
