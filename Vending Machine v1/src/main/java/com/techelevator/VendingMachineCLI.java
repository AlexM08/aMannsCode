package com.techelevator;

import com.techelevator.view.Inventory;
import com.techelevator.view.Menu;
import com.techelevator.view.Money;
import com.techelevator.view.TransactionLogger;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    // Set up hidden Sales Log option in Main Menu
    private static final String MAIN_MENU_OPTION_SALES_LOG = "Sales Log";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_LOG};
    // Set up variables and display array for Purchase Menu
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    // run() does the real work
    public void run() {
        boolean vending = true;
        // Better to instantiate Inventory in the Inventory class
        Inventory inventory = new Inventory();
        inventory.readInventory();
        Money money = new Money(0);
        TransactionLogger log = new TransactionLogger();

        // menu is displayed while vending is true
        while (vending) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // inventory TreeMap called and printed
                inventory.printInventory();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // I like this while conditional to set a variable state and keep menus open
                boolean purchasing = true;
                while (purchasing) {
                    System.out.println("Current Money Provided: $" + String.format("%.2f", money.getBalance()));
                    String purchase = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                    // if tests for valid user input
                    if (purchase.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                        double fedMoney = menu.requestMoney();
                        // if tests to only accept positive, whole number values
                        if (!(fedMoney < 0) && !(fedMoney % 1 == 0)) {
                            purchasing = false;
                            System.out.println("Please enter valid amount");
                        } else {
                            money.addBalance(fedMoney);
                            log.log("FEED MONEY:", fedMoney, money.getBalance());
                        }
                    } else if (purchase.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                        inventory.printInventory();
                        String productCode = menu.selectProduct();
                        inventory.selectProduct(productCode, money, log);
                    } else if (purchase.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                        purchasing = false;
                        System.out.println("Please take your change!");
                        money.returnChange(log);
                    }
                }
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                vending = false;
            } else if (choice.equals(MAIN_MENU_OPTION_SALES_LOG)) {
                // Calls and prints our inventory.salesLog()
                inventory.salesLog();
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
