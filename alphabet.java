/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
This is the class that stores all of the declared alph variables.
 */
package com.company;

public class alphabet {
    private String name; // each alph has a name
    private String value; // and a value

    alphabet() {
        name = null;
        value = null;
    }
    alphabet(String name, String value) { // argument constructor (setter)
        this.name = name;
        this.value = value;
    }
    public void display(){ // displays the alph variable
        System.out.println("Name: " + name);
        System.out.println("Value: " + value);
    }
}
