package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

    private PrintWriter out;
    private Scanner in;

    public Menu(InputStream input, OutputStream output) {
        // Instantiates Menu class to take user input and generate Log.txt records
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        // try-catch block to ensure method accepts only valid menu options
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        // choice variable goes into VendingMachineCLI run() method
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        // i < 3 to stop for loop from printing hidden Sales Report option at options[3]
        for (int i = 0; i < 3; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    public double requestMoney() {
        out.println();
        out.println("How many dollars would you like to insert?");
        out.flush();
        String userInput = in.nextLine();
        // moneyInserted is tested for valid inputs in Money Class
        double moneyInserted = Double.parseDouble(userInput);
        return moneyInserted;
    }

    public String selectProduct() {
        out.println();
        out.println("Please select your snack!");
        out.flush();
        String userInput = in.nextLine();
        return userInput;
    }
}

