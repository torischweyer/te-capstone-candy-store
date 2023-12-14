package com.techelevator;

import com.techelevator.items.CandyStoreItem;
import com.techelevator.items.Chocolate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CandyInventoryTest {
    // public CandyStoreItem takeCandyFromInventory(String candyID, int amountToTake)

    private CandyInventory inventory;

    @Before
    public void make_inventory(){
        inventory = new CandyInventory("inventory.csv");
    }

    @Test
    public void test_takeCandyFromInventory_returns_correct_item(){
        CandyStoreItem testCandyControl = new Chocolate("C1", "Snuckers Bar", 1.35, "Y");
        CandyStoreItem testCandyTaken = inventory.takeCandyFromInventory("C1", 10);
        Assert.assertEquals(testCandyControl.getProductID(), testCandyTaken.getProductID());
        Assert.assertEquals(testCandyControl.getName(), testCandyTaken.getName());
        Assert.assertEquals(testCandyControl.getPrice(), testCandyTaken.getPrice(), 0.00);
    }

    @Test
    public void test_takeCandyFromInventory_updates_qty_in_inventory(){
        CandyStoreItem testUpdatedQtyChocolate = inventory.takeCandyFromInventory("C1", 10);
        Assert.assertEquals(90, testUpdatedQtyChocolate.getQty());
    }


}

