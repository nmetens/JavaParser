/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
This is the functions derived class. Here, we parse the code one last
time to find what function the user would like to call. The only function
call I had time to implement was the 'show' function, which is like the
'cout' call in C++. Whatever is placed after the 'show = ' call will be
stored and displayed at the end of the function in the order it was
coded by the user.
 */
package com.company;

public class functions extends parser {
    private int loop_count;
    private display_items[] items;
    private int display_count; // holds what index we are at for the display_items array

    public functions() {
        loop_count = 0; // the given number of iterations
        display_count = 0;
    }

    // this is an argument constructor that copies each of the passed in objects
    // data so that we can use it here in this class.
    public functions(parser obj) {
        // a derived class is a base class plus more, so this copies the base classes data
        this.tokens = obj.tokens;
        this.code = obj.code;
        this.alph_values = obj.alph_values;
        this.equations = obj.equations;
        this.values = obj.values;
        items = new display_items[this.tokens.length]; // holds the items to display
    }

    // We test each token index to see if there is a function call
    // needing to be executed. Each split[0] is checked in the compare
    // function.
    public int parsing() {
        for (int i = 0; i < tokens.length; i++) {
            compare(tokens[i]);
        }
        return 0;
    }

    // This function compares each token created by the base class.
    // We look to see if we have a function call 'show' as the first
    // split in the user code. If we do, we extract each variable that
    // needs to be displayed and display it. If 'show' is not present, we exit.
    public double compare(String var) {
        String[] split = var.split(" ");
        if (split[0].compareTo("show") == 0) {
            String to_display = "";
            for (int i = 2; i < split.length; i++) { // increments values to display after equal sign
                int index = 0;
                boolean flag = false;
                while (values[index] != null) {
                    if (split[i].compareTo(values[index].getName()) == 0) { // the name of a variable is wanting to be displayed
                        to_display += values[index].getValue() + " "; // stores the value of a variable into display
                        flag = true;
                    }
                    index++;
                }
                if (to_display.compareTo("9999.0 ") == 0) { // checks for false expression value
                    to_display = "TRUE";
                } else if (to_display.compareTo("-9999.0 ") == 0) { // checks for true expression value
                    to_display = "FALSE";
                }
                if (flag == false) {
                    to_display += split[i] + " "; // no expression value, so just add the values
                }
            }
            items[display_count] = new display_items(to_display);
            display_count++;
        }
        return 0; // returns 0.0
    }

    // Here we finally display the user code back onto the screen for them. After
    // all of the parsing and saving of data and values, we display if the user
    // has successfully called the 'show = ' function.
    public void show() {
        if (items != null) { // check if there was any code
            System.out.println("--------------------------");

            for(int i=0; i< items.length; i++) {
                if (items[i] == null)
                    return; // we have reached a null ptr, so exit
                items[i].display();
            }
        }
    }
}
