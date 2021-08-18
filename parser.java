/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
This is the abstract base class file. All of the protected data members
are accessible to the derived classes for their jobs and the hierarchy
works together to divide up the work into different classes and function
operations.
 */
package com.company;
import java.util.Scanner;

abstract class parser {
    protected String [] tokens; // holds each line of user code
    protected String code;
    protected alphabet [] alph_values; // holds the names and strings of each alph created by the user
    protected int [] equations; // holds the equations from the user input code
    protected numbers [] values; // holds the name and value of each variable created by the user

    parser() { // sets all data initially to null
        tokens = null;
        code = "";
        alph_values = null;
        equations = null;
        values = null;
    }
    // pure virtual functions that are overridden in the derived classes
    abstract double compare(String var);
    abstract void show();

    // this is the main parsing function that takes in the user code and
    // splits each line after the user types in a space. Each line of code
    // created by the user is stored in the tokens array, which is then copied
    // and used in the derived classes for further parsing.
    public int parsing() {
        System.out.println("start");
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("stop");
        this.code = scan.next(); // reads multiple lines of user input
        // code is parsed into lines with "\n\" as a deliminator
        String [] split = this.code.split("\\n"); // each enter key by the user is the deliminator
        int index = 0;
        for(String x: split){
            index++; // counting the number of lines of code
        }
        String [] parsed_array = new String[index]; // parsed array will hold all tokens
        this.tokens = new String[index];
        int parsed_index = 0;
        for(String x: split){
            parsed_array[parsed_index] = x; // adds each line of code to the parsed array
            this.tokens[parsed_index] = x;
            parsed_index++;
        }
        return 1;
    }
}