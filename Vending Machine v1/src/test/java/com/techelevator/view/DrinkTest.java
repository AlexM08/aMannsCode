package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class DrinkTest {
    private Drink drinkTest;

    @Before
    public void setup(){
        drinkTest = new Drink("Dr FizzBuzz", 1.85);
    }
    @Test
    public void DrinkSoundTest(){
        Assert.assertEquals("Cheers Glug, Glug!", drinkTest.getSound());
    }
}
