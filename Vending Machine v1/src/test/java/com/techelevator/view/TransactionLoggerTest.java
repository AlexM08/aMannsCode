package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TransactionLoggerTest extends TestCase {
    //Log.txt only generates on first vending machine run.
    String logPath = ("Log.txt");
    File logFile = new File(logPath);

    @Before
    public void setup() {

    }

    @Test
    public void testGetLogFile() {
        //Arrange
        logFile.exists();
        //Act

        //Assert
        Assert.assertEquals(true, logFile.exists());
    }
}
//    @Test
//    public void testLog() {
//        String productName = "Bag of Chips";
//        double spent = 1.50;
//        double currentBalance = 5.00;
//        String line = String.format(productName, spent, currentBalance);
//
//        Assert.assertEquals;
//    }
//}