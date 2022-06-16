package com.techelevator.view;

public class Chip extends Product {

    public Chip(String name, double price) {
        super(name, price);
    }

    @Override
    public String getSound() {
        return "Crunch Crunch, Crunch!";
    }
}
