package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.Player;
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
    private String stateToChangeName;
    private boolean stateToChange;

    public MoveFromPlayer(String name, Game game, String element) {
        this.game = game;
        this.name = name;
        this.element = element;
        this.correctMovementMessage = " has just stolen your ";
        this.incorrectMovementMessage = "Hi!";
        this.auxiliarMessage = "Hi!\n" + "The ";
        this.interpreter = null;
        this.timeCommandList = new ArrayList<>();
        this.stateToChangeName = null;
        this.stateToChange = false;
    }

    public MoveFromPlayer(String name, Game game, String element, IInterpreter interpreter, List<TimeCommand> timeCommandList) {
        this(name, game, element);
        this.interpreter = interpreter;
        this.timeCommandList = timeCommandList;
    }

    public String doAction(Element element, int playerId) {
        Player player = game.getPlayer(playerId);

        //Si el jugador tiene el elemento en el inventario.
        if (player.getElementMap().containsKey(this.element)) {
            Element actualElement = player.getElement(this.element);
            player.removeElement(actualElement);
            actualElement.changeState("visible",false);
            element.addElement(actualElement);

            State stateToAffect = actualElement.getStateToAffect();
            if (stateToAffect != null && element.hasState(stateToAffect.getName())) {
                element.changeState(stateToAffect.getName(), stateToAffect.isActive());
            }
            checkStartTimer(element, player);
            checkStateChange(player);
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

    private void checkStartTimer(Element element, Player player) {
        if ( ( interpreter != null ) && (interpreter.interpret(player)) ) {
            for ( TimeCommand command : timeCommandList ) {
                command.startTimeAction();
            }
        }
    }

    public void changePlayerState(String stateName, boolean state) {
        this.stateToChangeName = stateName;
        this.stateToChange = state;
    }

    private void checkStateChange(Element player) {
        if (stateToChangeName != null) {
            player.changeState(stateToChangeName, stateToChange);
        }
    }
}
