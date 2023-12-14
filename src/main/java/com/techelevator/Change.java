package com.techelevator;

public class Change {

    private double totalChange;
    private int twenties;
    private int tens;
    private int fives;
    private int ones;
    private int quarters;
    private int dimes;
    private int nickels;

    public Change(){
        totalChange = 0.0;
        twenties = 0;
        tens = 0;
        fives = 0;
        ones = 0;
        quarters = 0;
        dimes = 0;
        nickels = 0;
    }

    // ADD TO
    public void addTwenty() {
        twenties++;
    }
    public void addTen() {
        tens++;
    }
    public void addFive() {
        fives++;
    }
    public void addOne() {
        ones++;
    }
    public void addQuarter() {
        quarters++;
    }
    public void addDime() {
        dimes++;
    }
    public void addNickel() {
        nickels++;
    }

    // SET TOTAL CHANGE


    public void setTotalChange(double totalChange) {
        this.totalChange = totalChange;
    }

    // GETTERS


    public double getTotalChange() {
        return totalChange;
    }

    public int getTwenties() {
        return twenties;
    }

    public int getTens() {
        return tens;
    }

    public int getFives() {
        return fives;
    }

    public int getOnes() {
        return ones;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }
}
