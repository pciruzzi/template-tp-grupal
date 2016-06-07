package ar.fiuba.tdd.tp.server;

public class InteractorStatus {

    private Interactor interactor;
    private boolean hasWon;
    private boolean hasLost;

    public InteractorStatus(Interactor interactor) {
        this.interactor = interactor;
        hasWon = false;
        hasLost = false;
    }

    public boolean getHasWon() {
        return this.hasWon;
    }

    public boolean getHasLost() {
        return this.hasLost;
    }

    public Interactor getInteractor() {
        return this.interactor;
    }

    public void won() {
        this.hasWon = true;
    }

    public void lost() {
        this.hasLost = true;
    }
}
