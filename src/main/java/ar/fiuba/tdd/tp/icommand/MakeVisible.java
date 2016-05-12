package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.ElementTwo;

public class MakeVisible extends ICommand {

    @Override
    public String doAction(ElementTwo element) {

        for (ElementTwo eachElement : element.getElementList()) {
            eachElement.setState(true);
        }

        String returnStr = element.getName() + " has been opened";

        return returnStr;
    }

}
