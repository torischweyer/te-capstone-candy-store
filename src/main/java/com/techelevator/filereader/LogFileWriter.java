package com.techelevator.filereader;

import com.techelevator.ShoppingCartItem;
import com.techelevator.view.UserInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    This class should contain any and all details of access to the log file
 */
public class LogFileWriter {

    private String fileName = "Log.txt";
    private File logFile;
    private UserInterface writerUI = new UserInterface();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");
    private DecimalFormat decimalFormat = new DecimalFormat("$0.00");

    // STEVE NOTE: Do not have UserInterface here - Exception should be thrown out to CLI
    public LogFileWriter(){
        try {
            logFile = new File(fileName);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }

        } catch (IOException e) {
            writerUI.printMessage(e.getMessage());
        }
    }
    // STEVE NOTES:
    // Write to Log Methods can be combined to one
    // Values passed in can be Strings so all logic is outside of the LogFileWriter
    // FileWriter is taking too much power - needs to be instantiated outside of method (just once)
        // Is the writer possibly staying open an issue? does it need to be closed in the method?

    public void writePurchaseToLog (ShoppingCartItem candyBought, double beforeBalance, double afterBalance){

        LocalDateTime now = LocalDateTime.now();

        try (FileWriter fileWriter = new FileWriter(logFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {
            String formattedBefore = decimalFormat.format(beforeBalance);
            String formattedAfter = decimalFormat.format(afterBalance);

            bufferedWriter.write(dateTimeFormatter.format(now) + " ");
            bufferedWriter.write(candyBought.getQty() + " " + candyBought.getName() + " " + candyBought.getProductID() + " ");
            bufferedWriter.write(formattedBefore + " " + formattedAfter);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch (IOException e) {
            writerUI.printMessage(e.getMessage());
        }
    }

    public void writeCashFlowToLog (String actionTaken, double beforeBalance, double afterBalance){

        LocalDateTime now = LocalDateTime.now();

        try (FileWriter fileWriter = new FileWriter(logFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {
            String formattedBefore = decimalFormat.format(beforeBalance);
            String formattedAfter = decimalFormat.format(afterBalance);

            bufferedWriter.write(dateTimeFormatter.format(now) + " ");
            bufferedWriter.write(actionTaken + " ");
            bufferedWriter.write(formattedBefore + " " + formattedAfter);
            // NEED TO WRITE ACTION TAKEN (WHEN..TAKE MONEY, GIVING CHANGE, ADDING TO CART)
            // CUSTOMERS BALANCE BEFORE ACTION
            // CUSTOMERS BALANCE AFTER ACTION

            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch (IOException e) {
            writerUI.printMessage(e.getMessage());
        }
    }

}
