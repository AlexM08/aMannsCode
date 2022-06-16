package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class GumTest {
    private Gum gumTest;

    @Before
    public void setup(){
        gumTest = new Gum("Five Gum", .75);
    }
    @Test
    public void GumSoundTest(){
        Assert.assertEquals("Chew Chew, Pop!", gumTest.getSound());
    }
}
