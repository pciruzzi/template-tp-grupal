package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.State;
import ar.fiuba.tdd.tp.games.Game;

import java.util.ArrayList;
import java.util.List;



public class HanoiTowers extends Game {

    @Override
    public void createGame() {
        Element diskOne = new Element("diskOne", "colummOne", 1);
        diskOne.addActionState("colummOne", "colummOne");
        diskOne.addActionState("colummTwo", "colummTwo");
        diskOne.addActionState("colummThree", "colummThree");
        Element diskTwo = new Element("diskTwo", "colummOne", 2);
        diskTwo.addActionState("colummOne", "colummOne");
        diskTwo.addActionState("colummTwo", "colummTwo");
        diskTwo.addActionState("colummThree", "colummThree");
        Element diskThree = new Element("diskThree", "colummOne", 3);
        diskThree.addActionState("colummOne", "colummOne");
        diskThree.addActionState("colummTwo", "colummTwo");
        diskThree.addActionState("colummThree", "colummThree");

        actualState = new State();
        actualState.addElement(diskOne);
        actualState.addElement(diskTwo);
        actualState.addElement(diskThree);

        finalState = new State();
        finalState.addElement(new Element("diskOne", "colummThree"));
        finalState.addElement(new Element("diskTwo", "colummThree"));
        finalState.addElement(new Element("diskThree", "colummThree"));

        actualState.addDesiredState(finalState);
        actualState.addNextState(finalState);
    }

    @Override
    public String doAction(String action) {

        String[] partes = action.split(" ");

        List<Element> elements = actualState.getElementList();
        List<Element> elementosDeMiPila = new ArrayList<>();
        List<Element> elementosOfOtherPile = new ArrayList<>();
        for ( Element elemento : elements) {
            if (elemento.getState().equals(partes[0])) {
                elementosDeMiPila.add(elemento);
            }
        }

        for ( Element elemento : elements) {
            if ( elemento.getState().equals(partes[1])){
                elementosOfOtherPile.add(elemento);
            }
        }
        int indexOfSmallest = 0;
        int smallest = 999999;
        for (int i = 0; i < elementosDeMiPila.size(); i++){
            if ( elementosDeMiPila.get(i).getSize() < smallest ) {
                smallest = elementosDeMiPila.get(i).getSize();
                indexOfSmallest = i;
                System.out.println("Index samllest: " + indexOfSmallest + " smallest: " + smallest);
            }
        }

//        int indexOfSmallestOtherColumm = 0;
        int smallestOtherColumm = 888888;
        for (int i = 0; i < elementosOfOtherPile.size(); i++) {
            if ( elementosOfOtherPile.get(i).getSize() < smallest ) {
                smallestOtherColumm = elements.get(i).getSize();
//                indexOfSmallestOtherColumm = i;
                System.out.println("Index samllest: " + indexOfSmallest + " smallest: " + smallest);
            }
        }
        if ( ( smallestOtherColumm > smallest) && ( smallest < 999999 ) ) {
            String nombre = elementosDeMiPila.get(indexOfSmallest).getName();
            System.out.println("Muevo el tamanio: " + elementosDeMiPila.get(indexOfSmallest).getSize() + " con el nombre: " + nombre);
            actualState.doAction(partes[1],nombre);
        }
        if ( smallest > 5) {
            return "No habia nada en esa pila";
        }
        if ( smallestOtherColumm < smallest ) {
            return "No podes poner uno mas grande arriba de uno chico";
        }



        if( actualState.isEqual(finalState)) {
            return "Hanoi la chupaste";
        } else {
            return "Seguis jugando";
        }
//        finalState.doAction("topTwo", "diskOne");
//        return "Hanoi chupala";



    }
}
