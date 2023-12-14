package com.techelevator;

import com.techelevator.items.*;

public class ShoppingCartItem {

    private String name;
    private double price;
    private String productID;
    private int qty;
    private String productType;

    // PASS CANDY OBJECT INTO CONSTRUCTOR -AND- CHECK CANDY SUBCLASS FOR PRODUCT TYPE
    public ShoppingCartItem(CandyStoreItem candy){
        // PULL VALUES OUT OF OBJECT
        this.name = candy.getName();
        this.price = candy.getPrice();
        this.productID = candy.getProductID();
        qty = 0;
        // STEVE NOTE: Have these be candy member variables (instead of instanceof)
            // product type string would be in the constructor for the subclass
        if (candy instanceof Chocolate)  {
            productType = "Chocolate Confectionery";
        } else if (candy instanceof HardCandy) {
            productType = "Hard Tack Confectionery";
        } else if (candy instanceof Licorice) {
            productType = "Licorice and Jellies";
        } else if (candy instanceof SourCandy) {
            productType = "Sour Flavored Candies";
        }

    }

    public void addToQty(int amountToAdd){
        qty += amountToAdd;
    }


    public void setShoppingCartQty(int amountToAdd){
        qty = amountToAdd;
    }


    // GETTERS
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getProductID() {
        return productID;
    }

    public int getQty() {
        return qty;
    }

    public String getProductType() {
        return productType;
    }
}
