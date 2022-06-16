package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ChipTest {
    private Chip chipTest;

    @Before
    public void setup(){
        chipTest = new Chip("JavaCrisps", 1.85);
    }
    @Test
    public void ChipSoundTest(){
        Assert.assertEquals("Crunch Crunch, Crunch!", chipTest.getSound());
    }
}
