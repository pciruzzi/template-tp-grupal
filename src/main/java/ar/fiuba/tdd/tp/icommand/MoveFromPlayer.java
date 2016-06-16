package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;
import ar.fiuba.tdd.tp.interpreter.IInterpreter;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.time.TimeCommand;

import java.util.ArrayList;
import java.util.List;

public class MoveFromPlayer extends ICommand {
    private Game game;
    private String element;
    private IInterpreter interpreter;
    private List<TimeCommand> timeCommandList;

    public MoveFromPlayer(String name, Game game, String element) {
        this.game = game;
        this.name = name;
        this.element = element;
        this.correctMovementMessage = " has just stolen your ";
        this.incorrectMovementMessage = "Hi!";
        this.auxiliarMessage = "Hi!\n" + "The ";
        this.interpreter = null;
        this.timeCommandList = new ArrayList<>();
    }

    public MoveFromPlayer(String name, Game game, String element, IInterpreter interpreter, List<TimeCommand> timeCommandList) {
        this(name, game, element);
        this.interpreter = interpreter;
        this.timeCommandList = timeCommandList;
    }

    public String doAction(Element element, int playerId) {
        Element player = game.getPlayer(playerId);
        Element actualElement = player.getElement(this.element);
        State stateToAffect = actualElement.getStateToAffect();
        //Si el jugador tiene el elemento en el inventario.
        if (player.getElementMap().containsKey(this.element)) {
            player.removeElement(actualElement);
            actualElement.changeState("visible",false);
            element.addElement(actualElement);

            if (stateToAffect != null && element.hasState(stateToAffect.getName())) {
                element.changeState(stateToAffect.getName(), stateToAffect.isActive());
            }
            checkStartTimer(element);

            try {
                if ( stateToAffect.getEffectMessage() != null ) {
                    return stateToAffect.getEffectMessage();
//                            + auxiliarMessage + element.getName() + correctMovementMessage + this.element;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            return auxiliarMessage + element.getName() + correctMovementMessage + this.element;
        } else {
            //Si el jugador no tiene el elemento.
            return incorrectMovementMessage;
        }
    }

    private void checkStartTimer(Element element) {
        if ( ( interpreter != null ) && (interpreter.interpret()) ) {
            for ( TimeCommand command : timeCommandList ) {
                command.startTimeAction();
            }
        }
    }
}
