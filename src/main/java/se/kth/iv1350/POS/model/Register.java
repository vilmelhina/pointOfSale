package se.kth.iv1350.POS.model;

/**
 * Handles communication with the cash register.
 */
public class Register {
    
    private Cash amountInRegister;

    public Register() {
        amountInRegister = new Cash(0, "SEK");
    }
    
    /**
     * Placeholder for a method that will update the amount in the register.
     *
     * @param amount amount of cash that was added.
     */
    public void addToRegister(Cash amount) {
        amountInRegister.add(amount);
    }

}
