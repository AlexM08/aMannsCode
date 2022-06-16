package com.techelevator.view;


public abstract class Product {

    // Set up needed variables for Product Objects
    private String name;
    private double price;
    // VendingMachine stock limit of 5, so each Product starts at quantity 5
    private int quantity = 5;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Override in each subclass type
    public abstract String getSound();

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}

