package com.techelevator.view;

public class Gum extends Product {
    public Gum(String name, double price) {
        super(name, price);
    }
    @Override
    public String getSound() {
        return "Chew Chew, Pop!";
    }
}
