package se.kth.iv1350.POS.model;

import java.util.ArrayList;


/**
 * Represents the cash register.
 */
public class Register {
    
    private Cash amountInRegister;
    private ArrayList<RegisterObserver> registerObservers = new ArrayList<>();

    public Register() {
        amountInRegister = new Cash(0, "SEK");
    }
    
    /**
     * Update the addedAmount in the register.
     *
     * @param addedAmount amount of cash that was added.
     */
    public void addToRegister(Cash addedAmount) {
        updateAmountInRegister(addedAmount);
        notifyObservers();
    }
    
    private void updateAmountInRegister(Cash addedAmount) {
        amountInRegister = amountInRegister.add(addedAmount);
    }
    
    private void notifyObservers() {
        for(RegisterObserver observer : registerObservers) {
            observer.totalRevenueChanged(amountInRegister);
        }
    }
    
    /**
     * Adds an observer that should be notified when the amount in the 
     * register changes.
     * @param observer the observer that should be notified
     */
    public void addRentalObserver(RegisterObserver observer) {
        registerObservers.add(observer);
    }

}
