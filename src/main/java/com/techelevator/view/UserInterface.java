package com.techelevator.view;

import com.techelevator.Change;
import com.techelevator.ShoppingCartItem;
import com.techelevator.items.CandyStoreItem;

import java.sql.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/*
 * This is the only class that should have any usage of System.out or System.in
 *
 * Usage of System.in or System.out should not appear ANYWHERE else in your code outside of this class.
 *
 * Work to get input from the user or display output to the user should be done in this class, however, it
 * should include no "work" that is the job of the candy store.
 */
public class UserInterface {
	
	private static final Scanner in = new Scanner(System.in);
	private DecimalFormat decimalFormat = new DecimalFormat("$0.00");

	public void showWelcomeMessage() {
		System.out.println("*****************************************************************");
		System.out.println("*******                  Silver Shamrock                  *******");
		System.out.println("**************            Candy Company            **************");
		System.out.println("*******                (Java Green Edition)               *******");
		System.out.println("*****************************************************************");
		System.out.println();
	}

	public int printMainMenu(){
		System.out.println();
		int choice = 0;
		try {
			System.out.println("(1) Show Inventory");
			System.out.println("(2) Make Sale");
			System.out.println("(3) Quit");
			// ACCOUNT FOR NON-NUMBER CHOICE
			choice = Integer.parseInt(in.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Answer was not a number, please try again");
		}
		return choice;
	}

	public int printSubMenu(double balance){
		System.out.println();
		int subChoice = 0;
		try {
			System.out.println("(1) Take Money");
			System.out.println("(2) Select Products");
			System.out.println("(3) Complete Sale");
			DecimalFormat decimalFormat = new DecimalFormat("$0.00");
			String formattedPrice = decimalFormat.format(balance);
			System.out.println("Current Customer Balance: " + formattedPrice);
			subChoice = Integer.parseInt(in.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Answer was not a number");
		}
		return subChoice;
	}

	public void printInventory(List<CandyStoreItem> candyInventory){
		System.out.println();
		System.out.println("*****************************************************************");
		System.out.println("***            Silver Shamrock's Candy Selection              ***");
		System.out.println("*****************************************************************");
		System.out.println();
		System.out.printf("%-5s %-20s %-10s %-10s %-5s", "Id", "Name", "Wrapper", "Qty", "Price");
		System.out.println();
		for (CandyStoreItem item : candyInventory){
			String qty = String.valueOf(item.getQty());
			if (qty.equals("0")) {
				qty = "SOLD OUT";
			}
			String formattedPrice = decimalFormat.format(item.getPrice());
			System.out.printf("%-5s %-20s %-10s %-10s %-5s", item.getProductID(), item.getName(), item.getWrapped(), qty, formattedPrice);
			System.out.println();
		}
		System.out.println();
	}

	public void printReceipt(List<ShoppingCartItem> cartList) {
		System.out.println("*****************************************************************");
		System.out.println("*****                   Customer Receipt                    *****");
		System.out.println("*****************************************************************");
		System.out.println();
		double grandTotal = 0.00;

		for (ShoppingCartItem item : cartList){
			String individualPrice = decimalFormat.format(item.getPrice());
			double tempTotal = item.getPrice() * item.getQty();
			grandTotal += tempTotal;
			String individualTotal = decimalFormat.format(tempTotal);
			System.out.printf("%-5s %-18s %-25s %-7s %-7s", item.getQty(), item.getName(), item.getProductType(), individualPrice, individualTotal);
			System.out.println();
		}
		System.out.println();
		String formattedTotal = decimalFormat.format(grandTotal);
		System.out.println("Total: " + formattedTotal);
	}

	public void printChange(Change change){
		System.out.println();
		String changeTotal = decimalFormat.format(change.getTotalChange());
		System.out.println("Change: " + changeTotal);

		List<String> changeDenominations = new ArrayList<>();
		if (change.getTwenties() >= 1) {
			changeDenominations.add("(" + change.getTwenties() + ") Twenties");
		}
		if (change.getTens() >= 1) {
			changeDenominations.add("(" + change.getTens() + ") Tens");
		}
		if (change.getFives() >= 1) {
			changeDenominations.add("(" + change.getFives() + ") Fives");
		}
		if (change.getOnes() >= 1) {
			changeDenominations.add("(" + change.getOnes() + ") Ones");
		}
		if (change.getQuarters() >= 1) {
			changeDenominations.add("(" + change.getQuarters() + ") Quarters");
		}
		if (change.getDimes() >= 1) {
			changeDenominations.add("(" + change.getDimes() + ") Dimes");
		}
		if (change.getNickels() >= 1) {
			changeDenominations.add("(" + change.getNickels() + ") Nickels");
		}
		String changeToPrint = String.join(", ", changeDenominations);
		System.out.println(changeToPrint);
		System.out.println();
	}

	public double takeMoney(){
		double amountToAdd = 0.00;
		try {
			System.out.println("How much would you like to add?");
			amountToAdd = Double.parseDouble(in.nextLine());
			if (Math.floor(amountToAdd) == amountToAdd) {
				return amountToAdd;
			} else {
				System.out.println("Whole dollar amounts only, please!");
				return 0.00;
			}
		} catch (NumberFormatException e) {
			System.out.println("Please pass in a number");
		}
		return amountToAdd;

	}
	public String askUserForCandyId()  {

		System.out.println("Choose a Candy:");
		String candyChoice = in.nextLine().toUpperCase();
		return candyChoice;

	}
	public int askUserForCandyQty () {

		int quantityChoice = 0;
		try {
			System.out.println("How many would you like ?");
			quantityChoice = Integer.parseInt(in.nextLine());
		}catch (NumberFormatException e) {
			System.out.println("Not a valid entry for quantity of candy");
		}

		return quantityChoice;
	}

	public void printMessage(String message){
		System.out.println(message);
	}

	public void printGoodbye(){
		System.out.println("*****************************************************************");
		System.out.println("***             Thank you for shopping at the                 ***");
		System.out.println("*****           Silver Shamrock Candy Company               *****");
		System.out.println("*****************************************************************");
	}

}
