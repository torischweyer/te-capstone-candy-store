package com.techelevator;

import org.junit.*;

public class CandyStoreTest {
    // public void makePurchase(String candyID, int qtyToBuy) ---- CandyStore
    // public void addItemToCart(ShoppingCartItem candyToAdd) ---- ShoppingCart through CandyStore
    private CandyStore store;

    @Before
    public void make_inventory_and_cart(){
        store = new CandyStore();
    }

    @Test
    public void test_makePurchase_and_addItemToCart_adds_correct_item(){
        store.makePurchase("S3", 2);
        store.makePurchase("C1", 50);
        Assert.assertEquals("Sour Tart", store.getCartItem("S3").getName());
        Assert.assertEquals(1.35, store.getCartItem("C1").getPrice(), 0.00);
    }

    @Test
    public void test_makePurchase_and_addItemToCart_updates_qty_instead_of_replace(){
        store.makePurchase("L1", 10);
        store.makePurchase("L1", 5);
        Assert.assertEquals(15, store.getCartItem("L1").getQty());
    }

}
