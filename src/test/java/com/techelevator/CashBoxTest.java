package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CashBoxTest {
    /*
    public Change getChange()

    private double totalChange;
    private int twenties;
    private int tens;
    private int fives;
    private int ones;
    private int quarters;
    private int dimes;
    private int nickels;
    */

    // test that denominations are correct for change passed

    private Change testChange;
    private CandyStore testCandyStore;

    @Before
    public void new_testChange(){
        testChange = new Change();
        testCandyStore = new CandyStore();
    }

    @Test
    public void test_that_denominations_are_correct_for_change_passed(){
        // Cash Box Balance to convert to change:
        testCandyStore.addMoney(99.85);
        testChange = testCandyStore.calculateChange();

        Assert.assertEquals(4, testChange.getTwenties());
        Assert.assertEquals(1, testChange.getTens());
        Assert.assertEquals(1, testChange.getFives());
        Assert.assertEquals(4, testChange.getOnes());
        Assert.assertEquals(3, testChange.getQuarters());
        Assert.assertEquals(1, testChange.getDimes());
        Assert.assertEquals(0, testChange.getNickels());
    }




}
