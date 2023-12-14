package com.techelevator.items;

/*
    This represents a single candy store item in the system

    This is an abstract class that should be used as a superclass for the more specific items.
 */
public abstract class CandyStoreItem {

    private String name;
    private double price;
    private String wrapped;
    private String productID;
    private int qty;
    // STEVE NOTE: have a productType variable here that the constructor for the subclasses passes in


    public CandyStoreItem(String productID, String name, double price, String wrapped) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        if (wrapped.equals("T")){
            this.wrapped = "Y";
        } else {
            this.wrapped = "N";
        }
        qty = 100;
    }

    public void deductFromQty(int amountToDeduct){
             qty -= amountToDeduct;
    }


    // GETTERS
    public int getQty(){
        return qty;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getWrapped() {
        return wrapped;
    }

    public String getProductID() {
        return productID;
    }
}
