package ar.fiuba.tdd.tp;

/**
 * Created by panchoubuntu on 25/04/16.
 */
public class Element {

    private String name;
    private boolean state;

    public Element(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public boolean getState() {
        return state;
    }
}
