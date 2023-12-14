package com.techelevator;

import com.techelevator.filereader.LogFileWriter;
import com.techelevator.items.CandyStoreItem;

import java.util.List;

/*
    This class should encapsulate all the functionality of the Candy Store application, meaning it should
    contain all the "work"
 */
public class CandyStore {

    private CandyInventory candyInventory = new CandyInventory("inventory.csv");
    private CashBox cashBox = new CashBox();
    private ShoppingCart cart = new ShoppingCart();
    private LogFileWriter logWriter = new LogFileWriter();


    public List<CandyStoreItem> getInventoryList(){
        return candyInventory.getListOfCandy();
    }
    public List<ShoppingCartItem> getShoppingCartList() {
        return cart.getCartList();
    }

    public double getBalance(){
        return cashBox.getBalance();
    }
    public void BalanceReset()  {
        cashBox.BalanceReset();
    }

    // TAKE MONEY
    public boolean addMoney(double amountToAdd) {
        double beforeBalance = cashBox.getBalance();
        boolean moneyWasAdded = cashBox.addMoney(amountToAdd);
        if (moneyWasAdded) {
            logWriter.writeCashFlowToLog("MONEY RECEIVED", beforeBalance, cashBox.getBalance());
        }
        return moneyWasAdded;
    }
    // SELECT PRODUCT
    public void makePurchase(String candyID, int qtyToBuy) {
        // TURNS CANDY ITEM OF CHOICE INTO SHOPPING CART ITEM -- SET QTY TO BUY -- ADD TO CART
        ShoppingCartItem candyToBuy = new ShoppingCartItem(candyInventory.takeCandyFromInventory(candyID, qtyToBuy));
        candyToBuy.setShoppingCartQty(qtyToBuy);
        cart.addItemToCart(candyToBuy);
        // CALCULATE TOTAL COST OF CANDY AT GIVEN QTY
        double cost = candyInventory.costOfCandyAtUserQty(candyID, qtyToBuy);
        // PRINT TO LOG
        logWriter.writePurchaseToLog(candyToBuy, cashBox.getBalance(), cashBox.getBalance() - cost);
        // UPDATE BALANCE
        cashBox.deductFromBalance(cost);
    }

    // COMPLETE SALE
    public Change calculateChange(){
        Change change = cashBox.calculateChange();
        logWriter.writeCashFlowToLog("CHANGE GIVEN", change.getTotalChange(), cashBox.getBalance() - change.getTotalChange());
        return change;
    }
    public void EmptyCart (){
        cart.EmptyCart();
    }

    // PURCHASE VALIDATION METHODS
    public boolean doesCandyExist(String candyID){
        return candyInventory.doesCandyExist(candyID);
    }
    public boolean isCandyInStock(String candyID){
        return candyInventory.isCandyInStock(candyID);
    }
    public boolean isCandyQtyAvailable(String candyID, int candyQty){
        return candyInventory.isCandyQtyAvailable(candyID, candyQty);
    }
    public boolean isBalanceEnoughForQty(String candyID, int candyQty){
        double cost = candyInventory.costOfCandyAtUserQty(candyID, candyQty);
        if (cost <= cashBox.getBalance()) {
            return true;
        }
        return false;
    }

    // FOR JUNIT
    public ShoppingCartItem getCartItem(String productID){
        return cart.getCartItem(productID);
    }

}
