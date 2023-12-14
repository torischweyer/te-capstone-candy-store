package com.techelevator.filereader;

import com.techelevator.items.*;
import com.techelevator.view.UserInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
    This class should contain any and all details of access to the inventory file
 */
public class InventoryFileReader {

    private String inventoryFileName;
    private File inventoryFile;
    private Scanner inventoryScanner;
    private UserInterface readerUI = new UserInterface();

    // STEVE NOTE: Do not have UserInterface here - Exception should be thrown out to CLI
    public InventoryFileReader(String inventoryFileName) {
        this.inventoryFileName = inventoryFileName;
        inventoryFile = new File(inventoryFileName);
        try {
            inventoryScanner = new Scanner(inventoryFile);
        } catch (FileNotFoundException e) {
            readerUI.printMessage(e.getMessage());
        }
    }
    // STEVE NOTES:
    // CandyStoreItem can be declared outside of if-block
    // Item can be put in map after if-block
    // In the while block, all code can be broken out into a method
    public Map <String, CandyStoreItem> stockInventory(){
        Map <String, CandyStoreItem> candyInventory = new TreeMap<>();

        while (inventoryScanner.hasNextLine())  {
            String candyItem = inventoryScanner.nextLine();
            String [] candyInfo = candyItem.split("\\|");
            //convert price to double
            // Check for the product type
            if (candyInfo[0].equals("CH")) {
                CandyStoreItem chocolateCandy = new Chocolate(candyInfo[1], candyInfo[2], Double.parseDouble(candyInfo[3]), candyInfo[4]);
                candyInventory.put(candyInfo[1], chocolateCandy);
            }
            else if (candyInfo[0].equals("SR")) {
                CandyStoreItem sourCandy = new SourCandy(candyInfo[1], candyInfo[2], Double.parseDouble(candyInfo[3]), candyInfo[4]);
                candyInventory.put(candyInfo[1], sourCandy);
            }
            else if (candyInfo[0].equals("HC")) {
                CandyStoreItem hardCandy = new HardCandy(candyInfo[1], candyInfo[2], Double.parseDouble(candyInfo[3]), candyInfo[4]);
                candyInventory.put(candyInfo[1], hardCandy);
            }
            else if (candyInfo[0].equals("LI")) {
                CandyStoreItem licoriceCandy = new Licorice(candyInfo[1], candyInfo[2], Double.parseDouble(candyInfo[3]), candyInfo[4]);
                candyInventory.put(candyInfo[1], licoriceCandy);
            }
        }

        return candyInventory;
    }

}
