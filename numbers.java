/*
Author: Nathan Metens         CS 202      Instructor: Karla Fant
This file holds the numbers class. Here we store the num variables name
and value that was declared by the user. There are various functions here
that allow for easy access of the num variable, which is stored in a numbers
array inside the derived classes.
 */
package com.company;

public class numbers {
    private String name;
    private double value;
    private String operation;

    numbers() {
        this.name = null;
        this.value = 0.0D;
        this.operation = null;
    }

    numbers(String name, double value) {
        this.name = name;
        this.value = value;
    }

    numbers(String name, String operation) {
        this.name = name;
        this.operation = operation;
    }

    public boolean hasOperation() {
        return this.operation != null;
    }

    public String getOperation() {

        return this.operation;
    }

    public void addValue(double value) {
        this.value += value;
    }

    public double getValue() {
        return this.value;
    }

    public String getName() {

        return this.name;
    }

    public void display() {
        System.out.println("Name: " + this.name);
        System.out.println("value: " + this.value);
        System.out.println("Expression: " + this.operation);
    }
}
