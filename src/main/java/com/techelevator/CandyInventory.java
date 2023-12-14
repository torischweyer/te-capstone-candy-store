package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.CandyStoreItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CandyInventory {

    private Map <String, CandyStoreItem> candyMap = new TreeMap<>();

    public CandyInventory (String inventoryFile)  {

        InventoryFileReader fileStockReader = new InventoryFileReader(inventoryFile);
        candyMap = fileStockReader.stockInventory();
    }

    // PRINT INVENTORY
    public List <CandyStoreItem> getListOfCandy (){
        List <CandyStoreItem> listOfCandy = new ArrayList<>();
        for (Map.Entry<String, CandyStoreItem> candy: candyMap.entrySet()) {
            listOfCandy.add(candy.getValue());
        }
        return listOfCandy;
    }
    // SELECT PRODUCT
    public CandyStoreItem takeCandyFromInventory(String candyID, int amountToTake)  {
        // GRABBING THE CANDY
        CandyStoreItem candyToTake = candyMap.get(candyID);
        // ADJUSTING THE STOCK -AND- PUTTING IT BACK INTO INVENTORY
        candyToTake.deductFromQty(amountToTake);
        candyMap.put(candyID, candyToTake);
        return candyToTake;
    }

    // VALIDATION METHODS
    public boolean doesCandyExist(String candyID){
        if (candyMap.containsKey(candyID)) {
            return true;
        }
        return false;
    }

    public boolean isCandyInStock(String candyID){
        int qty = candyMap.get(candyID).getQty();
        if (qty > 0) {
            return true;
        }
        return false;
    }

    public boolean isCandyQtyAvailable(String candyID, int candyQty) {
        int qtyOnHand = candyMap.get(candyID).getQty();
        if (qtyOnHand >= candyQty){
            return true;
        }
        return false;
    }

    public double costOfCandyAtUserQty(String candyID, int qtyToBuy) {
        double price = candyMap.get(candyID).getPrice();
        return price * qtyToBuy;
    }

}
