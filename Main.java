/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
This is the main class for program #4. Here, there is a menu interface for the user
and dynamic binding takes place to allow for each class to do its job. The classes
are derived from an abstract base class object and then copied into the next class
for a different job to be accomplished. The user must follow the language requirements
precisely. The language is made simple for children to easily understand and create
simple programs for arithmetic purposes.
 */
package com.company;
import java.util.Scanner;

/******************
 Example program:
 ******************
 // LEAVE SPACES BETWEEN EACH INPUT NUMBER, VALUE, NAME, VARIABLE, AND ARITHMETIC
 ******************
 start // provided starting point of the user program
 num x = 10 // declare numerical variable
 num y = 11
 alph name = nathan metens // declare an alphabetical variable
 num z = x + y // perform arithmetic operations on numbers and variables (num z = 10 + 6 works too)
 show = the value of z= z
 stop // ends the user input and displays whatever is asked after the 'show =' call
 ******************/

public class Main {
    public static void main(String[] args){
        boolean flag = true; // we are in the menu
        redBlackTree Tree = new redBlackTree(); // create the balanced tree for storing names and code

        do { // repeat the menu until the user sets the flag to false, ending the program
            System.out.println("\n****************************");
            System.out.println("Programming Menu");

            System.out.println("\n1. Sample Program\n2. Begin a Program\n3. Display all Programs\n4. Quit Program"); // menu display

            Scanner scanOption = new Scanner(System.in);
            System.out.println("Enter a menu option: "); // prompts user for option, reading it with Scanner
            int option = scanOption.nextInt();

            switch (option) {
                case 1: // view a test program and the rules
                    System.out.println("\nProgram Rules:\n" +
                            " 1) There are two variables: num, alph (num is for numbers and alph is for letters/ words)\n" +
                            " 2) There are no semi-colons at the end of lines, just hit enter\n" +
                            " 3) Leave spaces between each variable name and equal sign and number and arithmetic operator and expression\n" +
                            " 4) Each variable needs a name: 'num x = 10' or 'alph name = nathan metens'\n" +
                            " 5) The program starts at the 'start' line and ends when the user types 'stop' (then hits the enter key)\n" +
                            " 6) To display code, simply type 'show = ' and then type whatever you want to display after the equal sign");
                    System.out.println("\nSample program 1 (follow the same type of format):\n" +
                            "start\n" + "num x = 10\n" + "num y = 5\n" + "num z = x / y\n" +
                            "show = x= x and y= y . The answer to x/y = z\n" + "stop");
                    System.out.println("\nSample program 2 (follow the same type of format):\n" +
                            "start\n" + "num x = 100\n" + "num y = 71\n" + "num a = y > x\n" +
                            "show = x= x and y= y . The answer to y>x = a\n" + "stop");

                    break;

                case 2: // begin a program
                    Scanner scanName = new Scanner(System.in);
                    System.out.println("Please enter your name: ");
                    String name = scanName.nextLine(); // gets the child's name
                    // stores the child's name in the tree
                    Tree.insert(name); // then asks the user to type in their code that is also stored in the tree's nodes
                    break;

                case 3: // display all of the programs
                    if(Tree.display() == 0)
                        System.out.println("There is nothing to display.");
                    break;
                case 4: // exit the menu
                    flag = false; // the user wishes to exit the menu
                    break;
                default: // user typed an option other than 1, 2, or 3
                    System.out.println("You did not enter a valid menu option. Please try again.");
                    break;
            }
        } while (flag); // until the flag is false, the menu will keep displaying
    }
}