package com.techelevator.view;

public class Candy extends Product {
    public Candy(String name, double price) {
        super(name, price);
    }

    @Override
    public String getSound() {
        return "Munch Munch, Mmm-Good!";
    }
}
