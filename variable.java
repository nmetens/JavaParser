/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
This is another derived class from the parser class. Here, we take each
line of code from the parser class and parse it further to determine
if the code inputed is in the ocrrect format. If there are no errors,
then the parsed code is placed into the correct variable type (either
alph or num). Then the values and names of each variable is stored in
a numbers or alphabet array for later use.
 */
package com.company;
import java.util.Scanner;

public class variable extends parser {
    protected Scanner input = null;
    private double num;
    private String alph;

    variable() {
        input = new Scanner(System.in);
        this.num = 0;
        this.alph = "";
    }

    // Here, we call the super class's (parser) parsing function, which allows the user to enter
    // code that is stored into an array of strings called "tokens". The we are able to access
    // each line separately and act on them in the derived parsing function, which determines the
    // validity of the token string (are there errors). Then we assign the name and values to the
    // correct variables and store them in their own array for later use.
    public int parsing() {
        super.parsing(); // calls parent parsing function where user code is read and parsed
        this.values = new numbers[tokens.length]; // create an array of numbers that hold names and values for arithmetic
        this.alph_values = new alphabet[tokens.length]; // creates an array of alphabet variables
        for(int i=0; i<tokens.length; i++) {
            double result = compare(tokens[i]); // get the return value of the compare function
            if(result == -1.0) { // check for possible errors and tell the user what was wrong
                System.out.println("ERROR: the value assigned to num was not a number.");
                return 0;
            } else if(result == -2.0) {
                System.out.println("ERROR: neither the variable 'num' nor 'alph' was detected.");
                return 0;
            } else if(result == -3.0) {
                System.out.println("ERROR: syntax was incorrect when assigning a variable. Missing a '='?");
                return 0;
            } else if(result == -4.0) {
                System.out.println("ERROR: variable assignment '=' operator was not detected.");
                return 0;
            } else if(result == -5.0) {
                System.out.println("ERROR: the name of a variable was not valid.");
            }
        }
        return 1; // there was no error in the code
    }

    // compare the splits of each line of code with the "show", "num", and "alph" programming elements.
    // If the variables are correct, we store their values here. All numbers can be inputed as ints or
    // doubles, but for the sake of simplicity, we store them here as doubles.
    public double compare(String var) {
        String [] split = var.split(" ");
        if (split.length < 4 && split[0].compareTo("show") != 0) // check if there is a variable, variable name, assignment, and a value
            return -3;
        if(split.length > 4 && split.length % 2 != 0 && split[0].compareTo("alph") != 0 && split[0].compareTo("show") != 0) // if there are an odd number of tokens, the code has an error
            return -3;
        if (split[0].compareTo("num") == 0) { // a num variable was declared
            if(split[2].compareTo("=") != 0)
                return -4; // there was no assignment operator for the variable
            else if(split[1].matches("\\d+") || split[1].matches("\\d+\\.\\d+")) // check if the name of the variable "num" is valid (i.e. not a number)
                return -5;
            else if(!split[3].matches("\\d+")) //|| !split[3].matches("\\d+\\.\\d+"))
                return 0; // the value assigned to num was not a number
            else {
                if(split.length == 4) { // the variable has numerical values, it is a basic "num x = 10" assignment, so it is stored in name and value
                    Double stringToInteger = Double.parseDouble(split[3]); // after the third space is the value of num (num x = 10)
                    this.num = stringToInteger; // assign it to the num variable
                    int index = 0; int flag = 0;
                    while (flag == 0) {
                        if (this.values[index] == null) { // find an open index to store the number
                            this.values[index] = new numbers(split[1], stringToInteger);
                            flag = 1; // an index has been set
                        }
                        index++;
                    }
                    return 1;
                }
                else { // if the length of the split is greater than or less than 4 we come here
                    // here we have assignments such as these: "num = x + 10" or "num = 6 / 3" these are expressions that must be parsed in the
                    // arithmetic class, so we just store them as expressions instead of values.
                    int index = 0; int flag = 0;
                    String expression = "";
                    while (flag == 0) {
                        if (this.values[index] == null) { // find an open index to store the number
                            for(int i=3; i< split.length; i++){
                                expression += split[i] + " ";
                            }
                            this.values[index] = new numbers(split[1], expression);
                            flag = 1; // an index has been set
                        }
                        index++;
                    }
                    return 1;
                }
            }
        } else if (split[0].compareTo("alph") == 0) { // the string is not an int, so it is just a string of data
            if(split[2].compareTo("=") != 0)
                return -4; // there was no assignment operator for the variable
            for (int i = 3; i < split.length; i++) {
                if (i != 3) this.alph += " "; // put spaces before each word
                this.alph += split[i]; // concatenate the data
            }
            for (int i=0; i< alph_values.length; i++){
                if(alph_values[i] == null)
                    alph_values[i] = new alphabet(split[1], this.alph);
            }
            return 1; // an "alph" string was correctly declared
        }  else if (split[0].compareTo("show") == 0) {
            return 1; // this is a function that will be evaluated later
        } else {
            return -2; // the variable type given by the user was not one in the language
        }
    }

    public void show() { // this display is not used here, I only used the functions display in this program
        System.out.println("--------------------"); // output the results to the user
        if(values == null) return;
        for (numbers value : values) value.display();
        //System.out.println("alph = " + this.alph);
    }
}