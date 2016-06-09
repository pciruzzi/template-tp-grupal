package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;

public class ITimeCommand {

    String name;
    String correctMovementMessage;
    String incorrectMovementMessage;
    String auxiliarMessage;

    public String doTimeAction(Element element) {
        return "Incorrect action de element en ITimeCommand";
    }

    public String doTimeAction(Player player) {
        return "Incorrect action de Player en ITimeCommand";
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
