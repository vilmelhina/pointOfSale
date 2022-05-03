package se.kth.iv1350.POS.model;

/**
 * Represents a specified amount of money in a specific currency.
 */
public class Cash {
    private final double amount;
    private final String currency;
    
    /**
     * Creates an instance 
     * @param amount the amount of cash
     * @param currency the currency
     */
    public Cash(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    /**
     * Adds the Cash instance with another Cash.
     * @param cashToAdd the amount to add 
     * @return a new Cash with the sum
     */
    Cash add(Cash cashToAdd) {
        // TODO: exception if other currency
        Cash result = new Cash(amount + cashToAdd.getAmount(), 
                currency);
        return result;
    }
    
    /**
     * Subtracts another Cash from the Cash instance.
     * @param cashToSubtract the amount to subtract 
     * @return a new Cash with the difference
     */
    Cash subtract(Cash cashToSubtract) {
        // TODO: exception if other currency
        Cash result = new Cash(amount - cashToSubtract.getAmount(), 
                currency);
        return result;
    }
    
    /**
     * Multiplies the Cash instance with a number.
     * @param multiplier what the Cash should be multiplied with
     * @return the new multiplied Cash
     */
    Cash multiply(double multiplier) {
        Cash result = new Cash(amount * multiplier, 
                currency);
        return result;
    }
    
    /**
     * Returns the amount of cash.
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * Returns the currency of the Cash
     * @return the currency as a String
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Formats the Cash as a string.
     */
    @Override
    public String toString() {
        return amount + " " + currency;
    }
    
}
