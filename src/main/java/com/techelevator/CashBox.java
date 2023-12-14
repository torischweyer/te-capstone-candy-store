package com.techelevator;

import java.math.BigDecimal;

public class CashBox {

    private double balance;
    private Change change = new Change();
    private final int DEPOSIT_LIMIT = 100;
    private final int BALANCE_MAX = 1000;

    public CashBox() {
        balance = 0.00;
    }

    public void BalanceReset()  {
        // CLEAR BALANCE AND CHANGE BEFORE NEXT SALE
        balance = 0.00;
        change= new Change();
    }

    public double getBalance()  {
        return balance;
    }
    // Steve Note: Validation in the method good, could play with other return types
    public boolean addMoney(double amountToAdd)  {
        // VALIDATING MONEY IS BETWEEN 1-100 and BALANCE WILL NOT EXCEED 1000
        if (amountToAdd <= DEPOSIT_LIMIT && amountToAdd > 0) {
            if (balance + amountToAdd <= BALANCE_MAX) {
                balance += amountToAdd;
                return true;
            }
        }
        return false;
    }

    public void deductFromBalance(double amountToDeduct){
        balance -= amountToDeduct;
    }
    public Change calculateChange() {
        change.setTotalChange(balance);
        BigDecimal changeCount = BigDecimal.valueOf(balance);
        while (changeCount.compareTo(BigDecimal.ZERO) > 0) {
            if (changeCount.compareTo(BigDecimal.valueOf(20.0)) >= 0) {
                change.addTwenty();
                changeCount = changeCount.subtract(BigDecimal.valueOf(20.0));
            } else if (changeCount.compareTo(BigDecimal.valueOf(10.0)) >= 0) {
                change.addTen();
                changeCount = changeCount.subtract(BigDecimal.valueOf(10.0));
            } else if (changeCount.compareTo(BigDecimal.valueOf(5.0)) >= 0) {
                change.addFive();
                changeCount = changeCount.subtract(BigDecimal.valueOf(5.0));
            } else if (changeCount.compareTo(BigDecimal.valueOf(1.0)) >= 0) {
                change.addOne();
                changeCount = changeCount.subtract(BigDecimal.valueOf(1.0));
            } else if (changeCount.compareTo(BigDecimal.valueOf(0.25)) >= 0) {
                change.addQuarter();
                changeCount = changeCount.subtract(BigDecimal.valueOf(0.25));
            } else if (changeCount.compareTo(BigDecimal.valueOf(0.10)) >= 0) {
                change.addDime();
                changeCount = changeCount.subtract(BigDecimal.valueOf(0.10));
            } else if (changeCount.compareTo(BigDecimal.valueOf(0.05)) >= 0) {
                change.addNickel();
                changeCount = changeCount.subtract(BigDecimal.valueOf(0.05));
            } else {
                break;
            }
        }
        return change;
    }


}
