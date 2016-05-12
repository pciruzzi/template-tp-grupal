package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

public abstract class ICommand {

    String name;

    public abstract String doAction(Element element);

    public String doAction(Element originElement, Element element, Element destinationElement) {
        return "Recatate gato o te rebano el rabo";
    }

    public String getName() {
        return name;
    }

}
