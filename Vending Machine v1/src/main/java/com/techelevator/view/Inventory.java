package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

public class Inventory {

    // Map of Product Codes(key) and Product Objects
    private Map<String, Product> inventory = new TreeMap<>();

    // totalSales to track every successful purchase for Sales Report
    private double totalSales;

    public Inventory() {}

    public void salesLog() {
        // foreach loop to generate list of Product names and quantity sold (5 - Quantity)
        for (Map.Entry<String, Product> sales : inventory.entrySet()) {
            System.out.println(sales.getValue().getName() + "|" + (5 - sales.getValue().getQuantity()));
        }
        System.out.println("");
        System.out.println("**TOTAL SALES** $" + totalSales);
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }

    public void printInventory() {
        // foreach loop to generate list for menu display
        for (Map.Entry<String, Product> entry : inventory.entrySet()) {
            String code = entry.getKey();
            Product product = entry.getValue();
            String quantity = product.getQuantity() == 0 ? "SOLD OUT" : String.valueOf(product.getQuantity());
            System.out.println(code + " " + product.getName() + " $" + String.format("%.2f", product.getPrice()) + " (" + quantity + ")");
        }
    }

    public void readInventory() {
        String vendingInventory = "vendingmachine.csv";
        File inventoryFile = new File(vendingInventory);
        try (Scanner vendingScanner = new Scanner(inventoryFile)) {
            while (vendingScanner.hasNextLine()) {
                // Split lines in .cvs file by each occurrence of |
                String[] itemSplit = vendingScanner.nextLine().split(("\\|"));
                // Assign Object values by array index
                String code = itemSplit[0];
                String name = itemSplit[1];
                double price = Double.parseDouble(itemSplit[2]);
                String type = itemSplit[3];
                // Use cascading if else statements to assign Products to correct Class to return message upon purchase
                Product product = null;
                if (type.equals("Candy")) {
                    product = new Candy(name, price);
                } else if (type.equals("Chip")) {
                    product = new Chip(name, price);
                } else if (type.equals("Drink")) {
                    product = new Drink(name, price);
                } else if (type.equals("Gum")) {
                    product = new Gum(name, price);
                }
                // Populate the inventory TreeMap with key, Object
                inventory.put(code, product);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectProduct(String productCode, Money money, TransactionLogger log) {
        // Must use .containsKey to check for valid Code user input, invalid input returns to menu
        if (!inventory.containsKey(productCode)) {
            System.out.println("Invalid product code");
            return;
        }
        // if to test user balance against purchase price
        Product product = inventory.get(productCode);
        if (product.getPrice() > money.getBalance()) {
            System.out.println("Insufficient Funds");
            // if to test Quantity value against user input Code
        } else if (product.getQuantity() == 0) {
            System.out.println("SOLD OUT");
        } else {
            // Process for successful purchase, tracks Balance, totalSales and Quantity
            money.subtractBalance(product.getPrice());
            totalSales += product.getPrice();
            product.setQuantity(product.getQuantity() - 1);
            System.out.println(product.getName() + " $" + String.format("%.2f", product.getPrice()) + " $" + String.format("%.2f", money.getBalance()));
            System.out.println(product.getSound());
            // Generates a Log.txt line for successful purchase
            log.log(product.getName(), product.getPrice(), money.getBalance());
        }
    }
}