/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
This is the simple display items class that holds the values
of certain strings that are written after the show (display)
function in the language and then displays them when called by
the derived classes.
 */
package com.company;

public class display_items {
    private String to_display; // this is what will be displayed when the user types 'show = '

    display_items(String to_display) { // sets the item to display in the class
        this.to_display = to_display;
    }

    public void display() { // displays the item in the class
        System.out.println(to_display); // displays everything after the 'show = ' call
    }

}
