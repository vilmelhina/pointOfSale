package se.kth.iv1350.POS.util;

/**
 * Represents a specified amount of money in a specific currency.
 */

public class Amount {
    private double amount;
    private String currency;
    
    /**
     * Creates an instance 
     * @param amount the amount of money
     * @param currency the currency
     */
    public Amount(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    /**
     * Adds the Amount instance with another Amount.
     * @param amountToAdd the amount to add 
     * @return a new Amount with the sum
     */
    public Amount add(Amount amountToAdd) {
        Amount result = new Amount(amount + amountToAdd.getAmount(), 
                currency);
        return result;
    }
    
    /**
     * Subtracts another Amount from the Amount instance.
     * @param amountToSubtract the amount to subtract 
     * @return a new Amount with the difference
     */
    public Amount subtract(Amount amountToSubtract) {
        // TODO: exception if other currency
        Amount result = new Amount(amount - amountToSubtract.getAmount(), 
                currency);
        return result;
    }
    
    /**
     * Multiplies the Amount instance with a number.
     * @param mul what the Amount should be multiplied with
     * @return the new multiplied Amount
     */
    public Amount multiply(double mul) {
        // TODO: exception if other currency
        Amount result = new Amount(amount * mul, 
                currency);
        return result;
    }
    
    // TODO: fix this? rename amount or Amount?
    /**
     * Returns the amount.
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * Returns the currency of the Amount
     * @return the currency as a String
     */
    public String getCurrency() {
        return currency;
    }

    @Override
    /**
     * Formats the Amount as a string.
     */
    public String toString() {
        return amount + " " + currency;
    }
    
}
