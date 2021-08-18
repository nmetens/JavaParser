/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
Here, we create the node class that contains pointers to left, right,
and parent nodes. Each node also contains a node color enum that will
be set to red by default. The node class has a lessThan function that
compared the first letter of each student's name to the root node. This
function is used when inserting new data. The node has various getters and
setters for the private member data as well.
 */
package com.company;

enum nodeColor{ // each node can either be red or black
    RED, BLACK;
 }

public class node {
    private String name; // this is the name of a student
    private nodeColor color;
    private node left;
    private node right;
    private node parent; // must keep track of parent node

    // this is how the children's code will be stored int the node:
    private parser first_task; // this is the abstract base class parser contained in a node
    private parser second_task;
    private parser final_task;

    public node(String new_student){ // constructor
        this.name = new_student;
        this.color = nodeColor.RED; // set each root node to be red
        this.first_task = null;
        this.second_task = null;
        this.final_task = null;
    }

    // This function calls the parser hierarchy classes in the order they are
    // meant to work together so that they store and check for errors the code
    // of the user.
    public int creatingProgram(){
        // create an abstract base class object that is a new derived class
        first_task = new variable(); // dynamic binding

        if (first_task.parsing() == 0) { // accesses the parsing function in variable
            return 0; // there was an error in the program
        }

        // create a new base class object and copy the first task to the next, saving all of the previous work
        second_task = new arithmetic(first_task); // copies data from first task in order to work on it separately in the next derived class
        second_task.parsing(); // calls the arithmetic parse which checks for +, -, *, / operators as well as the relational operators

        // passes the data from the last two tasks into the last task, which is checking if there is a show function
        final_task = new functions(second_task);
        final_task.parsing(); // conducts final parse before showing output to the user
        return 1; // the program was created without any errors
    }

    // here we display the code of the user who created it.
    public void displayingProgram(){
        System.out.println("The program output created by '" + name + "' is: ");
        if(final_task != null) {
            final_task.show();
            return;
        }
        else{
            System.out.println("ERROR: Program cannot execute.");
        }
    }

    // this function compares the first char in the root's name and in the name_to_compare.
    // The function will compare the ASCII values of the first char, sorting the names of
    // each child in alphabetical order inside of the red-black tree.
    public boolean lessThan(String name_to_compare) { // will sort the names of each kid in alphabetical order
        if(name_to_compare.charAt(0) < name.charAt(0)){ // compare the first letter of each name to order names alphabetically
            return true;
        }
        else return false;
    }

    // setter and getter for the left node pointer
    public node getLeft() {
        return left;
    }
    public void setLeft(node left) {
        this.left = left;
    }

    // setter and getter for the right node pointer
    public node getRight() {
        return right;
    }
    public void setRight(node right) {
        this.right = right;
    }

    // setter and getter for the color of a node
    public nodeColor getColor() {
        return color;
    }
    public void setColor(nodeColor color) {
        this.color = color;
    }

    // setter and getter for the parent of a node
    public node getParent() {
        return parent;
    }
    public void setParent(node parent) {
        this.parent = parent;
    }

    // setter and getter for the name of the student
    public String getName(){
        return name;
    }
    public void setName(String data) {
        this.name = data;
    }

    // display the student name
    public void displayName(){
        System.out.println(name + " ");
    }
}
