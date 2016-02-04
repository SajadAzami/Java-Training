/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancechecker;

import java.util.ArrayList;

/**
 *
 * @author Sajad Azami
 */
public class Stack<O> {

    ArrayList<O> array;

    public Stack() {
        array = new ArrayList<>();
    }

    public O push(O object) {
        array.add(object);
        return object;
    }

    public boolean pop(O object) {
        if (!array.isEmpty() && array.get((array.size()) - 1).equals(object)) {
            array.remove(((array.size()) - 1));
            return true;
        } else {
            System.out.println("The line is not balanced!");
            return false;
        }
    }

    public Boolean isEmpty() {
        return array.isEmpty();
    }

    public int getSize() {
        return array.size();
    }

}
