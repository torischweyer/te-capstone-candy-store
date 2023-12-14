package com.techelevator;

import com.techelevator.items.CandyStoreItem;
import com.techelevator.view.UserInterface;

import java.util.List;

/*
 * This class should control the workflow of the application, but not do any other work
 * 
 * The menu class should communicate with the user, but do no other work
 * 
 * This class should control the logical workflow of the application, but it should do no other
 * work.  It should communicate with the user (System.in and System.out) using the Menu class and ask
 * the CandyStore class to do any work and pass the results between those 2 classes.
 */
public class ApplicationCLI {

	/*
	 * The menu class is instantiated in the main() method at the bottom of this file.  
	 * It is the only class instantiated in the starter code.  
	 * You will need to instantiate all other classes using the new keyword before you can use them.
	 * 
	 * Remember every class and data structure is a data types and can be passed as arguments to methods or constructors.
	 */
	private UserInterface ui;
	private CandyStore candyStore;
	private final int SHOW_INVENTORY = 1;
	private final int MAKE_SALE = 2;
	private final int QUIT = 3;
	private final int TAKE_MONEY = 1;
	private final int SELECT_PRODUCTS = 2;
	private final int COMPLETE_SALE = 3;

	public ApplicationCLI(UserInterface userInterface) {
		this.ui = userInterface;
		candyStore = new CandyStore();
	}

	/*
	 * Your application starts here
	 */
	public void run() {

		ui.showWelcomeMessage();

		while (true) {

			int userChoice = ui.printMainMenu();

			if (userChoice == SHOW_INVENTORY){
				ui.printInventory(candyStore.getInventoryList());
			}
			else if (userChoice == MAKE_SALE){

				while (true) {
					int subChoice = ui.printSubMenu(candyStore.getBalance());
					if (subChoice == TAKE_MONEY) {
						takeMoney();
					} else if (subChoice == SELECT_PRODUCTS) {
						selectProducts();
					} else if (subChoice == COMPLETE_SALE) {
						completeSale();
						break;
					}
					else {
						ui.printMessage("Not a valid menu option, please make another choice");
					}
				}
			}
			else if (userChoice == QUIT) {
				ui.printGoodbye();
				break;
			}
			else {
				ui.printMessage("Not a valid menu option, please make another choice");
			}
		}
	}
	// STEVE NOTE: ui.printMessage should be changed to specific printing methods in UI
	private void takeMoney(){
		double amountToAdd = ui.takeMoney();
		boolean moneyAdded = candyStore.addMoney(amountToAdd);
		if (moneyAdded) {
			ui.printMessage("Money was added to balance");
		} else {
			if (candyStore.getBalance() + amountToAdd > 1000){
				ui.printMessage("Cash Box can only hold up to $1000");
			} else {
				ui.printMessage("Not a valid amount");
			}
		}
	}

	private void selectProducts(){
		ui.printInventory(candyStore.getInventoryList());
		String candyId = ui.askUserForCandyId();
		int candyQty = ui.askUserForCandyQty();
		if (candyQty <= 0) {
			ui.printMessage("Please choose a positive number of candy");
		} else {
			boolean validated = validateTransaction(candyId, candyQty);
			if (validated) {
				candyStore.makePurchase(candyId, candyQty);
				ui.printMessage("Candy was added to the cart");
			}
		}
	}
	private void completeSale(){
		ui.printReceipt(candyStore.getShoppingCartList());
		ui.printChange(candyStore.calculateChange());
		candyStore.BalanceReset();
		candyStore.EmptyCart();
	}

	private boolean validateTransaction(String candyID, int candyQty){
		if (!candyStore.doesCandyExist(candyID)) {
			ui.printMessage("Sorry, that candy does not exist");
			return false;
		}
		else if (!candyStore.isCandyInStock(candyID)) {
			ui.printMessage("Sorry, that candy is out of stock");
			return false;
		}
		else if (!candyStore.isCandyQtyAvailable(candyID, candyQty)) {
			ui.printMessage("Insufficient stock");
			return false;
		}
		else if (!candyStore.isBalanceEnoughForQty(candyID, candyQty)) {
			ui.printMessage("Insufficient funds");
			return false;
		}
		else {
			return true;
		}
	}


	/*
	 * This starts the application, but you shouldn't need to change it.
	 */
	public static void main(String[] args) {
		UserInterface userInterface = new UserInterface();
		ApplicationCLI cli = new ApplicationCLI(userInterface);
		cli.run();
	}
}
