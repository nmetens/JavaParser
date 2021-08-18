/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
Arithmetic is derived from the abstract base class parser. Here, all of the arithmetic
operations and expressions are conducted. First a copy of the first task is made using
the copy constructor, then parsing and comparing take place.
 */
package com.company;

public class arithmetic extends parser {
    public int answer; // the answer of the operation
    arithmetic(){
        answer = 0;
    }
    arithmetic(parser p) { // copy constructor
        // a derived class is a base class plus more, so this copies the base classes data
        this.tokens = p.tokens;
        this.code = p.code;
        this.alph_values = p.alph_values;
        this.equations = p.equations;
        this.values = p.values;
    }

    // function parses the values that were established in the variable class and then was copied into this class
    // each line is parsed but checked for arithmetic purposes here
    public int parsing() {
        if(values != null) { // parses the numerical values
            for (int i = 0; i < values.length; i++) {
                if(values[i] == null) return 0; // end of the values
                if (values[i].hasOperation()) { // if that is true
                    double new_value = compare(values[i].getOperation());
                    this.values[i].addValue(new_value);
                }
            }
            return 1;
        }
        return 0;
    }

    // passes the operation of a numerical value and parses it further to add the value of the operation into the number's value
    public double compare(String var){ // compares the arithmetic symbols in a line of code provided by the user
        String [] split = var.split(" "); // splits the operation of a number into tokens for arithmetic purposes
        for (int i=0; i < split.length; i++) {
            int index = 0; boolean flag = false; // goes through the values to find a matching value name as in the code
            while (values[index] != null && flag == false) {
                if (split[i].compareTo(values[index].getName()) == 0) { // the name of a variable is wanting to be displayed
                    split[i] = "" + values[index].getValue(); // stores the value of a variable into the string
                    flag = true;
                }
                index++;
            }
            // the name of a variable is wanting to be displayed
            if (split[1].compareTo("+") == 0) { // check for the plus operator between the two numbers
                // each number is checked and the operator or expression between them is also checked to perform the correct task
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]); // at the first split index, the first variable is waiting
                    Double stringToDoub2 = Double.parseDouble(split[2]); // after the third space is another value of num (num x = 10)
                    return stringToDoub1 + stringToDoub2; // returns the value in the expression
                }
            } else if (split[1].compareTo("-") == 0) {
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]);
                    Double stringToDoub2 = Double.parseDouble(split[2]);
                    return stringToDoub1 - stringToDoub2; // returns the value in the expression
                }
            } else if (split[1].compareTo("*") == 0) {
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]);
                    Double stringToDoub2 = Double.parseDouble(split[2]);
                    return stringToDoub1 * stringToDoub2; // returns the value in the expression
                }
            } else if (split[1].compareTo("/") == 0) {
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]);
                    Double stringToDoub2 = Double.parseDouble(split[2]);
                    return stringToDoub1 / stringToDoub2; // returns the value in the expression
                }
            } else if (split[1].compareTo(">") == 0) {
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]);
                    Double stringToDoub2 = Double.parseDouble(split[2]);
                    if(stringToDoub1 > stringToDoub2)
                        return 9999; // this will act as a true value
                    else return -9999; // this will act as a false value
                }
            } else if (split[1].compareTo("<") == 0) {
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]);
                    Double stringToDoub2 = Double.parseDouble(split[2]);
                    if(stringToDoub1 < stringToDoub2)
                        return 9999;
                    else return -9999;
                }
            } else if (split[1].compareTo("==") == 0) {
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]);
                    Double stringToDoub2 = Double.parseDouble(split[2]);
                    if(stringToDoub1.equals(stringToDoub2))
                        return 9999;
                    else return -9999;
                }
            } else if (split[1].compareTo("!=") == 0) {
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]);
                    Double stringToDoub2 = Double.parseDouble(split[2]);
                    if(!stringToDoub1.equals(stringToDoub2))
                        return 9999;
                    else return -9999;
                }
            } else if (split[1].compareTo(">=") == 0) {
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]);
                    Double stringToDoub2 = Double.parseDouble(split[2]);
                    if(stringToDoub1 >= stringToDoub2)
                        return 9999;
                    else return -9999;
                }
            } else if (split[1].compareTo("<=") == 0) {
                if((split[0].matches("\\d+\\.\\d+") || split[0].matches("\\d+")) && (split[2].matches("\\d+\\.\\d+") || split[2].matches("\\d+"))) {
                    Double stringToDoub1 = Double.parseDouble(split[0]);
                    Double stringToDoub2 = Double.parseDouble(split[2]);
                    if(stringToDoub1 <= stringToDoub2)
                        return 9999;
                    else return -9999;
                }
            }
        }
        return 1;
    }

    // This is the display function, which is not used in this class, but remains here so that the class is not abstract
    public void show() {
        System.out.println("--------------------"); // output the results to the user
        if(values[0] != null) {
            // displays the nums
            for (numbers value : values) value.display();
        }
        // displays the alphs
        if(alph_values[0] != null) {
            for (alphabet alph_value : alph_values) alph_value.display();
        }
    }
}