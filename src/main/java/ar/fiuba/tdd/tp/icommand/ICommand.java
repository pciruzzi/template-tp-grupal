package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;
import ar.fiuba.tdd.tp.engine.State;

public abstract class ICommand {

    String name;
    String correctMovementMessage;
    String incorrectMovementMessage;
    String auxiliarMessage;

    public abstract String doAction(Element element);

    public String doAction(Element originElement, Element element, Element destinationElement) {
        return "Incorrect doAction";
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

    protected String affectPlayer(Element element, Element player) {

        String returnMessage = "";
        State stateToAffect = element.getStateToAffect();
        if (stateToAffect == null) {
            return "";
        }

        if (player.hasState(stateToAffect.getName()) && player.getValueOfState(stateToAffect.getName()) != stateToAffect.isActive()) {
            player.changeState(stateToAffect.getName(), stateToAffect.isActive());
            returnMessage += "\n" + stateToAffect.getEffectMessage();

            if (stateToAffect.isWillDestroyTheItem()) {
                player.removeElement(element);
            }

            returnMessage = checkAntiState(player, returnMessage, stateToAffect);

        }
        return returnMessage;
    }

    private String checkAntiState(Element player, String returnMessage, State stateToAffect) {
        if (player.hasElement(stateToAffect.getAntiState())) {
            player.changeState(stateToAffect.getName(), !stateToAffect.isActive());
            returnMessage += "\n" + stateToAffect.getAntiEffectMessage();
            if (stateToAffect.isWillDestroyTheItem()) {
                Element elementToRemoveOfAntiEffect = player.getElement(stateToAffect.getAntiState());
                player.removeElement(elementToRemoveOfAntiEffect);
            }

        }
        return returnMessage;
    }
}
