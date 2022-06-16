package com.techelevator.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionLogger {

    // variables to set up Log.txt path, File Object and log formatting
    private String logPath = "Log.txt";
    private File logFile = new File(logPath);
    private String logMessage = "%s %s $%.2f $%.2f";

    public TransactionLogger() {
        // deletes existing Log.txt file when VendingMachine is run
        if (logFile.exists()) {
            logFile.delete();
        }
    }

    public File getLogFile() {
        return logFile;
    }

    public void log(String productName, double spent, double currentBalance) {
        // Access Java SimpleDateFormat and set variable for Log.txt message
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String strDate = dateFormat.format(date);
        String line = String.format(logMessage, strDate, productName, spent, currentBalance);
        // FileOutputStream to logFile adds new line each time log() called when append: true
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {
            writer.append(line + System.lineSeparator());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}