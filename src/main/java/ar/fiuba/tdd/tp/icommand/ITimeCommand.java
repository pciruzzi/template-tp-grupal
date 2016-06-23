package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

public class ITimeCommand {

    protected String name;
    protected String correctMovementMessage;
    protected String incorrectMovementMessage;
    protected String auxiliarMessage;

    public String doTimeAction(Element element) {
        return "Incorrect action de element en ITimeCommand: " + name;
    }

    public String doTimeAction(Player player) {
        return "Incorrect action de Player en ITimeCommand: " + name;
    }

    public void correctMovementMessage(String message) {
        this.correctMovementMessage = message;
    }

    public void auxiliarMessage(String message) {
        this.auxiliarMessage = message;
    }

    public void incorrectMovementMessage(String message) {
        this.incorrectMovementMessage = message;
    }

    public String getName() {
        return name;
    }
}
