package se.kth.iv1350.POS.integration;

/**
 * Thrown when something goes wrong related to the inventory system.
 */
public class InventorySystemException extends RuntimeException{

    /**
     * Creates an instance with a message of what went wrong.
     * @param message a message describing what went wrong
     */
    public InventorySystemException(String message) {
        super(message);
    }
    
}
