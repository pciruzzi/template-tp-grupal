package ar.fiuba.tdd.tp.engine;


public class State {

    private String name;
    private boolean active;

    public boolean isWillDestroyTheItem() {
        return willDestroyTheItem;
    }

    private boolean willDestroyTheItem;

    private String antiState;

    private String effectMessage;
    private String antiEffectMessage;

    public State(String name, boolean status, boolean willDestroyTheItem) {
        this.name = name;
        this.active = status;
        this.willDestroyTheItem = willDestroyTheItem;
    }

    public State(String name, boolean status, String antiState, boolean willDestroyTheItem) {
        this.name = name;
        this.active = status;
        this.antiState = antiState;
        this.willDestroyTheItem = willDestroyTheItem;
    }

    public String getEffectMessage() {
        return effectMessage;
    }

    public void setEffectMessage(String effectMessage) {
        this.effectMessage = effectMessage;
    }

    public void setAntiEffectMessage(String antiEffectMessage) {
        this.antiEffectMessage = antiEffectMessage;
    }

    public String getAntiEffectMessage() {
        return antiEffectMessage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean value) {
        this.active = value;
    }

    public String getName() {
        return name;
    }

    public String getAntiState() {
        return antiState;
    }

    public void setAntiState(String antiState) {
        this.antiState = antiState;
    }
}
