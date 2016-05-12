package ar.fiuba.tdd.tp.icommand;

import ar.fiuba.tdd.tp.engine.Element;

public class MakeVisible extends ICommand {

    @Override
    public String doAction(Element element) {

        for (Element eachElement : element.getElementList()) {
            eachElement.setState(true);
        }

        String returnStr = element.getName() + " has been opened";

        return returnStr;
    }

}
