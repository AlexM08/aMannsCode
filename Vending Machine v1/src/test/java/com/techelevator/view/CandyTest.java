package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CandyTest {
    private Candy candyTest;

    @Before
    public void setup(){
        candyTest = new Candy("Snickers", 1.25);
    }
    @Test
    public void CandySoundTest(){
        Assert.assertEquals("Munch Munch, Mmm-Good!", candyTest.getSound());
    }
}
